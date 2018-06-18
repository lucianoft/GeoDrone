package br.com.geodrone.ui.previsaotempo.consulta;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.resource.PrevisaoTempoArqResource;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.base.BaseRelatorioActivity;
import br.com.geodrone.ui.helper.GenericProgress;
import br.com.geodrone.utils.Messenger;
import br.com.geodrone.utils.Util;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PrevisaoTempoConsultaActivity extends BaseRelatorioActivity implements PrevisaoTempoConsultaPresenter.View{

    private GenericProgress mProgress;
    private PrevisaoTempoConsultaPresenter previsaoTempoConsultaPresenter;
    private PrevisaoTempoConsultaAdapter previsaoTempoConsultaAdapter;

    @BindView(R.id.recyclerViewPrevisaoTempo)
    RecyclerView recyclerViewPrevisaoTempo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previsao_tempo_consulta);
        ButterKnife.bind(this);

        if (!Util.verificaConexao(this)) {
            Util.initToast(this, getString(R.string.msg_sem_conexao_internet));
            finish();
        } else {

            mProgress = new GenericProgress(this);
            previsaoTempoConsultaPresenter = new PrevisaoTempoConsultaPresenter(this);
            showLoading();

        }
    }



    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        previsaoTempoConsultaPresenter.takeView(this);
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
    public void onListPrevisaoTempo(List<PrevisaoTempoArqResource> previsaoTempoArqResources) {
        previsaoTempoConsultaAdapter.clearData();

        int i = 0;
        if (previsaoTempoArqResources != null) {
            for (PrevisaoTempoArqResource previsaoTempoArqResource : previsaoTempoArqResources) {
                ++i;
                previsaoTempoConsultaAdapter.addData(previsaoTempoArqResource);
            }
        }
        previsaoTempoConsultaAdapter.notifyDataSetChanged();
        hideLoading();
    }

    @Override
    public void onError(Messenger messenger) {
        showMessenger(messenger);
        hideLoading();
    }

    private void initAdapter() {
        recyclerViewPrevisaoTempo.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewPrevisaoTempo.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        previsaoTempoConsultaAdapter = new PrevisaoTempoConsultaAdapter(this);

        recyclerViewPrevisaoTempo.setAdapter(previsaoTempoConsultaAdapter);
        previsaoTempoConsultaPresenter.findPrevisaoTempoCliente();
    }

    @Override
    public void onClickPrevisaoTempoArqResource(PrevisaoTempoArqResource previsaoTempoArqResource) {
        try{
            showLoading();
            previsaoTempoConsultaPresenter.gerarRelatorio(previsaoTempoArqResource);
        }catch (Exception ex){
            onError(ex);
            ex.printStackTrace();

        }
    }
}
