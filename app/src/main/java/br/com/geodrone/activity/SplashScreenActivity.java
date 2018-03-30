package br.com.geodrone.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


import br.com.geodrone.BuildConfig;
import br.com.geodrone.R;
import br.com.geodrone.ui.login.LoginActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreenActivity extends AppCompatActivity {

    @BindView(R.id.text_view_versao)TextView txtViewVersao;
    @BindView(R.id.progressBar_abertura)
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);
        txtViewVersao.setText("Versão "+ BuildConfig.VERSION_NAME);
        progressBar.setVisibility(View.VISIBLE);
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
            mostrarLogin();
            }
        }, 3000);
    }

    private void mostrarLogin() {
        progressBar.setVisibility(View.INVISIBLE);
        Intent i = new Intent(this,LoginActivity.class);
        startActivity(i);
        finish();
    }
}
