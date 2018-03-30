package br.com.geodrone.ui.registrodoenca;

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
import br.com.geodrone.model.Doenca;
import br.com.geodrone.model.TipoCultivo;
import br.com.geodrone.utils.NumberUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;

public class RegistroDoencaActivity extends AppCompatActivity implements RegistroDoencaPresenter.View{

    @BindView(R.id.spinner_tipo_doenca) Spinner spiTipoDoenca;
    //@BindView(R.id.spinner_doenca) Spinner spiDoenca;
    @BindView(R.id.edit_text_obs_doenca) EditText editTextObservacao;
    @BindView(R.id.edit_text_qtde_doenca) EditText editTextQtdeDoencas;
    @BindView(R.id.btn_salvar_doenca) Button buttonSalvar;
    @BindView(R.id.autoCompleteDoenca)
    AutoCompleteTextView autoCompleteDoenca;

    private Location location;
    private NumberUtils numberUtils = new NumberUtils();

    private RegistroDoencaPresenter registroDoencaPresenter;
    private RegistroDoencaAdapter doencaAdapter;

    private List<Doenca> doencaList = new ArrayList<>();
    private List<Doenca> doencaListFilter = new ArrayList<>();

    private List<TipoCultivo> tipoCultivoList = new ArrayList<>();
    private TipoCultivo tipoCultivo = null;
    private Doenca doenca = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_doenca);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão

        Intent it = getIntent();
        location = it.getParcelableExtra("localizacao");
        registroDoencaPresenter = new RegistroDoencaPresenter(this);

        initComponentes();
    }

    private void initComponentes() {
        tipoCultivoList = registroDoencaPresenter.findAllTipoCultivo();
        doencaList = registroDoencaPresenter.findAllDoenca();

        ArrayAdapter<TipoCultivo> myAdapter = new ArrayAdapter<TipoCultivo>(this, android.R.layout.simple_spinner_item, tipoCultivoList);
        spiTipoDoenca.setAdapter(myAdapter);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        autoCompleteDoenca.setThreshold(1);
        doencaAdapter = new RegistroDoencaAdapter(this, R.layout.activity_registro_doenca, R.id.textViewPraDoencaDescricao, doencaListFilter);
        autoCompleteDoenca.setAdapter(doencaAdapter);
        doencaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        registroDoencaPresenter.takeView(this);
        onInvisibleProgressBar();
    }

    @OnItemSelected(R.id.spinner_tipo_doenca)
    public void onChanceTipoCultivo(int position) {
        tipoCultivo = (TipoCultivo) tipoCultivoList.get(position);
        onSelectedTipoCultivo(tipoCultivo);
    }

    @Override
    public void onVisibleProgressBar() {

    }

    @Override
    public void onInvisibleProgressBar() {

    }

    @Override
    public void onSelectedTipoCultivo(TipoCultivo tipoCultivo) {
        doencaListFilter = new ArrayList<>();
        if (doencaList != null){
            for (Doenca doenca : doencaList){
                if (doenca.getIdTipoCultivoRef() == null ||
                        doenca.getIdTipoCultivoRef().equals(tipoCultivo.getIdTipoCultivoRef())){
                    doencaListFilter.add(doenca);
                }
            }
        }
        autoCompleteDoenca.setText("");
        doencaAdapter = new RegistroDoencaAdapter(RegistroDoencaActivity.this, R.layout.activity_main, R.id.textViewPraDoencaDescricao, doencaListFilter);
        autoCompleteDoenca.setAdapter(doencaAdapter);

    }

}
