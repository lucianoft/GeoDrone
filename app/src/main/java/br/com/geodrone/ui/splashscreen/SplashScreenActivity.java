package br.com.geodrone.ui.splashscreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


import br.com.geodrone.BuildConfig;
import br.com.geodrone.R;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.login.LoginActivity;
import br.com.geodrone.ui.primeirologin.PrimeiroLoginActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreenActivity extends BaseActivity implements SplashScreenPresenter.View {

    @BindView(R.id.text_view_versao)TextView txtViewVersao;

    SplashScreenPresenter splashScreenPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);
        splashScreenPresenter = new SplashScreenPresenter(this);
        txtViewVersao.setText("Versão "+ BuildConfig.VERSION_NAME);
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                onLoadLogin();
            }
        }, 3000);
    }


    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        splashScreenPresenter.takeView(this);
        hideLoading();
    }

    @Override
    public void onLoadLogin() {
        Intent i = null;
        if (!splashScreenPresenter.isPrimeiroLogin()){
            i = new Intent(this,LoginActivity.class);
        }else{
            i = new Intent(this,PrimeiroLoginActivity.class);
        }
        startActivity(i);
        finish();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
