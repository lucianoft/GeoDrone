package br.com.geodrone.ui.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import br.com.geodrone.R;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.helper.GenericProgress;
import br.com.geodrone.ui.usuario.CadatroUsuarioActivity;
import br.com.geodrone.ui.main.MainActivity;
import br.com.geodrone.utils.KeyboardUtils;
import br.com.geodrone.utils.PreferencesUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginPresenter.View{
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    private LoginPresenter loginPresenter = null;
    @BindView(R.id.edit_text_email_login) EditText editTextEmail;
    @BindView(R.id.edit_text_senha_login) EditText editTextSenha;
    @BindView(R.id.button_olho) Button buttonOlho;

    private GenericProgress mProgress;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mProgress = new GenericProgress(this);
        loginPresenter = new LoginPresenter(this);
        editTextEmail.setText(PreferencesUtils.getString(getApplicationContext(), PreferencesUtils.CHAVE_EMAIL_USUARIO, ""));
        editTextSenha.setText(PreferencesUtils.getString(getApplicationContext(), PreferencesUtils.CHAVE_SENHA_USUARIO, ""));

        KeyboardUtils.hideSoftInput(this);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        loginPresenter.takeView(this);
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
    @OnClick(R.id.button_login)
    public void onClickLogin() {
        showLoading();
        loginPresenter.login(editTextEmail.getText().toString(), editTextSenha.getText().toString());
    }

    @Override
    public void onSuccessoLogin(String message) {

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        PreferencesUtils.putString(getApplicationContext(), PreferencesUtils.CHAVE_EMAIL_USUARIO, editTextEmail.getText().toString());
        PreferencesUtils.putString(getApplicationContext(), PreferencesUtils.CHAVE_SENHA_USUARIO, editTextSenha.getText().toString());
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onErrorEmail(String message) {
        hideLoading();
        editTextEmail.setError(message);
    }

    @Override
    public void onErrorSenha(String message) {
        hideLoading();
        editTextSenha.setError(message);
    }

    @Override
    public void showLoading() {
        mProgress.show();

    }

    @Override
    public void hideLoading() {
        mProgress.hide();

    }
}