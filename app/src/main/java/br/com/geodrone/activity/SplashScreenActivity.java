package br.com.geodrone.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;


import br.com.geodrone.BuildConfig;
import br.com.geodrone.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreenActivity extends AppCompatActivity {

    @BindView(R.id.aberturaVersaoTV)TextView _versaoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);
        Handler handle = new Handler();
        final ProgressDialog progressDialog = new ProgressDialog(SplashScreenActivity.this,
                R.style.AppTheme_PopUp);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Carregando...");
        progressDialog.show();

        _versaoText.setText("Versão "+ BuildConfig.VERSION_NAME);
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
            mostrarLogin();
            progressDialog.dismiss();
            }
        }, 3000);
    }

    private void mostrarLogin() {
        Intent i = new Intent(this,LoginActivity.class);
        startActivity(i);
        finish();
    }
}
