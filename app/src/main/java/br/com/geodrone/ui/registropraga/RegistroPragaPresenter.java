package br.com.geodrone.ui.registropraga;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.Session;
import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.Praga;
import br.com.geodrone.model.RegistroPraga;
import br.com.geodrone.model.TipoCultivo;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.base.BasePresenter;
import br.com.geodrone.service.PragaService;
import br.com.geodrone.service.RegistroPragaService;
import br.com.geodrone.service.TipoCultivoService;
import br.com.geodrone.utils.PreferencesUtils;
import br.com.geodrone.utils.StringUtils;

/**
 * Created by fernandes on 29/03/2018.
 */
public class RegistroPragaPresenter extends BasePresenter<RegistroPragaPresenter.View> {

    interface View {
        void onSelectedTipoCultivo(TipoCultivo tipoCultivo);

        void onErrorQtde(String message);
        void onErrorPraga(String message);

        void onRegitroPragaSucesso(String message);
        void onErrorRegitroPraga(String message);

    }

    private BaseActivity activity;
    RegistroPragaService registroPragaService = null;
    PragaService pragaService = null;
    TipoCultivoService tipoCultivoService = null;

    public RegistroPragaPresenter(BaseActivity activity){
        this.activity = activity;
        this.pragaService = new PragaService(activity);
        this.tipoCultivoService = new TipoCultivoService(activity);
        this.registroPragaService = new RegistroPragaService(activity);
    }

    public List<Praga> findAllPraga(){
        List<Praga> pragas = this.pragaService.findAll();
        return pragas != null ? pragas : new ArrayList<Praga>();
    }

    public List<TipoCultivo> findAllTipoCultivo(){
        List<TipoCultivo> tipoCultivos = this.tipoCultivoService.findAll();
        return tipoCultivos != null ? tipoCultivos : new ArrayList<TipoCultivo>();
    }


    private boolean validar(Praga praga, String observacao, Double latitude, Double longitude, String qtde){
        boolean isOk = true;
        if (hasView()) {
            if (praga == null){
                view.onErrorPraga(activity.getString(R.string.msg_obr_praga));
                isOk = false;
            }
            if (StringUtils.isEmpty(qtde)){
                view.onErrorQtde(activity.getString(R.string.msg_obr_qtde));
                isOk = false;
            }
        }
        return isOk;
    }

    public void salvar(Praga praga, String observacao, Double latitude, Double longitude, String qtde) {
        boolean isOk = validar(praga, observacao, latitude, longitude, qtde);

        if (isOk) {
            RegistroPraga registroPraga = new RegistroPraga();
            registroPraga.setIdPragaRef(praga != null ? praga.getIdPragaRef() : null);
            registroPraga.setLatitude(latitude);
            registroPraga.setLongitude(longitude);
            registroPraga.setQtde(Integer.parseInt(qtde));
            Cliente cliente = Session.getAttribute(PreferencesUtils.CHAVE_CLIENTE);

            registroPraga.setIdClienteRef(cliente != null ? cliente.getIdClienteRef() : null);

            this.registroPragaService.insert(registroPraga);

            view.onRegitroPragaSucesso(activity.getString(R.string.msg_operacao_sucesso));
        }
    }
}

