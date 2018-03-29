package br.com.geodrone.presenter;

import android.app.Activity;

import java.util.List;

import br.com.geodrone.model.Praga;
import br.com.geodrone.model.TipoCultivo;
import br.com.geodrone.service.PragaService;
import br.com.geodrone.service.TipoCultivoService;

/**
 * Created by fernandes on 27/03/2018.
 */

public class PragaPresenter {
    PragaService pragaService = null;
    TipoCultivoService tipoCultivoService = null;

    public PragaPresenter(Activity activity) {
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
