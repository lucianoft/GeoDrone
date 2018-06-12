package br.com.geodrone.ui.registrodefensivo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.model.RegistroDefensivo;
import br.com.geodrone.model.TipoDefensivo;
import br.com.geodrone.service.RegistroDefensivoService;
import br.com.geodrone.service.TipoDefensivoService;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.base.BasePresenter;
import br.com.geodrone.utils.DateUtils;
import br.com.geodrone.utils.StringUtils;

/**
 * Created by fernandes on 10/06/2018.
 */

public class RegistroDefensivoPresenter extends BasePresenter<RegistroDefensivoPresenter.View> {

    interface View {

        void onErrorQtde(String message);
        void onErrorTipoDefensivo(String message);
        void onErrorDtRegistro(String message);

        void onRegitroDefensivoSucesso(String message);
        void onErrorRegitroDefensivo(String message);

    }

    private BaseActivity activity;
    RegistroDefensivoService registroDefensivoService = null;
    TipoDefensivoService tipoDefensivoService = null;

    public RegistroDefensivoPresenter(BaseActivity activity){
        this.activity = activity;
        this.tipoDefensivoService = new TipoDefensivoService(activity);
        this.registroDefensivoService = new RegistroDefensivoService(activity);
    }

    public List<TipoDefensivo> findAllTipoDefensivo(){
        List<TipoDefensivo> tipoDefensivos = this.tipoDefensivoService.findAll();
        return tipoDefensivos != null ? tipoDefensivos : new ArrayList<TipoDefensivo>();
    }


    private boolean validar(TipoDefensivo tipoDefensivo, String dtRegistro, String dose){
        boolean isOk = true;
        if (hasView()) {
            if (tipoDefensivo == null){
                view.onErrorTipoDefensivo(activity.getString(R.string.msg_obr_tipo_defensivo));
                isOk = false;
            }
            if (StringUtils.isEmpty(dose)){
                view.onErrorQtde(activity.getString(R.string.msg_obr_dose));
                isOk = false;
            }
        }
        return isOk;
    }

    public void salvar(TipoDefensivo tipoDefensivo, String dtRegistro, String dose) {
        try{
            boolean isOk = validar(tipoDefensivo, dtRegistro, dose);

            if (isOk) {
                DateUtils dateUtils = new DateUtils();

                RegistroDefensivo registroDefensivo = new RegistroDefensivo();
                registroDefensivo.setidTipoDefensivo(tipoDefensivo != null ? tipoDefensivo.getId() : null);
                registroDefensivo.setDose(Double.parseDouble(dose));
                registroDefensivo.setDtRegistro(dateUtils.parse(dtRegistro, "dd/MM/yyyy"));
                this.registroDefensivoService.insert(registroDefensivo);

                view.onRegitroDefensivoSucesso(activity.getString(R.string.msg_operacao_sucesso));
            }
        }catch (Exception ex){
            this.activity.onError(ex);
        }
    }
}