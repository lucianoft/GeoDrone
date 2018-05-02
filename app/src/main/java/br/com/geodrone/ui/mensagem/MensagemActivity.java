package br.com.geodrone.ui.mensagem;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import br.com.geodrone.R;
import br.com.geodrone.ui.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MensagemActivity extends BaseActivity implements  MensagemPresenter.View {

    @BindView(R.id.list_of_messages)  RecyclerView mRecyclerView;
    @BindView(R.id.edit_mensagem)  EditText mMessageEditText;
    @BindView(R.id.fab_mensagem)  FloatingActionButton mMessageSendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensagem);
        ButterKnife.bind(this);

        mMessageSendButton.setEnabled(false);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
