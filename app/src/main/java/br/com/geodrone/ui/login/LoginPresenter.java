package br.com.geodrone.ui.login;


import android.app.Activity;

import br.com.geodrone.Session;
import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.Usuario;
import br.com.geodrone.presenter.BasePresenter;
import br.com.geodrone.presenter.ProgressBarPresenter;
import br.com.geodrone.service.ClienteService;
import br.com.geodrone.service.UsuarioService;
import br.com.geodrone.utils.PreferencesUtils;

/**
 * Created by fernandes on 29/03/2018.
 */

public class LoginPresenter extends BasePresenter<LoginPresenter.View> {

    interface View extends ProgressBarPresenter {

        void onLoginRequested();

        void onLoginErro();

        void onSuccessoLogin();

        void onEmailLoginErro();

        void onSenhaLoginErro();
    }

    UsuarioService usuarioService = null;
    ClienteService clienteService = null;
    private Activity activity;

    public LoginPresenter(Activity activity){
        this.activity = activity;
        this.usuarioService = new UsuarioService(activity);
        this.clienteService = new ClienteService(activity);
    }

    private boolean validarLogin(String login, String senha) {
        boolean isOk = true;
        if (hasView()) {
            if (login == null){
                isOk = false;
                view.onEmailLoginErro();
            }
            if (senha == null){
                isOk = false;
                view.onSenhaLoginErro();
            }
        }
        return isOk;
    }

    public void login(String login, String senha) {
        if (validarLogin(login, senha)){
            Usuario usuario = usuarioService.findByEmail(login);
            if (usuario != null){
                PreferencesUtils.putString(this.activity.getApplicationContext(), PreferencesUtils.CHAVE_EMAIL_USUARIO, login);
                PreferencesUtils.putString(this.activity.getApplicationContext(), PreferencesUtils.CHAVE_SENHA_USUARIO, senha);
                PreferencesUtils.putUsuario(this.activity.getApplicationContext(), usuario);
                Cliente cliente = this.clienteService.findById(usuario.getIdClienteRef());
                Session.setAttribute(PreferencesUtils.CHAVE_USUARIO, usuario);
                Session.setAttribute(PreferencesUtils.CHAVE_CLIENTE, cliente);
                view.onSuccessoLogin();
            }else{
                view.onLoginErro();
            }
        }

    }
}