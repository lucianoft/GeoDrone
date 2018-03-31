package br.com.geodrone.ui.registropraga;

import android.app.Activity;

import java.util.List;

import br.com.geodrone.model.Praga;
import br.com.geodrone.model.TipoCultivo;
import br.com.geodrone.ui.base.BasePresenter;
import br.com.geodrone.ui.base.ProgressBarPresenter;
import br.com.geodrone.service.PragaService;
import br.com.geodrone.service.TipoCultivoService;

/**
 * Created by fernandes on 29/03/2018.
 */
public class RegistroPragaPresenter extends BasePresenter<RegistroPragaPresenter.View> {

    interface View extends ProgressBarPresenter {

        void onSelectedTipoPraga(TipoCultivo tipoCultivo);

    }

    private Activity activity;
    PragaService pragaService = null;
    TipoCultivoService tipoCultivoService = null;

    public RegistroPragaPresenter(Activity activity){
        this.activity = activity;
        this.pragaService = new PragaService(activity);
        this.tipoCultivoService = new TipoCultivoService(activity);
    }

    public List<Praga> findAllPraga(){
        return this.pragaService.findAll();
    }

    public List<TipoCultivo> findAllTipoCultivo(){
        return this.tipoCultivoService.findAll();
    }
}

