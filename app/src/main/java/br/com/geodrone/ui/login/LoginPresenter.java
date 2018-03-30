package br.com.geodrone.ui.login;


import android.app.Activity;

import br.com.geodrone.presenter.BasePresenter;
import br.com.geodrone.presenter.ProgressBarPresenter;

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

    private Activity activity;

    public LoginPresenter(Activity activity){
        this.activity = activity;
    }

    public void login(String login, String senha) {

        if (hasView()) {
            if (login == null){
                view.onEmailLoginErro();
            }
            if (senha == null){
                view.onSenhaLoginErro();
            }
            view.onSuccessoLogin();
        }
    }
}