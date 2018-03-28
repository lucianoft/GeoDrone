package br.com.geodrone.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.activity.utils.MyDividerItemDecoration;
import br.com.geodrone.model.Praga;
import br.com.geodrone.presenter.PragaPresenter;
import br.com.geodrone.view.adapter.ConsultaPragaAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ConsultarPragaActivity extends AppCompatActivity {

    private List<Praga> pragaList = new ArrayList<>();

    private ConsultaPragaAdapter mAdapter;

    @BindView(R.id.spinner_praga_pesquisa)
    Spinner spTipoPraga;

    @BindView(R.id.edit_text_pesquisa_praga)
    EditText editTextPesquisa;

    @BindView(R.id.recyclerViewPesquisaPraga)
    RecyclerView recyclerViewPraga;


    private PragaPresenter pragaPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_consultar_praga);
        ButterKnife.bind(this);

        pragaPresenter = new PragaPresenter(this);

        pragaList = pragaPresenter.findAll();

        pragaPresenter = new PragaPresenter(this);
        mAdapter = new ConsultaPragaAdapter(pragaList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewPraga.setLayoutManager(mLayoutManager);
        recyclerViewPraga.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.VERTICAL, 16));
        recyclerViewPraga.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPraga.setAdapter(mAdapter);

    }
}
