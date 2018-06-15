package br.com.geodrone.ui.monitoramento;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.location.Location;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.SessionGeooDrone;
import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.RotaTrabalho;
import br.com.geodrone.model.Talhao;
import br.com.geodrone.model.constantes.FlagOperacaoRota;
import br.com.geodrone.model.constantes.FlagTipoRota;
import br.com.geodrone.service.ClienteService;
import br.com.geodrone.service.RotaTrabalhoService;
import br.com.geodrone.service.TalhaoService;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.base.BaseFragmentActivity;
import br.com.geodrone.ui.base.BasePresenter;
import br.com.geodrone.ui.helper.GenericProgress;

/**
 * Created by luciano on 31/03/2018.
 */

public class MonitoramentoPresenter extends BasePresenter<MonitoramentoPresenter.View> {

    private static String TAG = MonitoramentoPresenter.class.getName();

    interface View {
        void onChangeLocation(Location location);
    }

    private Talhao talhao = null;
    private Location locationOld = null;
    private RotaTrabalhoService rotaTrabalhoService;
    private BaseFragmentActivity fragmentActivity;

    public MonitoramentoPresenter(BaseFragmentActivity fragmentActivity){
        this.fragmentActivity = fragmentActivity;
        this.rotaTrabalhoService = new RotaTrabalhoService(fragmentActivity);
    }

    private float distancia(Location location){
        float distancia = 0;
        if (locationOld != null && location.hasAccuracy()){
            distancia = location.distanceTo(locationOld);
        }
        return distancia;
    }

    public void onChangeLocation(Location location){
        try {
            if (locationOld == null){
                RotaTrabalho rotaTrabalho = new RotaTrabalho();
                rotaTrabalho.setFlagTipo(FlagTipoRota.MONITORAMENTO.value());
                rotaTrabalho.setFlagOperacaoRota(FlagOperacaoRota.INICIO_MONITORAMENTO.value());
                rotaTrabalho.setIdTalhao(talhao != null ? talhao.getId() : null);
                rotaTrabalhoService.insert(rotaTrabalho);
                Log.i(TAG, "Rota de trabalho grava com sucesso");
            }else if( distancia(location) > 50f) {
                RotaTrabalho rotaTrabalho = new RotaTrabalho();
                rotaTrabalho.setFlagTipo(FlagTipoRota.MONITORAMENTO.value());
                rotaTrabalho.setFlagOperacaoRota(FlagOperacaoRota.MEIO_MONITORAMENTO.value());
                rotaTrabalho.setIdTalhao(talhao != null ? talhao.getId() : null);
                rotaTrabalhoService.insert(rotaTrabalho);
                Log.i(TAG, "Rota de trabalho grava com sucesso");
            }
        }catch (Exception ex){
            Log.e(TAG, "Erro ao gravar Rota de trabalho", ex);
        }
        locationOld = location;

    }

    public void salvarTerminoRota(Location location){
        try {
            if (location != null) {
                RotaTrabalho rotaTrabalho = new RotaTrabalho();
                rotaTrabalho.setFlagTipo(FlagTipoRota.MONITORAMENTO.value());
                rotaTrabalho.setFlagOperacaoRota(FlagOperacaoRota.FIM_MONITORAMENTO.value());
                rotaTrabalho.setIdTalhao(talhao != null ? talhao.getId() : null);
                rotaTrabalhoService.insert(rotaTrabalho);
            }
            Log.i(TAG, "Rota de trabalho grava com sucesso");
        }catch (Exception ex){
            Log.e(TAG, "Erro ao gravar Rota de trabalho", ex);
        }
    }


    public void selecionarTalhao(final Location location) {

        Cliente cliente = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_CLIENTE);

        TalhaoService talhaoService = new TalhaoService(fragmentActivity);
        List<Talhao> talhaos = talhaoService.findAllByCliente(cliente.getId());
        LayoutInflater li = fragmentActivity.getLayoutInflater();
        android.view.View view = li.inflate(R.layout.dialog_selecionar_talhao, null);
        final Spinner spTalhao = view.findViewById(R.id.spinner_talhao_selecionar);
        ArrayAdapter<Talhao> myAdapter = new ArrayAdapter<Talhao>(fragmentActivity, android.R.layout.simple_spinner_item, talhaos);
        spTalhao.setAdapter(myAdapter);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final AlertDialog.Builder alert = new AlertDialog.Builder(fragmentActivity);
        alert.setTitle(fragmentActivity.getString(R.string.lb_talhao));
        alert.setView(view);
        alert.setCancelable(false);
        alert.setNegativeButton(R.string.lb_cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        final GenericProgress genericProgress = new GenericProgress(fragmentActivity);;
        alert.setPositiveButton(R.string.lb_confirmar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selecionarTalhao(spTalhao);
                onChangeLocation(location);
                dialog.dismiss();
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();

    }

    private void selecionarTalhao(Spinner spTalhao){
        this.talhao = (Talhao) spTalhao.getSelectedItem();
    }

    public Talhao getTalhao(){
        return this.talhao;
    }
}
