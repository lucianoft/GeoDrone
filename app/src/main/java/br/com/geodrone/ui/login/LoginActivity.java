package br.com.geodrone.ui.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import br.com.geodrone.R;
import br.com.geodrone.activity.CadatroUsuarioActivity;
import br.com.geodrone.activity.MainActivity;
import br.com.geodrone.activity.utils.ActivityHelper;
import br.com.geodrone.utils.PreferencesUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginPresenter.View{
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    private LoginPresenter loginPresenter = null;
    @BindView(R.id.edit_text_email_login) EditText editTextEmail;
    @BindView(R.id.edit_text_senha_login) EditText editTextSenha;
    @BindView(R.id.button_olho) Button buttonOlho;
    @BindView(R.id.progressBar_login) ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        loginPresenter = new LoginPresenter(this);
        editTextEmail.setText(PreferencesUtils.getString(getApplicationContext(), PreferencesUtils.CHAVE_EMAIL_USUARIO, ""));
        editTextSenha.setText(PreferencesUtils.getString(getApplicationContext(), PreferencesUtils.CHAVE_SENHA_USUARIO, ""));
    }
    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        loginPresenter.takeView(this);
        onInvisibleProgressBar();
    }

    @Override
    public void onLoginErro() {
        Toast.makeText(this, R.string.msg_login_invalido, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessoLogin() {
        onInvisibleProgressBar();
        PreferencesUtils.putString(getApplicationContext(), PreferencesUtils.CHAVE_EMAIL_USUARIO, editTextEmail.getText().toString());
        PreferencesUtils.putString(getApplicationContext(), PreferencesUtils.CHAVE_SENHA_USUARIO, editTextSenha.getText().toString());
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onEmailLoginErro() {
        onInvisibleProgressBar();
        editTextEmail.setError("enter a valid email address");
    }

    @Override
    public void onSenhaLoginErro() {
        onInvisibleProgressBar();
        editTextEmail.setError("enter a valid email address");
    }

    @OnClick(R.id.button_login)
    public void onLoginRequested() {
        loginPresenter.login(editTextEmail.getText().toString(), editTextSenha.getText().toString());
    }

    @OnClick(R.id.text_view_criar_usuario)
    public void onClickNovoUsuario() {
        Intent i = new Intent(LoginActivity.this ,CadatroUsuarioActivity.class);
        startActivity(i);
    }

    @OnClick(R.id.button_olho)
    public void onClickOlho() {
        if(editTextSenha.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            editTextSenha.setInputType( InputType.TYPE_CLASS_TEXT |
                    InputType.TYPE_TEXT_VARIATION_PASSWORD);
            buttonOlho.setBackgroundResource(R.mipmap.eye_closed);
        }else {
            editTextSenha.setInputType( InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD );
            buttonOlho.setBackgroundResource(R.mipmap.eye_opened);
        }
        editTextSenha.setSelection(editTextSenha.getText().length());
    }


    @Override
    public void onVisibleProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onInvisibleProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);

    }
}