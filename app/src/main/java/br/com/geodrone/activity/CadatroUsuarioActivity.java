package br.com.geodrone.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import br.com.geodrone.R;
import br.com.geodrone.activity.utils.Mask;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CadatroUsuarioActivity extends AppCompatActivity {


    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.input_telefone) EditText edTelefone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadatro_usuario);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão

        edTelefone.addTextChangedListener(Mask.insert("(##)#####-####", edTelefone));

    }

    @Override
    public void onBackPressed() {
       super.onBackPressed();
    }
}
