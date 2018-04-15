package br.com.geodrone.ui.registropraga;

import android.location.Location;
import android.util.Log;

import br.com.geodrone.R;
import br.com.geodrone.model.RegistroPraga;
import br.com.geodrone.service.RegistroPragaService;
import br.com.geodrone.ui.base.BaseFragmentActivity;
import br.com.geodrone.ui.base.BasePresenter;

/**
 * Created by fernandes on 31/03/2018.
 */
public class RegistroSemPraga extends BasePresenter<RegistroSemPraga.View> {

    private static final String TAG = RegistroSemPraga.class.getName();

    public interface View {
        void onSemRegistroPragaSucesso(String message);
        void onErrorSemRegistroRegitroPraga(String message);

    }

    private BaseFragmentActivity activity;
    RegistroPragaService registroPragaService = null;

    public RegistroSemPraga(BaseFragmentActivity activity){
        this.activity = activity;
        this.registroPragaService = new RegistroPragaService(activity);
    }

    public void salvar(Location location) {
        try {
            boolean isOk = validar(location);
            if (isOk) {
                RegistroPraga registroPraga = new RegistroPraga();
                registroPraga.setIdPraga(null);
                registroPraga.setLatitude(location != null ? location.getLatitude() : null);
                registroPraga.setLongitude(location != null ? location.getLongitude() : null);
                registroPraga.setQtde(0);
                this.registroPragaService.insert(registroPraga);

                view.onSemRegistroPragaSucesso(activity.getString(R.string.msg_operacao_sucesso));
            }
        }catch (Exception ex){
            Log.e(TAG, ex.toString(), ex);
            this.activity.onError(ex);
        }
    }

    private boolean validar(Location location) {
        return true;
    }
}
