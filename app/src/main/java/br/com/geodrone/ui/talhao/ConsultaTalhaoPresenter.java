package br.com.geodrone.ui.talhao;

import java.util.List;

import br.com.geodrone.SessionGeooDrone;
import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.PontoColetaChuva;
import br.com.geodrone.model.Talhao;
import br.com.geodrone.service.ConfiguracaoService;
import br.com.geodrone.service.TalhaoService;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.base.BasePresenter;

/**
 * Created by fernandes on 06/06/2018.
 */

public class ConsultaTalhaoPresenter extends BasePresenter<ConsultaTalhaoPresenter.View> {

    private static final String TAG = ConsultaTalhaoPresenter.class.getSimpleName();


    interface View {
        void onClickTalhao(Talhao talhao);

        void onSucessoFindTalhao(List<Talhao> talhaos);
        void onErroFindTalhao(String msg);
    }

    private BaseActivity activity;

    TalhaoService talhaoService = null;

    public ConsultaTalhaoPresenter(BaseActivity activity) {
        this.activity = activity;
        this.talhaoService = new TalhaoService(activity);
    }

    private List<Talhao> findAllByCliente(Long idCliente){
        return talhaoService.findAllByCliente(idCliente);
    }

    public void findTalhao(){
        try {
            Cliente cliente = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_CLIENTE);

            view.onSucessoFindTalhao(findAllByCliente(cliente.getId()));
        }catch (Exception ex){
            view.onErroFindTalhao(ex.toString());
        }
    }
}    
