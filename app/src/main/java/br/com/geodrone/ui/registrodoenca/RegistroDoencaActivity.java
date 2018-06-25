package br.com.geodrone.ui.registrodoenca;

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
import br.com.geodrone.model.Doenca;
import br.com.geodrone.model.EstagioInfestacao;
import br.com.geodrone.model.TipoCultivo;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.estagioinfestacao.EstagioInfestacaoAdapter;
import br.com.geodrone.ui.helper.GenericProgress;
import br.com.geodrone.utils.NumberUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;

public class RegistroDoencaActivity extends BaseActivity implements RegistroDoencaPresenter.View{

    @BindView(R.id.spinner_tipo_doenca) Spinner spiTipoDoenca;
    @BindView(R.id.edit_text_obs_doenca) EditText editTextObservacao;
    @BindView(R.id.edit_text_qtde_doenca) EditText editTextQtdeDoencas;
    @BindView(R.id.btn_salvar_doenca) Button buttonSalvar;
    @BindView(R.id.autoCompleteDoenca)
    AutoCompleteTextView autoCompleteDoenca;

    @BindView(R.id.autoCompleteEstagioInfestacao)
    AutoCompleteTextView autoCompleteEstagioInfestacao;


    private Location location;
    private NumberUtils numberUtils = new NumberUtils();

    private RegistroDoencaPresenter registroDoencaPresenter;
    private RegistroDoencaAdapter doencaAdapter;
    private EstagioInfestacaoAdapter estagioInfestacaoAdapter;


    private List<Doenca> doencaList = new ArrayList<>();
    private List<Doenca> doencaListFilter = new ArrayList<>();

    private List<TipoCultivo> tipoCultivoList = new ArrayList<>();
    private TipoCultivo tipoCultivo = null;
    private Doenca doenca = null;

    private EstagioInfestacao estagioInfestacao = null;

    private List<EstagioInfestacao> estagioInfestacaoList = new ArrayList<>();

    private GenericProgress mProgress;
    Long idTalhao = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_doenca);
        ButterKnife.bind(this);

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        //getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão

        mProgress = new GenericProgress(this);

        Intent it = getIntent();
        location = it.getParcelableExtra("localizacao");
        idTalhao = it.getLongExtra("idTalhao", -1);
        if (idTalhao == -1){
            idTalhao = null;
        }

        registroDoencaPresenter = new RegistroDoencaPresenter(this);

        initComponentes();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        registroDoencaPresenter.takeView(this);
    }

    private void initComponentes() {
        tipoCultivoList = registroDoencaPresenter.findAllTipoCultivo();
        doencaList = registroDoencaPresenter.findAllDoenca();
        estagioInfestacaoList = registroDoencaPresenter.findAllEstagioInfestacao();


        ArrayAdapter<TipoCultivo> myAdapter = new ArrayAdapter<TipoCultivo>(this, android.R.layout.simple_spinner_item, tipoCultivoList);
        spiTipoDoenca.setAdapter(myAdapter);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        autoCompleteDoenca.setThreshold(1);
        doencaAdapter = new RegistroDoencaAdapter(this, R.layout.activity_registro_doenca, R.id.textViewDoencaDescricao, doencaListFilter);
        autoCompleteDoenca.setAdapter(doencaAdapter);
        doencaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        autoCompleteDoenca.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int position, long id) {
                Object item = parent.getItemAtPosition(0);
                if (item instanceof Doenca){
                    doenca =(Doenca) item;
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

    @OnItemSelected(R.id.spinner_tipo_doenca)
    public void onChanceTipoCultivo(int position) {
        tipoCultivo = (TipoCultivo) tipoCultivoList.get(position);
        onSelectedTipoCultivo(tipoCultivo);
    }

    @OnClick(R.id.btn_salvar_doenca)
    public void salvar() {
        try{
            showLoading();
            registroDoencaPresenter.salvar(doenca,
                                           idTalhao,
                                           estagioInfestacao,
                                           editTextObservacao.getText().toString(),
                                           location != null ? location.getLatitude() : null,
                                           location != null ? location.getLongitude() : null,
                                           editTextQtdeDoencas.getText().toString());
        }catch (Exception ex){
            hideLoading();
            onError(ex);
        }
    }

    @Override
    public void onSelectedTipoCultivo(TipoCultivo tipoCultivo) {
        doencaListFilter = new ArrayList<>();
        if (doencaList != null){
            for (Doenca doenca : doencaList){
                if (doenca.getIdTipoCultivo() == null ||
                        doenca.getIdTipoCultivo().equals(tipoCultivo.getId())){
                    doencaListFilter.add(doenca);
                }
            }
        }
        autoCompleteDoenca.setText("");
        doencaAdapter = new RegistroDoencaAdapter(RegistroDoencaActivity.this, R.layout.activity_main, R.id.textViewDoencaDescricao, doencaListFilter);
        autoCompleteDoenca.setAdapter(doencaAdapter);

    }

    @Override
    public void onErrorQtde(String message) {
        hideLoading();
        editTextQtdeDoencas.setError(message);
    }

    @Override
    public void onErrorDoenca(String message) {
        hideLoading();
        autoCompleteDoenca.setError(message);
    }

    @Override
    public void onRegitroDoencaSucesso(String message) {
        showMessage(message);
        hideLoading();
        finish();
    }

    @Override
    public void onErrorRegitroDoenca(String message) {
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
