package br.com.geodrone.ui.registrodoenca;

import android.location.Location;
import android.util.Log;

import br.com.geodrone.R;
import br.com.geodrone.Session;
import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.RegistroDoenca;
import br.com.geodrone.service.RegistroDoencaService;
import br.com.geodrone.ui.base.BaseFragmentActivity;
import br.com.geodrone.ui.base.BasePresenter;
import br.com.geodrone.ui.monitoramento.MonitoramentoActivity;
import br.com.geodrone.utils.PreferencesUtils;

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

    public void salvar(Location location) {
        try {
            boolean isOk = validar(location);
            if (isOk) {
                RegistroDoenca registroDoenca = new RegistroDoenca();
                registroDoenca.setIdDoencaRef(null);
                registroDoenca.setLatitude(location != null ? location.getLatitude() : null);
                registroDoenca.setLongitude(location != null ? location.getLongitude() : null);
                registroDoenca.setQtde(0);
                Cliente cliente = Session.getAttribute(PreferencesUtils.CHAVE_CLIENTE);

                registroDoenca.setIdClienteRef(cliente != null ? cliente.getIdClienteRef() : null);

                this.registroDoencaService.insert(registroDoenca);

                view.onSemRegistroDoencaSucesso(activity.getString(R.string.msg_operacao_sucesso));
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
