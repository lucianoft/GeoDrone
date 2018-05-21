package br.com.geodrone.ui.monitoramento;

import android.location.Location;
import android.util.Log;

import br.com.geodrone.model.RotaTrabalho;
import br.com.geodrone.model.constantes.FlagOperacaoRota;
import br.com.geodrone.model.constantes.FlagTipoRota;
import br.com.geodrone.service.RotaTrabalhoService;
import br.com.geodrone.ui.base.BaseFragmentActivity;
import br.com.geodrone.ui.base.BasePresenter;

/**
 * Created by luciano on 31/03/2018.
 */

public class MonitoramentoPresenter extends BasePresenter<MonitoramentoPresenter.View> {

    private static String TAG = MonitoramentoPresenter.class.getName();

    interface View {
        void onChangeLocation(Location location);
    }

    private Location locationOld = null;
    private RotaTrabalhoService rotaTrabalhoService;
    private BaseFragmentActivity fragmentActivity;

    public MonitoramentoPresenter(BaseFragmentActivity fragmentActivity){
        this.fragmentActivity = fragmentActivity;
        this.rotaTrabalhoService = new RotaTrabalhoService(fragmentActivity);
    }

    private float distancia(Location location){
        float distancia = 0;
        if (locationOld != null){
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
                rotaTrabalhoService.insert(rotaTrabalho);

            }else if( distancia(location) > 10f) {
                RotaTrabalho rotaTrabalho = new RotaTrabalho();
                rotaTrabalho.setFlagTipo(FlagTipoRota.MONITORAMENTO.value());
                rotaTrabalho.setFlagOperacaoRota(FlagOperacaoRota.MEIO_MONITORAMENTO.value());
                rotaTrabalhoService.insert(rotaTrabalho);
            }
            Log.i(TAG, "Rota de trabalho grava com sucesso");
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
                rotaTrabalhoService.insert(rotaTrabalho);
            }
            Log.i(TAG, "Rota de trabalho grava com sucesso");
        }catch (Exception ex){
            Log.e(TAG, "Erro ao gravar Rota de trabalho", ex);
        }
    }
}
