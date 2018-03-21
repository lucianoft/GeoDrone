package br.com.geodrone.presenter;

import android.app.Activity;

import java.util.List;

import br.com.geodrone.model.EstacaoPluviometrica;
import br.com.geodrone.service.EstacaoPluviometricaService;
import br.com.geodrone.service.UsuarioService;

public class EstacaoPluviometricaPresenter {
    EstacaoPluviometricaService estacaoPluviometricaService = null;

    public EstacaoPluviometricaPresenter(Activity activity) {
        this.estacaoPluviometricaService = new EstacaoPluviometricaService(activity);
    }
    public List<EstacaoPluviometrica> findAllByCliente(Long idCliente){
        return estacaoPluviometricaService.findAllByCliente(idCliente);
    }
}
