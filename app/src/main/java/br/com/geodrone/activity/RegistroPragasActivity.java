package br.com.geodrone.activity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.dto.PragaDto;
import br.com.geodrone.utils.NumberUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.satsuware.usefulviews.LabelledSpinner;

public class RegistroPragasActivity extends AppCompatActivity implements LabelledSpinner.OnItemChosenListener {

    @BindView(R.id.edit_text_latitude_praga) EditText editTextLatitude;
    @BindView(R.id.edit_text_longitude_praga) EditText editTextLongitude;
    @BindView(R.id.spinner_tipo_praga) Spinner spiTipoPraga;
    @BindView(R.id.spinner_praga) Spinner spiPraga;
    @BindView(R.id.edit_text_obs_praga) EditText editTextObservacao;
    @BindView(R.id.edit_text_qtde_praga) EditText editTextQtdePragas;

    private Location location;
    private NumberUtils numberUtils = new NumberUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_pragas);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true);      //Ativar o botão

        Intent it = getIntent();
        location = it.getParcelableExtra("localizacao");
        if (location != null){
            editTextLatitude.setText(numberUtils.toString(location.getLatitude()));
            editTextLongitude.setText(numberUtils.toString(location.getLongitude()));
        }
        // Initializing list view with the custom adapter
        ArrayList <PragaDto> pragaList = new ArrayList<>();
        // Populating list Pragas
        for(int i=0; i<100; i++) {
            pragaList.add(new PragaDto(new Long(i), "Praga " + i));
        }
        //spiPraga.setItemsArray(pragaList);
        //spiPraga.setOnItemChosenListener(this);
    }

    @Override
    public void onItemChosen(View labelledSpinner, AdapterView<?> adapterView, View itemView, int position, long id) {

    }

    @Override
    public void onNothingChosen(View labelledSpinner, AdapterView<?> adapterView) {

    }
}
