package br.com.geodrone.ui.splashscreen;

import br.com.geodrone.service.DispositivoService;
import br.com.geodrone.service.UsuarioService;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.base.BasePresenter;
import br.com.geodrone.ui.usuario.UsuarioPresenter;

/**
 * Created by fernandes on 31/03/2018.
 */

public class SplashScreenPresenter extends BasePresenter<SplashScreenPresenter.View> {

    interface View {
        void onLoadLogin();
    }

    private BaseActivity activity;

    private DispositivoService dispositivoService;

    public SplashScreenPresenter(BaseActivity activity) {
        this.activity = activity;
        dispositivoService = new DispositivoService(activity);
    }

    public boolean isPrimeiroLogin(){
        return dispositivoService.isPrimeiroLogin();
    }
}
