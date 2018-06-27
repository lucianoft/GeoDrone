package br.com.geodrone.ui.main;

import br.com.geodrone.model.Usuario;
import br.com.geodrone.model.constantes.FlagPerfilUsuario;
import br.com.geodrone.service.ConfiguracaoService;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.base.BasePresenter;

/**
 * Created by fernandes on 10/06/2018.
 */

public class MainPresenter extends BasePresenter<MainPresenter.View> {

    private static final String TAG = MainPresenter.class.getSimpleName() ;

    interface View {
    }

    private BaseActivity activity;
    private Long idUsuario;

    ConfiguracaoService configuracaoService = null;

    public MainPresenter(BaseActivity activity) {
        this.activity = activity;
        this.configuracaoService = new ConfiguracaoService(activity);
    }

}
