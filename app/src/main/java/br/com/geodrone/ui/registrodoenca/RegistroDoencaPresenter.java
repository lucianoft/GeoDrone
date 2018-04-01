package br.com.geodrone.ui.registrodoenca;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.Session;
import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.Doenca;
import br.com.geodrone.model.RegistroDoenca;
import br.com.geodrone.model.TipoCultivo;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.base.BasePresenter;
import br.com.geodrone.service.DoencaService;
import br.com.geodrone.service.RegistroDoencaService;
import br.com.geodrone.service.TipoCultivoService;
import br.com.geodrone.utils.PreferencesUtils;
import br.com.geodrone.utils.StringUtils;

/**
 * Created by fernandes on 29/03/2018.
 */
public class RegistroDoencaPresenter extends BasePresenter<RegistroDoencaPresenter.View> {

    interface View {
        void onSelectedTipoCultivo(TipoCultivo tipoCultivo);

        void onErrorQtde(String message);
        void onErrorDoenca(String message);

        void onRegitroDoencaSucesso(String message);
        void onErrorRegitroDoenca(String message);

    }

    private BaseActivity activity;
    RegistroDoencaService registroDoencaService = null;
    DoencaService doencaService = null;
    TipoCultivoService tipoCultivoService = null;

    public RegistroDoencaPresenter(BaseActivity activity){
        this.activity = activity;
        this.doencaService = new DoencaService(activity);
        this.tipoCultivoService = new TipoCultivoService(activity);
        this.registroDoencaService = new RegistroDoencaService(activity);
    }

    public List<Doenca> findAllDoenca(){
        List<Doenca> doencas = this.doencaService.findAll();
        return doencas != null ? doencas : new ArrayList<Doenca>();
    }

    public List<TipoCultivo> findAllTipoCultivo(){
        List<TipoCultivo> tipoCultivos = this.tipoCultivoService.findAll();
        return tipoCultivos != null ? tipoCultivos : new ArrayList<TipoCultivo>();
    }


    private boolean validar(Doenca doenca, String observacao, Double latitude, Double longitude, String qtde){
        boolean isOk = true;
        if (hasView()) {
            if (doenca == null){
                view.onErrorDoenca(activity.getString(R.string.msg_obr_doenca));
                isOk = false;
            }
            if (StringUtils.isEmpty(qtde)){
                view.onErrorQtde(activity.getString(R.string.msg_obr_qtde));
                isOk = false;
            }
        }
        return isOk;
    }

    public void salvar(Doenca doenca, String observacao, Double latitude, Double longitude, String qtde) {
        try{
            boolean isOk = validar(doenca, observacao, latitude, longitude, qtde);

            if (isOk) {
                RegistroDoenca registroDoenca = new RegistroDoenca();
                registroDoenca.setIdDoencaRef(doenca != null ? doenca.getIdDoencaRef() : null);
                registroDoenca.setLatitude(latitude);
                registroDoenca.setLongitude(longitude);
                registroDoenca.setQtde(Integer.parseInt(qtde));
                Cliente cliente = Session.getAttribute(PreferencesUtils.CHAVE_CLIENTE);

                registroDoenca.setIdClienteRef(cliente != null ? cliente.getIdClienteRef() : null);

                this.registroDoencaService.insert(registroDoenca);

                view.onRegitroDoencaSucesso(activity.getString(R.string.msg_operacao_sucesso));
            }
        }catch (Exception ex){
            this.activity.onError(ex);
        }
    }
}

