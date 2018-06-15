package br.com.geodrone.ui.registrodoenca;

import android.location.Location;
import android.util.Log;

import br.com.geodrone.R;
import br.com.geodrone.model.RegistroDoenca;
import br.com.geodrone.model.Talhao;
import br.com.geodrone.service.RegistroDoencaService;
import br.com.geodrone.ui.base.BaseFragmentActivity;
import br.com.geodrone.ui.base.BasePresenter;

/**
 * Created by fernandes on 31/03/2018.
 */
public class RegistroSemDoenca extends BasePresenter<RegistroSemDoenca.View> {

    private static final String TAG = RegistroSemDoenca.class.getName();

    public interface View {
        void onSemRegistroDoencaSucesso(String message);
        void onErrorSemRegistroRegitroDoenca(String message);

    }

    private BaseFragmentActivity activity;
    RegistroDoencaService registroDoencaService = null;

    public RegistroSemDoenca(BaseFragmentActivity activity){
        this.activity = activity;
        this.registroDoencaService = new RegistroDoencaService(activity);
    }

    public void salvar(Location location, Talhao talhao) {
        try {
            boolean isOk = validar(location, talhao);
            if (isOk) {
                RegistroDoenca registroDoenca = new RegistroDoenca();
                registroDoenca.setIdDoenca(null);
                registroDoenca.setIdTalhao(talhao != null ? talhao.getId() : null);
                registroDoenca.setLatitude(location != null ? location.getLatitude() : null);
                registroDoenca.setLongitude(location != null ? location.getLongitude() : null);
                this.registroDoencaService.insert(registroDoenca);

                view.onSemRegistroDoencaSucesso(activity.getString(R.string.msg_operacao_sucesso));
            }
        }catch (Exception ex){
            Log.e(TAG, ex.toString(), ex);
            this.activity.onError(ex);
        }
    }

    private boolean validar(Location location, Talhao talhao) {
        if (talhao == null){
            view.onErrorSemRegistroRegitroDoenca(activity.getString(R.string.msg_obr_talhao));
            return false;
        }
        return true;
    }
}
