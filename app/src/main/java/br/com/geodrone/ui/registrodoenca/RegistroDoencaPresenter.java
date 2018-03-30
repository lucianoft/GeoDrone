package br.com.geodrone.ui.registrodoenca;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import br.com.geodrone.model.Doenca;
import br.com.geodrone.model.TipoCultivo;
import br.com.geodrone.presenter.BasePresenter;
import br.com.geodrone.presenter.ProgressBarPresenter;
import br.com.geodrone.service.DoencaService;
import br.com.geodrone.service.TipoCultivoService;

/**
 * Created by fernandes on 29/03/2018.
 */
public class RegistroDoencaPresenter extends BasePresenter<RegistroDoencaPresenter.View> {

    interface View extends ProgressBarPresenter {
        void onSelectedTipoCultivo(TipoCultivo tipoCultivo);
    }

    private Activity activity;
    DoencaService doencaService = null;
    TipoCultivoService tipoCultivoService = null;

    public RegistroDoencaPresenter(Activity activity){
        this.activity = activity;
        this.doencaService = new DoencaService(activity);
        this.tipoCultivoService = new TipoCultivoService(activity);
    }

    public List<Doenca> findAllDoenca(){
        List<Doenca> doencas = this.doencaService.findAll();
        return doencas != null ? doencas : new ArrayList<Doenca>();
    }

    public List<TipoCultivo> findAllTipoCultivo(){
        List<TipoCultivo> tipoCultivos = this.tipoCultivoService.findAll();
        return tipoCultivos != null ? tipoCultivos : new ArrayList<TipoCultivo>();
    }
}

