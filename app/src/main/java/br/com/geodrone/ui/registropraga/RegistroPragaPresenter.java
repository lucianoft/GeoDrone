package br.com.geodrone.ui.registropraga;

import java.util.ArrayList;
import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.model.Praga;
import br.com.geodrone.model.RegistroPraga;
import br.com.geodrone.model.TipoCultivo;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.base.BasePresenter;
import br.com.geodrone.service.PragaService;
import br.com.geodrone.service.RegistroPragaService;
import br.com.geodrone.service.TipoCultivoService;
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


    private boolean validar(Praga praga, Long idTalhao, String observacao, Double latitude, Double longitude, String qtde){
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
            if (idTalhao == null){
                view.onErrorRegitroPraga(activity.getString(R.string.msg_obr_talhao));
            }
        }
        return isOk;
    }

    public void salvar(Praga praga, Long idTalhao, String observacao, Double latitude, Double longitude, String qtde) {
        try {
            boolean isOk = validar(praga, idTalhao, observacao, latitude, longitude, qtde);

            if (isOk) {
                RegistroPraga registroPraga = new RegistroPraga();
                registroPraga.setIdPraga(praga != null ? praga.getId() : null);
                registroPraga.setObservacao(observacao);
                registroPraga.setIdTalhao(idTalhao);
                registroPraga.setLatitude(latitude);
                registroPraga.setLongitude(longitude);
                registroPraga.setQtde(Long.parseLong(qtde));
                this.registroPragaService.insert(registroPraga);

                view.onRegitroPragaSucesso(activity.getString(R.string.msg_operacao_sucesso));
            }
        }catch (Exception ex){
            this.activity.onError(ex);
        }
    }
}

