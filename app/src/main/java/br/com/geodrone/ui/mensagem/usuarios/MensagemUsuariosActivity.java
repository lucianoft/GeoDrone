package br.com.geodrone.ui.mensagem.usuarios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;

import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.activity.utils.Constantes;
import br.com.geodrone.resource.UsuarioMensagemResource;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.forum.ChatMessage;
import br.com.geodrone.ui.forum.adapter.ForumAdapter;
import br.com.geodrone.ui.helper.GenericProgress;
import br.com.geodrone.ui.mensagem.MensagemActivity;
import br.com.geodrone.ui.sincronizacao.SincronizacaoActivity;
import br.com.geodrone.utils.Messenger;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MensagemUsuariosActivity extends BaseActivity implements MensagemUsuariosPresenter.View{

    private MensagemUsuariosPresenter mensagemUsuariosPresenter;
    private UsuariosMensagemAdapter usuariosMensagemAdapter;
    private GenericProgress mProgress;

    @BindView(R.id.recyclerViewUsuariosMensagem)
    RecyclerView recyclerViewUsuarios;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensagem_usuarios);
        ButterKnife.bind(this);

        mProgress = new GenericProgress(this);
        mensagemUsuariosPresenter =  new MensagemUsuariosPresenter(this);
        showLoading();

        recyclerViewUsuarios.setLayoutManager(new LinearLayoutManager(this));

        usuariosMensagemAdapter = new UsuariosMensagemAdapter();
        usuariosMensagemAdapter.setActivity(this);
        recyclerViewUsuarios.setAdapter(usuariosMensagemAdapter);
        // Configurando um dividr entre linhas, para uma melhor visualização.
        recyclerViewUsuarios.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mensagemUsuariosPresenter.takeView(this);
        mensagemUsuariosPresenter.findUsuariosMensagem();
    }

    @Override
    public void showLoading() {
        mProgress.show();
    }

    @Override
    public void hideLoading() {
        mProgress.hide();
    }

    @Override
    public void onClickUsuario(UsuarioMensagemResource usuarioMensagemResource) {

        Intent intent = new Intent(this, MensagemActivity.class);
        Bundle b = new Bundle();
        b.putLong(Constantes.CHAVE_ID_USUARIO, usuarioMensagemResource.getId()); //Your id
        intent.putExtras(b); //Put your id to your next Intent
        startActivity(intent);
    }

    @Override
    public void onSucessoFindUsuarios(List<UsuarioMensagemResource> usuarioMensagemResources) {
        usuariosMensagemAdapter.clearData();

        int i = 0;
        if (usuarioMensagemResources != null) {
            for (UsuarioMensagemResource usuarioMensagemResource : usuarioMensagemResources) {
                ++i;
                usuariosMensagemAdapter.addData(usuarioMensagemResource);
            }
        }
        usuariosMensagemAdapter.notifyDataSetChanged();
        hideLoading();
    }

    @Override
    public void onErroFindUsuarios(String msg) {
        showMessage(msg);
    }

    @Override
    public void onErroFindUsuarios(Messenger messenger) {
        showMessenger(messenger);
    }
}
