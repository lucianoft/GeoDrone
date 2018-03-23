package br.com.geodrone.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import br.com.geodrone.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MensagemActivity extends AppCompatActivity {

    @BindView(R.id.list_of_messages)  RecyclerView mRecyclerView;
    @BindView(R.id.edit_mensagem)  EditText mMessageEditText;
    @BindView(R.id.fab_mensagem)  FloatingActionButton mMessageSendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensagem);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão

        mMessageSendButton.setEnabled(false);
    }
}
