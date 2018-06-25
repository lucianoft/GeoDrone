package br.com.geodrone.ui.registropraga;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.model.EstagioInfestacao;
import br.com.geodrone.model.Praga;
import br.com.geodrone.model.TipoCultivo;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.estagioinfestacao.EstagioInfestacaoAdapter;
import br.com.geodrone.ui.helper.GenericProgress;
import br.com.geodrone.utils.NumberUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class RegistroPragaActivity extends BaseActivity implements RegistroPragaPresenter.View{

    @BindView(R.id.spinner_tipo_cultivo_praga) Spinner spiTipoCultivoPraga;
    @BindView(R.id.edit_text_obs_praga) EditText editTextObservacao;
    @BindView(R.id.edit_text_qtde_praga) EditText editTextQtdePragas;
    @BindView(R.id.btn_salvar_praga) Button buttonSalvar;
    @BindView(R.id.autoCompletePraga)
    AutoCompleteTextView autoCompletePraga;

    @BindView(R.id.autoCompleteEstagioInfestacao)
    AutoCompleteTextView autoCompleteEstagioInfestacao;

    private Location location;
    private NumberUtils numberUtils = new NumberUtils();

    private RegistroPragaPresenter registroPragaPresenter;
    private RegistroPragaAdapter pragaAdapter;
    private EstagioInfestacaoAdapter estagioInfestacaoAdapter;

    private List<Praga> pragaList = new ArrayList<>();
    private List<Praga> pragaListFilter = new ArrayList<>();

    private List<EstagioInfestacao> estagioInfestacaoList = new ArrayList<>();

    private List<TipoCultivo> tipoCultivoList = new ArrayList<>();
    private TipoCultivo tipoCultivo = null;
    private Praga praga = null;
    Long idTalhao = null;
    private EstagioInfestacao estagioInfestacao = null;


    private GenericProgress mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_praga);
        ButterKnife.bind(this);
        mProgress = new GenericProgress(this);

        Intent it = getIntent();
        location = it.getParcelableExtra("localizacao");
        idTalhao = it.getLongExtra("idTalhao", -1);
        if (idTalhao == -1){
            idTalhao = null;
        }
        registroPragaPresenter = new RegistroPragaPresenter(this);

        initComponentes();
    }

    private void initComponentes() {
        tipoCultivoList = registroPragaPresenter.findAllTipoCultivo();
        pragaList = registroPragaPresenter.findAllPraga();
        estagioInfestacaoList = registroPragaPresenter.findAllEstagioInfestacao();

        ArrayAdapter<TipoCultivo> myAdapter = new ArrayAdapter<TipoCultivo>(this, android.R.layout.simple_spinner_item, tipoCultivoList);
        spiTipoCultivoPraga.setAdapter(myAdapter);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        autoCompletePraga.setThreshold(1);
        pragaAdapter = new RegistroPragaAdapter(this, R.layout.activity_registro_praga, R.id.textViewPragaDescricao, pragaListFilter);
        autoCompletePraga.setAdapter(pragaAdapter);
        pragaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        autoCompletePraga.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int position, long id) {
                Object item = parent.getItemAtPosition(0);
                if (item instanceof Praga){
                    praga =(Praga) item;
                }
            }

        });

        autoCompleteEstagioInfestacao.setThreshold(1);
        estagioInfestacaoAdapter = new EstagioInfestacaoAdapter(this, R.layout.activity_registro_praga, R.id.textViewEstacaoInfestacaoDescricao, estagioInfestacaoList);
        autoCompleteEstagioInfestacao.setAdapter(estagioInfestacaoAdapter);
        estagioInfestacaoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        autoCompleteEstagioInfestacao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int position, long id) {
                Object item = parent.getItemAtPosition(0);
                if (item instanceof EstagioInfestacao){
                    estagioInfestacao =(EstagioInfestacao) item;
                }
            }

        });
        
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        registroPragaPresenter.takeView(this);
    }

    @OnItemSelected(R.id.spinner_tipo_cultivo_praga)
    public void onChanceTipoCultivo(int position) {
        tipoCultivo = (TipoCultivo) tipoCultivoList.get(position);
        onSelectedTipoCultivo(tipoCultivo);
    }

    @OnClick(R.id.btn_salvar_praga)
    public void salvar() {
        try {
            showLoading();
            registroPragaPresenter.salvar(praga,
                    idTalhao,
                    estagioInfestacao,
                    editTextObservacao.getText().toString(),
                    location != null ? location.getLatitude() : null,
                    location != null ? location.getLongitude() : null,
                    editTextQtdePragas.getText().toString());
        }catch (Exception ex){
            hideLoading();
            onError(ex);
        }
    }

    @Override
    public void onSelectedTipoCultivo(TipoCultivo tipoCultivo) {
        pragaListFilter = new ArrayList<>();
        if (pragaList != null){
            for (Praga praga : pragaList){
                if (praga.getIdTipoCultivo() == null ||
                        praga.getIdTipoCultivo().equals(tipoCultivo.getId())){
                    pragaListFilter.add(praga);
                }
            }
        }
        autoCompletePraga.setText("");
        pragaAdapter = new RegistroPragaAdapter(RegistroPragaActivity.this, R.layout.activity_main, R.id.textViewPragaDescricao, pragaListFilter);
        autoCompletePraga.setAdapter(pragaAdapter);

    }

    @Override
    public void onErrorQtde(String message) {
        hideLoading();
        editTextQtdePragas.setError(message);
    }

    @Override
    public void onErrorPraga(String message) {
       hideLoading();
        autoCompletePraga.setError(message);
    }

    @Override
    public void onRegitroPragaSucesso(String message) {
        showMessage(message);
        finish();
    }

    @Override
    public void onErrorRegitroPraga(String message) {
        hideLoading();
        onError(message);
    }

    @Override
    public void showLoading() {
        mProgress.show();
    }

    @Override
    public void hideLoading() {
        mProgress.hide();
    }
}
