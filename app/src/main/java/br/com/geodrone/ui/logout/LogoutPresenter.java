package br.com.geodrone.ui.logout;

import android.os.Handler;

import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.base.BasePresenter;
import br.com.geodrone.ui.login.LoginPresenter;

/**
 * Created by fernandes on 06/05/2018.
 */

public class LogoutPresenter extends BasePresenter<LogoutPresenter.View> {

    private static  String TAG = LoginPresenter.class.getName();

    interface View {
        void finishApp();
    }

    private BaseActivity activity;

    public LogoutPresenter(BaseActivity activity){
        this.activity = activity;
    }
    public void logout(){
        if (hasView()){
            view.finishApp();
        }
    }

}
