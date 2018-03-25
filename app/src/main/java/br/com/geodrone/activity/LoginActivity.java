package br.com.geodrone.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.activity.utils.ActivityHelper;
import br.com.geodrone.presenter.UsuarioPresenter;
import br.com.geodrone.utils.PreferencesUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

import static android.Manifest.permission.READ_CONTACTS;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    private UsuarioPresenter usuarioPresenter = null;
    @BindView(R.id.edit_text_email_login) EditText _emailText;
    @BindView(R.id.edit_text_senha_login) EditText _passwordText;
    @BindView(R.id.button_olho) Button _showPasswordButton;
    @BindView(R.id.button_login) Button _loginButton;
    @BindView(R.id.text_view_criar_usuario) TextView _signupLink;
    @BindView(R.id.progressBar_login) ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        progressBar.setVisibility(View.INVISIBLE);

        usuarioPresenter = new UsuarioPresenter(this);
        _emailText.setText(PreferencesUtils.getString(getApplicationContext(), PreferencesUtils.CHAVE_EMAIL_USUARIO, ""));
        _passwordText.setText(PreferencesUtils.getString(getApplicationContext(), PreferencesUtils.CHAVE_SENHA_USUARIO, ""));


        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                //Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                //startActivityForResult(intent, REQUEST_SIGNUP);
                Intent i = new Intent(LoginActivity.this ,CadatroUsuarioActivity.class);
                startActivity(i);

                //finish();
                //overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
        _showPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            if(_passwordText.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                _passwordText.setInputType( InputType.TYPE_CLASS_TEXT |
                        InputType.TYPE_TEXT_VARIATION_PASSWORD);
                _showPasswordButton.setBackgroundResource(R.mipmap.eye_closed);
            }else {
                _passwordText.setInputType( InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD );
                _showPasswordButton.setBackgroundResource(R.mipmap.eye_opened);
            }
                _passwordText.setSelection(_passwordText.getText().length());
            }
        });
        ActivityHelper activityHelper = new ActivityHelper();
        activityHelper.escondeTeclado(this);
    }

    public void login() {
        Log.d(TAG, "Login");

        progressBar.setVisibility(View.VISIBLE);

        if (!validate()) {
            onLoginFailed();
            progressBar.setVisibility(View.INVISIBLE);

            return;
        }
        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        usuarioPresenter.findByEmail(email, password);
        _loginButton.setEnabled(false);


        // TODO: Implement your own authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                    }
                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the SplashScreenActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        progressBar.setVisibility(View.INVISIBLE);
        _loginButton.setEnabled(true);
        PreferencesUtils.putString(getApplicationContext(), PreferencesUtils.CHAVE_EMAIL_USUARIO, _emailText.getText().toString());
        PreferencesUtils.putString(getApplicationContext(), PreferencesUtils.CHAVE_SENHA_USUARIO, _passwordText.getText().toString());
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }

    public void onLoginFailed() {
        //UtilToas.showMsgAlertOK(this, "Erro", "Falha", TipoMsg.ERRO);
        progressBar.setVisibility(View.INVISIBLE);

        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}