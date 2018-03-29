package br.com.geodrone.presenter;

import android.app.Activity;

import java.util.List;

import br.com.geodrone.model.TipoCultivo;
import br.com.geodrone.service.TipoCultivoService;

/**
 * Created by fernandes on 28/03/2018.
 */

public class TipoCultivoPresenter {
    TipoCultivoService tipoCultivoService = null;

    public TipoCultivoPresenter(Activity activity) {
        this.tipoCultivoService = new TipoCultivoService(activity);
    }

    public List<TipoCultivo> findAll(){
        return this.tipoCultivoService.findAll();
    }
}
