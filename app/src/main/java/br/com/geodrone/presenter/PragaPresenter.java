package br.com.geodrone.presenter;

import android.app.Activity;

import java.util.List;

import br.com.geodrone.model.Praga;
import br.com.geodrone.service.PragaService;

/**
 * Created by fernandes on 27/03/2018.
 */

public class PragaPresenter {
    PragaService pragaService = null;

    public PragaPresenter(Activity activity) {
        this.pragaService = new PragaService(activity);
    }

    public List<Praga> findAll(){
        return this.pragaService.findAll();
    }
}
