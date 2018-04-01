package br.com.geodrone.ui.monitoramento;

import android.location.Location;
import android.util.Log;

import br.com.geodrone.model.RotaTrabalho;
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
            if (locationOld != null || distancia(location) > 10f) {
                RotaTrabalho rotaTrabalho = new RotaTrabalho();
                rotaTrabalho.setLatitude(location.getLatitude());
                rotaTrabalho.setLongitude(location.getLongitude());
                rotaTrabalhoService.insert(rotaTrabalho);
            }
            Log.i(TAG, "Rota de trabalho grava com sucesso");
        }catch (Exception ex){
            Log.i(TAG, "Erro ao gravar Rota de trabalho");
        }
        locationOld = location;

    }
}
