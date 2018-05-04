package br.com.geodrone.ui.registrocondicaotempo;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.model.constantes.FlagDirecao;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.helper.GenericProgress;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;

public class RegistroCondicoesTempoActivity extends BaseActivity implements RegistroCondicaoTempoPresenter.View{

    private List<RegistroCondicaoTempoRecyclerViewItem> carItemList = null;

    @BindView(R.id.spinner_reg_codicoes_tempo_direcao)
    Spinner spinnerDirecao;

    private RegistroCondicaoTempoPresenter registroCondicaoTempoPresenter;
    private String flagDirecao = null;

    private GenericProgress mProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_condicoes_tempo);
        ButterKnife.bind(this);

        mProgress = new GenericProgress(this);

        registroCondicaoTempoPresenter= new RegistroCondicaoTempoPresenter(this);
        carItemList = carItemList = registroCondicaoTempoPresenter.getRegistroCondicaoTempoRecyclerViewItem();

        ArrayAdapter<FlagDirecao> spinnerDirecaoAdapter = new ArrayAdapter<FlagDirecao>(this, android.R.layout.simple_spinner_item, FlagDirecao.values());
        spinnerDirecao.setAdapter(spinnerDirecaoAdapter);
        spinnerDirecaoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Create the recyclerview.
        RecyclerView carRecyclerView = (RecyclerView)findViewById(R.id.card_view_recycler_list);
        // Create the grid layout manager with 2 columns.
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        // Set layout manager.
        carRecyclerView.setLayoutManager(gridLayoutManager);

        // Create car recycler view data adapter with car item list.
        RegistroCondicaoTempoRecyclerViewDataAdapter carDataAdapter = new RegistroCondicaoTempoRecyclerViewDataAdapter(carItemList);
        // Set data adapter.
        carRecyclerView.setAdapter(carDataAdapter);

        carRecyclerView.addOnItemTouchListener(new RecyclerItemListener(getApplicationContext(), carRecyclerView,
                new RecyclerItemListener.RecyclerTouchListener() {
                    public void onClickItem(View v, int position) {
                        showLoading();
                        registroCondicaoTempoPresenter.salvar(flagDirecao , position);
                    }

                    public void onLongClickItem(View v, int position) {
                        showLoading();
                        registroCondicaoTempoPresenter.salvar(flagDirecao , position);
                    }
                }));
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        registroCondicaoTempoPresenter.takeView(this);
        hideLoading();
    }

    @Override
    public void showLoading() {
        mProgress.show();
    }

    @Override
    public void hideLoading() {
        mProgress.hide();
    }

    @OnItemSelected(R.id.spinner_reg_codicoes_tempo_direcao)
    void onItemSelected(int position) {
        flagDirecao = FlagDirecao.getValueByIndice(position);
    }

    @Override
    public void onRegistroCondicaoTempoSucesso(String message) {
        showMessage(message);
        hideLoading();
        finish();
    }

    @Override
    public void onRegistroCondicaoTempoErro(String message) {
        showMessage(message);
        hideLoading();
    }
}
