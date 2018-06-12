package br.com.geodrone.ui.login;

import android.content.Intent;

import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.geodrone.R;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.cliente.cadastrar.CadastroClienteActivity;
import br.com.geodrone.ui.helper.ActivityHelper;
import br.com.geodrone.ui.helper.GenericProgress;
import br.com.geodrone.ui.sincronizacao.SincronizacaoActivity;
import br.com.geodrone.ui.main.MainActivity;
import br.com.geodrone.utils.KeyboardUtils;
import br.com.geodrone.utils.Messenger;
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
    @BindView(R.id.text_view_cadastrar_cliente)
    TextView textViewCadastrarCliente;

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

    @OnClick(R.id.text_view_cadastrar_cliente)
    public void onClickNovoUsuario() {
        Intent i = new Intent(LoginActivity.this ,CadastroClienteActivity.class);
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

        showMessage(message);
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
    public void onDispositivoInstaladoSucesso(String message) {
        hideLoading();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        PreferencesUtils.putString(getApplicationContext(), PreferencesUtils.CHAVE_EMAIL_USUARIO, editTextEmail.getText().toString());
        PreferencesUtils.putString(getApplicationContext(), PreferencesUtils.CHAVE_SENHA_USUARIO, editTextSenha.getText().toString());
        Intent intent = new Intent(this, SincronizacaoActivity.class);
        Bundle b = new Bundle();
        b.putString(br.com.geodrone.activity.utils.Constantes.CHAVE_UI_ORIGEM, br.com.geodrone.activity.utils.Constantes.ACTIVITY_LOGIN); //Your id
        intent.putExtras(b); //Put your id to your next Intent
        startActivity(intent);
        finish();
    }

    @Override
    public void onDispositivoInstaladoError(String message) {
        hideLoading();
        onError(message);
    }

    @Override
    public void onDispositivoInstaladoError(Messenger messenger) {
        hideLoading();
        showMessenger(messenger);
    }


    @Override
    public void showLoading() {
        mProgress.show();
    }

    @Override
    public void hideLoading() {
        mProgress.hide();
    }

    @OnClick(R.id.text_view_esqueci_minha_senha)
    public void onClickEsqueciMinhaSenha() {
        try{
            ActivityHelper activityHelper = new ActivityHelper();
            activityHelper.esqueciMinhaSenha(this);
        }catch (Exception ex){
            showMessage(ex.toString());
        }
    }

}