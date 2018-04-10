package br.com.geodrone.ui.login;


import android.app.Activity;

import br.com.geodrone.R;
import br.com.geodrone.Session;
import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.Dispositivo;
import br.com.geodrone.model.Usuario;
import br.com.geodrone.service.DispositivoService;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.base.BasePresenter;
import br.com.geodrone.service.ClienteService;
import br.com.geodrone.service.UsuarioService;
import br.com.geodrone.utils.PreferencesUtils;

/**
 * Created by fernandes on 29/03/2018.
 */
public class LoginPresenter extends BasePresenter<LoginPresenter.View> {

    interface View {

        void onClickLogin();

        void onSuccessoLogin(String message);

        void onErrorEmail(String message);

        void onErrorSenha(String message);
    }

    UsuarioService usuarioService = null;
    ClienteService clienteService = null;
    DispositivoService dispositivoService = null;

    private BaseActivity activity;

    public LoginPresenter(BaseActivity activity){
        this.activity = activity;
        this.usuarioService = new UsuarioService(activity);
        this.clienteService = new ClienteService(activity);
        this.dispositivoService = new DispositivoService(activity);
        this.activity.hideLoading();
    }

    private boolean validarLogin(String login, String senha) {
        boolean isOk = true;
        if (hasView()) {
            if (login == null){
                isOk = false;
                view.onErrorEmail(activity.getString(R.string.msg_obr_email));
            }
            if (senha == null){
                isOk = false;
                view.onErrorSenha(activity.getString(R.string.msg_obr_senha));
            }
        }
        return isOk;
    }

    public void login(String login, String senha) {
        try {
            this.activity.showLoading();
            if (validarLogin(login, senha)) {
                Usuario usuario = usuarioService.findByEmail(login);
                if (usuario != null) {
                    PreferencesUtils.putString(this.activity.getApplicationContext(), PreferencesUtils.CHAVE_EMAIL_USUARIO, login);
                    PreferencesUtils.putString(this.activity.getApplicationContext(), PreferencesUtils.CHAVE_SENHA_USUARIO, senha);
                    PreferencesUtils.putUsuario(this.activity.getApplicationContext(), usuario);
                    Cliente cliente = this.clienteService.findById(usuario.getIdCliente());
                    Dispositivo dispositivo = this.dispositivoService.findOne();
                    Session.setAttribute(PreferencesUtils.CHAVE_USUARIO, usuario);
                    Session.setAttribute(PreferencesUtils.CHAVE_CLIENTE, cliente);
                    Session.setAttribute(PreferencesUtils.CHAVE_DISPOSITIVO, dispositivo);
                    view.onSuccessoLogin(activity.getString(R.string.msg_obr_login_sucesso));
                } else {
                    activity.onError(activity.getString(R.string.msg_inv_login));
                    view.onSuccessoLogin(activity.getString(R.string.msg_obr_login_sucesso));
                }
            }
            this.activity.hideLoading();
        }catch (Exception ex){
            this.activity.hideLoading();
            activity.onError(ex);

        }
    }

}