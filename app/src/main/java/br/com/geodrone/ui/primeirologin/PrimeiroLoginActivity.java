package br.com.geodrone.ui.primeirologin;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.geodrone.R;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.helper.GenericProgress;
import br.com.geodrone.ui.sincronizacao.SincronizacaoActivity;
import br.com.geodrone.utils.Constantes;
import br.com.geodrone.utils.PreferencesUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PrimeiroLoginActivity extends BaseActivity implements PrimeiroLoginPresenter.View{

    @BindView(R.id.edit_text_url_pri_login)
    EditText editTextUrl;

    @BindView(R.id.edit_text_email_pri_login)
    EditText editTextEmail;
    @BindView(R.id.edit_text_senha_pri_login) EditText editTextSenha;
    @BindView(R.id.button_pri_olho)
    Button buttonOlho;

    private PrimeiroLoginPresenter primeiroLoginPresenter;
    private GenericProgress mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primeiro_login);
        ButterKnife.bind(this);
        mProgress = new GenericProgress(this);

        primeiroLoginPresenter = new PrimeiroLoginPresenter(this);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        editTextUrl.setText(Constantes.API_LOGIN_URL);
        primeiroLoginPresenter.takeView(this);
    }

    @OnClick(R.id.button_pri_olho)
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
    @OnClick(R.id.button_pri_login)
    public void onClickLogin() {
        showLoading();
        primeiroLoginPresenter.login(editTextUrl.getText().toString(), editTextEmail.getText().toString(), editTextSenha.getText().toString());
    }

    @Override
    public void onSuccessoLogin(String message) {
        hideLoading();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        PreferencesUtils.putString(getApplicationContext(), PreferencesUtils.CHAVE_EMAIL_USUARIO, editTextEmail.getText().toString());
        PreferencesUtils.putString(getApplicationContext(), PreferencesUtils.CHAVE_SENHA_USUARIO, editTextSenha.getText().toString());
        Intent i = new Intent(this, SincronizacaoActivity.class);
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

    }

    @Override
    public void onDispositivoInstaladoError(String message) {
        hideLoading();
        onError(message);
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
