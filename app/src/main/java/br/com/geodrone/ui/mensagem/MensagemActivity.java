package br.com.geodrone.ui.mensagem;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.SessionGeooDrone;
import br.com.geodrone.activity.utils.Constantes;
import br.com.geodrone.model.Usuario;
import br.com.geodrone.resource.MensagemResource;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.helper.GenericProgress;
import br.com.geodrone.utils.Messenger;
import br.com.geodrone.utils.Util;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MensagemActivity extends BaseActivity implements  MensagemPresenter.View {



    @BindView(R.id.messagesRecyclerView)  RecyclerView mRecyclerView;
    @BindView(R.id.editTextMessage)
    EditText mMessageEditText;

    @BindView(R.id.contentRoot)
    View contentRoot;

    /*@BindView(R.id.buttonEmoji)
    ImageView btEmoji;
    EmojIconActions emojIcon;
*/
    private GenericProgress mProgress;
    private MensagemPresenter mensagemPresenter;
    private MensagemAdapter mensagemAdapter;

    Long idUsuario = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensagem);
        ButterKnife.bind(this);

        if (!Util.verificaConexao(this)){
            Util.initToast(this,"Você não tem conexão com internet");
            finish();
        }
        mProgress = new GenericProgress(this);

        Bundle b = getIntent().getExtras();
        if (b != null) {
            idUsuario = b.getLong(Constantes.CHAVE_ID_USUARIO);
        }
        if (idUsuario == null){
            Usuario usuario = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_USUARIO);
            idUsuario = usuario.getId();
        }
        mensagemPresenter = new MensagemPresenter(this, idUsuario);

        /*emojIcon = new EmojIconActions(this,contentRoot,mMessageEditText,btEmoji);
        emojIcon.ShowEmojIcon();*/

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mensagemPresenter.takeView(this);
        initAdapter();
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
    @OnClick(R.id.buttonMessage)
    public void onClickMensagem() {
        try {
            if (mMessageEditText.getText().toString() != null &&
                    mMessageEditText.getText().toString().trim() != "") {
                showLoading();
                mensagemPresenter.salvar(mMessageEditText.getText().toString().trim());
            }
        }catch (Exception ex){
            onMensagemErro(ex.toString());
        }
    }

    @Override
    public void insert(String msg) {

    }

    @Override
    public void onMensagemSucesso(String msg) {
        showMessage(msg);
        mMessageEditText.setText("");

        mensagemPresenter.findMensagensByUsuario(idUsuario);

        hideLoading();
    }

    @Override
    public void onMensagemErro(String msg) {
        showMessage(msg);
        hideLoading();

    }

    @Override
    public void onMensagemErro(Messenger messenger) {
        showMessenger(messenger);
        hideLoading();
    }

    @Override
    public void onListMessage(List<MensagemResource> mensagemResources) {
        mensagemAdapter.clearData();
          int i = 0;
        if (mensagemResources != null) {
            for (MensagemResource mensagemResource : mensagemResources) {
                ++i;
                mensagemAdapter.addData(mensagemResource);
            }
        }

        mensagemAdapter.notifyDataSetChanged();
        mRecyclerView.scrollToPosition(i);
    }

    private void initAdapter() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mensagemAdapter = new MensagemAdapter(this, idUsuario);
        mRecyclerView.setAdapter(mensagemAdapter);
        mensagemPresenter.findMensagensByUsuario(idUsuario);
    }

}
