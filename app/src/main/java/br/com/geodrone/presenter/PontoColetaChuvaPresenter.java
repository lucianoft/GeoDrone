package br.com.geodrone.presenter;

import android.app.Activity;

import java.util.List;

import br.com.geodrone.model.PontoColetaChuva;
import br.com.geodrone.service.PontoColetaChuvaService;

public class PontoColetaChuvaPresenter {
    PontoColetaChuvaService estacaoPluviometricaService = null;

    public PontoColetaChuvaPresenter(Activity activity) {
        this.estacaoPluviometricaService = new PontoColetaChuvaService(activity);
    }
    public List<PontoColetaChuva> findAllByCliente(Long idCliente){
        return estacaoPluviometricaService.findAllByCliente(idCliente);
    }
}
