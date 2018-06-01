package br.com.geodrone.ui.helper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Date;
import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.SessionGeooDrone;
import br.com.geodrone.crypt.Cryptography;
import br.com.geodrone.crypt.KeyUtils;
import br.com.geodrone.dto.ColetaPluviosidadeDto;
import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.Configuracao;
import br.com.geodrone.model.Dispositivo;
import br.com.geodrone.model.TipoCultivo;
import br.com.geodrone.model.Usuario;
import br.com.geodrone.oauth.APIClient;
import br.com.geodrone.oauth.ServiceGenerator;
import br.com.geodrone.oauth.dto.AccessToken;
import br.com.geodrone.resource.AlterarSenhaUsuarioResourse;
import br.com.geodrone.resource.SincronizacaoAndroidResource;
import br.com.geodrone.service.ClienteService;
import br.com.geodrone.service.ConfiguracaoService;
import br.com.geodrone.service.UsuarioService;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.utils.DateUtils;
import br.com.geodrone.utils.ErrorUtils;
import br.com.geodrone.utils.Messenger;
import br.com.geodrone.utils.PreferencesUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by luciano on 31/05/2018.
 */
public class ActivityHelper {

    public void alterarSenha(final BaseActivity activity) {
        LayoutInflater li = activity.getLayoutInflater();
        View view = li.inflate(R.layout.dialog_alterar_senha, null);
        final EditText ediTextSenhaAtual = view.findViewById(R.id.input_senha_usuario_atual);
        final EditText ediTextNovaSenha = view.findViewById(R.id.input_nova_senha_usuario);
        final EditText ediTextConfirmarNovaSenha = view.findViewById(R.id.input_confirmar_senha_usuario);
        final AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle(activity.getString(R.string.lb_alterar_senha));
        alert.setView(view);
        alert.setCancelable(false);
        alert.setNegativeButton(R.string.lb_cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        final GenericProgress genericProgress = new GenericProgress(activity);;
        alert.setPositiveButton(R.string.lb_confirmar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AlterarSenhaUsuarioResourse alterarSenhaUsuarioResourse = new AlterarSenhaUsuarioResourse();
                alterarSenhaUsuarioResourse.setSenha(ediTextSenhaAtual.getText().toString());
                alterarSenhaUsuarioResourse.setNovaSenha(ediTextNovaSenha.getText().toString());
                alterarSenhaUsuarioResourse.setConfirmaSenha(ediTextConfirmarNovaSenha.getText().toString());
                alterarSenha(activity, alterarSenhaUsuarioResourse, genericProgress, dialog);

            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();

    }

    private void alterarSenha(final BaseActivity activity, final AlterarSenhaUsuarioResourse alterarSenhaUsuarioResourse,
                             final GenericProgress genericProgress, final DialogInterface dialog){
        try {

            genericProgress.show();
            ConfiguracaoService configuracaoService = new ConfiguracaoService(activity);
            final UsuarioService usuarioService = new UsuarioService(activity);
            AccessToken accessToken = PreferencesUtils.getAccessToken(activity);
            final Usuario usuario = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_USUARIO);

            Configuracao configuracao = configuracaoService.getOneConfiguracao();
            final String URL_BASE = configuracao.getUrl();
            APIClient client = ServiceGenerator.getInstance(URL_BASE).createServiceWithAuth(APIClient.class, accessToken);
            DateUtils dateUtils = new DateUtils();
            Call<Void> call = client.alterarSenhaUsuario(accessToken.getIdUsuario(),
                    alterarSenhaUsuarioResourse);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if(response.isSuccessful()) {
                        usuarioService.alterarSenha(usuario, alterarSenhaUsuarioResourse.getNovaSenha());
                        activity.showMessage(activity.getString(R.string.msg_operacao_sucesso));
                    } else {
                        Messenger messenger = ErrorUtils.parseError(response, URL_BASE);
                        activity.showMessenger(messenger);
                    }
                    dialog.dismiss();
                    genericProgress.hide();
                }
                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    activity.showMessage(t.getMessage());
                    Log.e("Alterar Senha", t.toString(), t);
                    dialog.dismiss();
                    genericProgress.hide();
                }
            });

        }catch (Exception ex){
            activity.showMessage(ex.getMessage());
            Log.e("alterar senha", ex.toString(), ex);
            dialog.dismiss();
            genericProgress.hide();
        }
    }



    public void alterarCliente(final BaseActivity activity) {

        ClienteService clienteService = new ClienteService(activity);
        List<Cliente> clientes = clienteService.findAllByUsuario();
        LayoutInflater li = activity.getLayoutInflater();
        View view = li.inflate(R.layout.dialog_alterar_cliente, null);
        final Spinner spClientes = view.findViewById(R.id.spinner_cliente_alt_cliente);
        ArrayAdapter<Cliente> myAdapter = new ArrayAdapter<Cliente>(activity, android.R.layout.simple_spinner_item, clientes);
        spClientes.setAdapter(myAdapter);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle(activity.getString(R.string.lb_alterar_cliente));
        alert.setView(view);
        alert.setCancelable(false);
        alert.setNegativeButton(R.string.lb_cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        final GenericProgress genericProgress = new GenericProgress(activity);;
        alert.setPositiveButton(R.string.lb_confirmar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alterarCliente(spClientes);
                dialog.dismiss();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();

    }

    private void alterarCliente(Spinner spClientes){
        Cliente cliente = (Cliente) spClientes.getSelectedItem();
        if (cliente != null){
            SessionGeooDrone.setAttribute(SessionGeooDrone.CHAVE_CLIENTE, cliente);
        }
    }

    public void esqueciMinhaSenha(final BaseActivity activity) {
        LayoutInflater li = activity.getLayoutInflater();
        View view = li.inflate(R.layout.dialog_esqueci_minha_senha, null);
        final EditText ediTextEmail = view.findViewById(R.id.input_email_usuario);
        final AlertDialog.Builder alert = new AlertDialog.Builder(activity);
        alert.setTitle(activity.getString(R.string.lb_alterar_senha));
        alert.setView(view);
        alert.setCancelable(false);
        alert.setNegativeButton(R.string.lb_cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alert.setPositiveButton(R.string.lb_confirmar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                esqueciMinhaSenha(activity, ediTextEmail.getText().toString(), dialog);

            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    private void esqueciMinhaSenha(final BaseActivity activity, final String email,
                                  final DialogInterface dialog){
        final GenericProgress genericProgress = new GenericProgress(activity);;
        try {
            genericProgress.show();
            ConfiguracaoService configuracaoService = new ConfiguracaoService(activity);
            Configuracao configuracao = configuracaoService.getOneConfiguracao();
            final String URL_BASE = configuracao.getUrl();
            APIClient client = ServiceGenerator.getInstance(URL_BASE).createServiceWithoutAuth(APIClient.class);
            DateUtils dateUtils = new DateUtils();
            Call<Void> call = client.recuperarSenha(email);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if(response.isSuccessful()) {
                        activity.showMessage(activity.getString(R.string.msg_nova_senha_enviada));
                    } else {
                        Messenger messenger = ErrorUtils.parseError(response, URL_BASE);
                        activity.showMessenger(messenger);
                    }
                    dialog.dismiss();
                    genericProgress.hide();
                }
                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    activity.showMessage(t.getMessage());
                    Log.e("Alterar Senha", t.toString(), t);
                    dialog.dismiss();
                    genericProgress.hide();
                }
            });

        }catch (Exception ex){
            activity.showMessage(ex.getMessage());
            Log.e("alterar senha", ex.toString(), ex);
            dialog.dismiss();
            genericProgress.hide();
        }
    }
}
