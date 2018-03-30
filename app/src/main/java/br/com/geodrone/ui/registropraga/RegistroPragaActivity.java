package br.com.geodrone.ui.registropraga;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.model.Praga;
import br.com.geodrone.model.TipoCultivo;
import br.com.geodrone.utils.NumberUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import butterknife.OnItemSelected;

public class RegistroPragaActivity extends AppCompatActivity implements RegistroPragaPresenter.View{

    @BindView(R.id.spinner_tipo_praga) Spinner spiTipoPraga;
    @BindView(R.id.edit_text_obs_praga) EditText editTextObservacao;
    @BindView(R.id.edit_text_qtde_praga) EditText editTextQtdePragas;
    @BindView(R.id.btn_salvar_praga) Button buttonSalvar;
    @BindView(R.id.autoCompletePraga) AutoCompleteTextView autoCompletePraga;

    private Location location;
    private NumberUtils numberUtils = new NumberUtils();

    private RegistroPragaPresenter registroPragaPresenter;
    private RegistroPragaAdapter pragaAdapter;

    private List<Praga> pragaList = new ArrayList<>();
    private List<Praga> pragaListFilter = new ArrayList<>();

    private List<TipoCultivo> tipoCultivoList = new ArrayList<>();
    private TipoCultivo tipoCultivo = null;
    private Praga praga = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_praga);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão

        Intent it = getIntent();
        location = it.getParcelableExtra("localizacao");
        registroPragaPresenter = new RegistroPragaPresenter(this);

        initComponentes();
    }

    private void initComponentes() {
        tipoCultivoList = registroPragaPresenter.findAllTipoCultivo();
        pragaList = registroPragaPresenter.findAllPraga();

        ArrayAdapter<TipoCultivo> myAdapter = new ArrayAdapter<TipoCultivo>(this, android.R.layout.simple_spinner_item, tipoCultivoList);
        spiTipoPraga.setAdapter(myAdapter);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        autoCompletePraga.setThreshold(1);
        pragaAdapter = new RegistroPragaAdapter(this, R.layout.activity_registro_praga, R.id.textViewPragaNomeComum, pragaListFilter);
        autoCompletePraga.setAdapter(pragaAdapter);
        pragaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        registroPragaPresenter.takeView(this);
        onInvisibleProgressBar();
    }

    @OnItemSelected(R.id.spinner_tipo_praga)
    public void onChanceTipoCultivo(int position) {
        tipoCultivo = (TipoCultivo) tipoCultivoList.get(position);
        onSelectedTipoPraga(tipoCultivo);
    }

    //@OnAutoCompleteItemClick            (R.id.autoCompletePraga)
    public void onChancePraga(int position) {
    }

    @Override
    public void onVisibleProgressBar() {

    }

    @Override
    public void onInvisibleProgressBar() {

    }

    @Override
    public void onSelectedTipoPraga(TipoCultivo tipoCultivo) {
        pragaListFilter = new ArrayList<>();
        if (pragaList != null){
            for (Praga praga : pragaList){
                if (praga.getIdTipoCultivoRef() == null ||
                        praga.getIdTipoCultivoRef().equals(tipoCultivo.getIdTipoCultivoRef())){
                    pragaListFilter.add(praga);
                }
            }
        }
        autoCompletePraga.setText("");
        pragaAdapter = new RegistroPragaAdapter(RegistroPragaActivity.this, R.layout.activity_main, R.id.textViewPragaNomeComum, pragaListFilter);
        autoCompletePraga.setAdapter(pragaAdapter);

    }

}
