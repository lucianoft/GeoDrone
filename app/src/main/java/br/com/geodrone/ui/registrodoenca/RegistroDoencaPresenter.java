package br.com.geodrone.ui.registrodoenca;

import android.app.Activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.geodrone.Session;
import br.com.geodrone.model.AuditApi;
import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.Doenca;
import br.com.geodrone.model.RegistroDoenca;
import br.com.geodrone.model.TipoCultivo;
import br.com.geodrone.model.Usuario;
import br.com.geodrone.presenter.BasePresenter;
import br.com.geodrone.presenter.ProgressBarPresenter;
import br.com.geodrone.service.DoencaService;
import br.com.geodrone.service.RegistroDoencaService;
import br.com.geodrone.service.TipoCultivoService;
import br.com.geodrone.utils.PreferencesUtils;

/**
 * Created by fernandes on 29/03/2018.
 */
public class RegistroDoencaPresenter extends BasePresenter<RegistroDoencaPresenter.View> {

    interface View extends ProgressBarPresenter {
        void onSelectedTipoCultivo(TipoCultivo tipoCultivo);
        void regitroDoencaSucesso();
        void regitroDoencaErro();
    }

    private Activity activity;
    RegistroDoencaService registroDoencaService = null;
    DoencaService doencaService = null;
    TipoCultivoService tipoCultivoService = null;

    public RegistroDoencaPresenter(Activity activity){
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

    public RegistroDoenca salvar(Doenca doenca, String observacao, Double latitude, Double longitude, String qtde) {
        RegistroDoenca registroDoenca = new RegistroDoenca();
        registroDoenca.setIdDoencaRef(doenca != null ? doenca.getIdDoencaRef() : null);
        registroDoenca.setLatitude(latitude);
        registroDoenca.setLongitude(longitude);
        registroDoenca.setQtde(Integer.parseInt(qtde));
        Cliente cliente = Session.getAttribute(PreferencesUtils.CHAVE_CLIENTE);

        registroDoenca.setIdClienteRef(cliente != null ? cliente.getIdClienteRef() : null);
        registroDoenca = this.registroDoencaService.insert(registroDoenca);

        view.regitroDoencaSucesso();

        return registroDoenca;
    }
}

