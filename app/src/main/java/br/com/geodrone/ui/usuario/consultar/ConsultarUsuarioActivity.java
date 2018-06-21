package br.com.geodrone.ui.usuario.consultar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.model.constantes.FlagPerfilUsuario;
import br.com.geodrone.model.constantes.FlagSimNao;
import br.com.geodrone.resource.MicroRegiaoResource;
import br.com.geodrone.resource.UsuarioResource;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.helper.GenericProgress;
import br.com.geodrone.utils.Messenger;
import br.com.geodrone.utils.NumberUtils;
import br.com.geodrone.utils.Util;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConsultarUsuarioActivity extends BaseActivity implements ConsultarUsuarioPresenter.View {

    private GenericProgress mProgress;
    private ConsultarUsuarioPresenter consultarUsuarioPresenter;
    private ConsultarUsuarioAdapter consultarUsuarioAdapter;

    @BindView(R.id.recyclerViewUsuario)
    RecyclerView recyclerViewUsuarios;
    private List<MicroRegiaoResource> microRegiaoResources = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_usuario);
        ButterKnife.bind(this);

        if (!Util.verificaConexao(this)) {
            Util.initToast(this, getString(R.string.msg_sem_conexao_internet));
            finish();
        } else {

            mProgress = new GenericProgress(this);
            consultarUsuarioPresenter = new ConsultarUsuarioPresenter(this);
            showLoading();

            recyclerViewUsuarios.setLayoutManager(new LinearLayoutManager(this));

            consultarUsuarioAdapter = new ConsultarUsuarioAdapter();
            consultarUsuarioAdapter.setActivity(this);
            recyclerViewUsuarios.setAdapter(consultarUsuarioAdapter);
            // Configurando um dividr entre linhas, para uma melhor visualização.
            recyclerViewUsuarios.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        }
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        consultarUsuarioPresenter.takeView(this);
        consultarUsuarioPresenter.findAllUsuario();
    }

    @Override
    public void onClickUsuario(final UsuarioResource usuarioResource) {
        LayoutInflater li = this.getLayoutInflater();
        View view = li.inflate(R.layout.dialog_alterar_usuario, null);
        final EditText editTextNome = view.findViewById(R.id.editText_nome);
        final EditText editTextCpfCnpj = view.findViewById(R.id.editText_cpf_cnpj);

        final EditText editTextEmail = view.findViewById(R.id.editText_email);
        final EditText editTextTelefone = view.findViewById(R.id.edit_text_telefone);
        final EditText ediTextSenha = view.findViewById(R.id.input_senha_usuario);
        final EditText ediTextConfirmarNovaSenha = view.findViewById(R.id.input_confirmar_senha_usuario);

        final FlagPerfilUsuario[] flagPerfis = {FlagPerfilUsuario.CLIENTE, FlagPerfilUsuario.COLETOR};

        final Spinner spPerfil = view.findViewById(R.id.spinner_perfil);
        ArrayAdapter<FlagPerfilUsuario> adapterStatus = new ArrayAdapter<FlagPerfilUsuario>(this, android.R.layout.simple_spinner_item, flagPerfis);
        spPerfil.setAdapter(adapterStatus);
        adapterStatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        final Spinner spAtivo = view.findViewById(R.id.spinner_ativo);
        ArrayAdapter<FlagSimNao> adapterAtivo = new ArrayAdapter<FlagSimNao>(this, android.R.layout.simple_spinner_item, FlagSimNao.values());
        spAtivo.setAdapter(adapterAtivo);
        adapterAtivo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        editTextNome.setText(usuarioResource.getNome());
        editTextCpfCnpj.setText(usuarioResource.getCpfCnpj() != null ? usuarioResource.getCpfCnpj().toString() : "");
        editTextEmail.setText(usuarioResource.getEmail());
        editTextTelefone.setText(usuarioResource.getTelefone());
        ediTextSenha.setText(usuarioResource.getSenha());
        ediTextConfirmarNovaSenha.setText(usuarioResource.getSenha());
        spPerfil.setSelection(getIndexPerfil(spPerfil, usuarioResource.getFlagPerfil()));
        spAtivo.setSelection(getIndexFlagAtivo(spAtivo, usuarioResource.getIndAtivo()));

        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        if (usuarioResource.getId() != null) {
            alert.setTitle(this.getString(R.string.lb_alterar_usuario));
        }else {
            alert.setTitle(this.getString(R.string.lb_cadastrar_usuario));
        }
        alert.setView(view);
        alert.setCancelable(false);
        alert.setNegativeButton(R.string.lb_cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        final GenericProgress genericProgress = new GenericProgress(this);
        alert.setPositiveButton(R.string.lb_confirmar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showLoading();
                FlagPerfilUsuario flagPerfilUsuario = (FlagPerfilUsuario) spPerfil.getSelectedItem();
                FlagSimNao flagSimNao = (FlagSimNao) spAtivo.getSelectedItem();
               consultarUsuarioPresenter.salvarUsuario(usuarioResource, editTextNome.getText().toString(), editTextCpfCnpj.getText().toString(),
                       editTextEmail.getText().toString(), editTextTelefone.getText().toString(), ediTextSenha.getText().toString(), ediTextConfirmarNovaSenha.getText().toString(),
                       flagPerfilUsuario != null ? flagPerfilUsuario.getValue() : null,
                       flagSimNao != null ? flagSimNao.getValue() : null);
               dialog.dismiss();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    @OnClick(R.id.float_button_incluir_usuario)
    public void onClickNovoUsuario(){
        onClickUsuario(new UsuarioResource());
    }


    @Override
    public void onSalvarUsuarioSucesso(String message) {
        showMessage(message);
        hideLoading();
    }

    @Override
    public void onError(Messenger messenger) {
        showMessenger(messenger);
        hideLoading();
    }

    @Override
    public void onListUsuario(List<UsuarioResource> usuarioResources) {
        consultarUsuarioAdapter.clearData();

        int i = 0;
        if (usuarioResources != null) {
            for (UsuarioResource usuarioResource : usuarioResources) {
                ++i;
                consultarUsuarioAdapter.addData(usuarioResource);
            }
        }
        consultarUsuarioAdapter.notifyDataSetChanged();
        hideLoading();
    }

    @Override
    public void onListMicroRegiao(List<MicroRegiaoResource> microRegiaoResources) {
        this.microRegiaoResources = microRegiaoResources;
    }


    @Override
    public void showLoading() {
        mProgress.show();
    }

    @Override
    public void hideLoading() {
        mProgress.hide();
    }


    private int getIndexPerfil(Spinner spinner, String string) {
        int index = 0;

        FlagPerfilUsuario flagPerfi = FlagPerfilUsuario.getInstance(string);
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(flagPerfi)) {
                index = i;
                break;
            }
        }
        return index;
    }

    private int getIndexFlagAtivo(Spinner spinner, Integer indAtivo) {
        int index = 0;

        FlagSimNao flagPerfi = FlagSimNao.getInstance(indAtivo);
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).equals(flagPerfi)) {
                index = i;
                break;
            }
        }
        return index;
    }
}
