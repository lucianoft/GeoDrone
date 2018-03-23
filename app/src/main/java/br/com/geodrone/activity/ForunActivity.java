package br.com.geodrone.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import br.com.geodrone.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ForunActivity extends AppCompatActivity {

    @BindView(R.id.list_mensagem_forum)
    RecyclerView mRecyclerView;
    @BindView(R.id.edit_mensagem_forum)
    EditText mMessageEditText;
    @BindView(R.id.fab_mensagem_forun)
    FloatingActionButton mMessageSendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forun);

        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão

        mMessageSendButton.setEnabled(false);

    }
}
