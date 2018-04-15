package br.com.geodrone.ui.sincronizacao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.geodrone.R;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.helper.GenericProgress;
import butterknife.ButterKnife;

public class SincronizacaoActivity extends BaseActivity implements SincronizacaoPresenter.View {

    private GenericProgress mProgress;

    private SincronizacaoPresenter sincronizacaoPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sincronizacao);
        ButterKnife.bind(this);
        mProgress = new GenericProgress(this);
        sincronizacaoPresenter = new SincronizacaoPresenter(this);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        sincronizacaoPresenter.takeView(this);
        sincronizacaoPresenter.getAtualizacoes();
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
    public void onAtualizacaoSucesso(String msg) {
        hideLoading();
        showMessage(msg);
    }

    @Override
    public void onAtualizacaoError(String msg) {
        hideLoading();
        onError(msg);
    }
}
