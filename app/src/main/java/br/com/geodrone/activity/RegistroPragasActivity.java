package br.com.geodrone.activity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.dto.PragaDto;
import br.com.geodrone.model.Praga;
import br.com.geodrone.model.TipoCultivo;
import br.com.geodrone.presenter.PragaPresenter;
import br.com.geodrone.utils.NumberUtils;
import br.com.geodrone.view.adapter.PragaAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.satsuware.usefulviews.LabelledSpinner;

public class RegistroPragasActivity extends AppCompatActivity {

    @BindView(R.id.spinner_tipo_praga) Spinner spiTipoPraga;
    //@BindView(R.id.spinner_praga) Spinner spiPraga;
    @BindView(R.id.edit_text_obs_praga) EditText editTextObservacao;
    @BindView(R.id.edit_text_qtde_praga) EditText editTextQtdePragas;
    @BindView(R.id.btn_salvar_praga) Button buttonSalvar;
    @BindView(R.id.autoCompletePraga)
    AutoCompleteTextView autoCompletePraga;

    private Location location;
    private NumberUtils numberUtils = new NumberUtils();

    private PragaPresenter pragaPresenter;
    private PragaAdapter pragaAdapter;

    private List<Praga> pragaList = new ArrayList<>();
    private List<Praga> pragaListFilter = new ArrayList<>();

    private List<TipoCultivo> tipoCultivoList = new ArrayList<>();
    private TipoCultivo tipoCultivo = null;
    private Praga praga = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_pragas);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão

        Intent it = getIntent();
        location = it.getParcelableExtra("localizacao");

        pragaPresenter = new PragaPresenter(this);

        tipoCultivoList = pragaPresenter.findAllTipoCultivo();
        pragaList = pragaPresenter.findAllPraga();

        ArrayAdapter<TipoCultivo> myAdapter = new ArrayAdapter<TipoCultivo>(this, android.R.layout.simple_spinner_item, tipoCultivoList);
        spiTipoPraga.setAdapter(myAdapter);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        autoCompletePraga.setThreshold(1);
        pragaAdapter = new PragaAdapter(this, R.layout.activity_registro_pragas, R.id.textViewPragaNomeComum, pragaListFilter);
        autoCompletePraga.setAdapter(pragaAdapter);
        pragaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spiTipoPraga.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tipoCultivo = (TipoCultivo) adapterView.getSelectedItem();
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
                pragaAdapter = new PragaAdapter(RegistroPragasActivity.this, R.layout.activity_main, R.id.textViewPragaNomeComum, pragaListFilter);
                autoCompletePraga.setAdapter(pragaAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                pragaAdapter.clear();
                pragaAdapter.notifyDataSetChanged();
            }
        });

        autoCompletePraga.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                if (item instanceof Praga) {
                    praga = (Praga) item;
                }
            }
        });
        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            validarCampos();
            }
        });
    }

    private boolean validarCampos() {
        TipoCultivo tipoCultivo = (TipoCultivo) spiTipoPraga.getSelectedItem();
        if (tipoCultivo == null){
            ((TextView)spiTipoPraga.getSelectedView()).setError("Error message");
        }
        if (praga == null){
            autoCompletePraga.setError("Error message");
        }
        return true;
    }

}
