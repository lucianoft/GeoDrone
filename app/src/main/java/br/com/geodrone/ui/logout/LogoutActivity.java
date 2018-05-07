package br.com.geodrone.ui.logout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.geodrone.R;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.helper.GenericProgress;
import br.com.geodrone.ui.login.LoginPresenter;

public class LogoutActivity extends BaseActivity implements LogoutPresenter.View{

    private LogoutPresenter logoutPresenter = null;
    private GenericProgress mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);
        mProgress = new GenericProgress(this);

        logoutPresenter = new LogoutPresenter(this);

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        logoutPresenter.takeView(this);
        showLoading();
        logoutPresenter.logout();
    }

    @Override
    public void showLoading() {
        mProgress.show();
    }

    @Override
    public void hideLoading() {
        mProgress.hide();
    }

    @Override
    public void finishApp(){
        hideLoading();
        finishAffinity();
    }
}
