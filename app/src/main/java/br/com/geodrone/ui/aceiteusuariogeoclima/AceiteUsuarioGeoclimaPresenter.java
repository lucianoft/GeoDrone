package br.com.geodrone.ui.aceiteusuariogeoclima;

import br.com.geodrone.R;
import br.com.geodrone.SessionGeooDrone;
import br.com.geodrone.model.Usuario;
import br.com.geodrone.service.UsuarioService;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.base.BasePresenter;

/**
 * Created by fernandes on 09/05/2018.
 */

public class AceiteUsuarioGeoclimaPresenter extends BasePresenter<AceiteUsuarioGeoclimaPresenter.View> {

    private static  String TAG = AceiteUsuarioGeoclimaPresenter.class.getName();
    private UsuarioService usuarioService;

    interface View {

        void onClickAceite();

        void onErrorAceite(String message);

        void onAceiteSucesso(String message);
    }

    private BaseActivity activity;

    public AceiteUsuarioGeoclimaPresenter(BaseActivity activity){
        this.activity = activity;
        this.usuarioService = new UsuarioService(activity);
    }

    public void aceite(Boolean isChecked){
        if (isChecked){
            Usuario usuario = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_USUARIO);
            usuario.setIndAceiteGeoClima(1);
            usuario = usuarioService.update(usuario);
            SessionGeooDrone.setAttribute(SessionGeooDrone.CHAVE_USUARIO, usuario);
            view.onAceiteSucesso(activity.getString(R.string.msg_operacao_sucesso));
        }else{
            view.onErrorAceite(activity.getString(R.string.msg_obr_aceite_usuario));
        }
    }
}