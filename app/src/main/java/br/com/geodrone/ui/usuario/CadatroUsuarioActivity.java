package br.com.geodrone.ui.usuario;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import br.com.geodrone.R;
import br.com.geodrone.activity.utils.Mask;
import br.com.geodrone.ui.login.LoginPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CadatroUsuarioActivity extends AppCompatActivity implements UsuarioPresenter.View{

    private UsuarioPresenter usuarioPresenter;

    @BindView(R.id.input_nome_usuario) EditText editTextNome;
    @BindView(R.id.input_sobrenome_usuario) EditText editTextSobreNome;
    @BindView(R.id.input_telefone_usuario) EditText editTextTelefone;
    @BindView(R.id.input_email_usuario) EditText edEmail;
    @BindView(R.id.input_senha_usuario) EditText edSenha;
    @BindView(R.id.input_confirmar_senha_usuario) EditText edConfirmarSenha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadatro_usuario);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão

        usuarioPresenter = new UsuarioPresenter(this);
        editTextTelefone.addTextChangedListener(Mask.insert("(##)#####-####", editTextTelefone));

    }
    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        usuarioPresenter.takeView(this);
        onInvisibleProgressBar();
    }


    @Override
    public void onClickCadastro() {
        usuarioPresenter.salvar(editTextNome.getText().toString(),
                editTextSobreNome.getText().toString(),
                editTextTelefone.getText().toString(),
                edEmail.getText().toString(),
                edSenha.getText().toString(),
                edConfirmarSenha.getText().toString());
    }

    @Override
    public void onCadastroErro() {

    }

    @Override
    public void onCadastroSucesso() {

    }

    @Override
    public void onNomeObrigatorio() {

    }

    @Override
    public void onTelefoneObrigatorio() {

    }

    @Override
    public void onEmailObrigatorio() {

    }

    @Override
    public void onSenhaObrigatorio() {

    }

    @Override
    public void onConfirmSenhaObrigatorio() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onVisibleProgressBar() {

    }

    @Override
    public void onInvisibleProgressBar() {

    }

}
