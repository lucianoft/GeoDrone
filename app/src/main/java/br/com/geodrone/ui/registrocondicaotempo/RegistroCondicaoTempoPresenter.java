package br.com.geodrone.ui.registrocondicaotempo;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.model.RegistroCondicaoTempo;
import br.com.geodrone.model.constantes.FlagEstadoTempo;
import br.com.geodrone.service.RegistroCondicaoTempoService;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.base.BasePresenter;
import br.com.geodrone.utils.DateUtils;
import br.com.geodrone.utils.Messenger;
import br.com.geodrone.utils.NumberUtils;

/**
 * Created by fernandes on 03/05/2018.
 */

public class RegistroCondicaoTempoPresenter extends BasePresenter<RegistroCondicaoTempoPresenter.View> {

    private static  String TAG = RegistroCondicaoTempoPresenter.class.getName();
    private final BaseActivity activity;
    private final RegistroCondicaoTempoService registroCondicaoTempoService;

    interface View {
        void onRegistroCondicaoTempoSucesso(String message);
        void onRegistroCondicaoTempoErro(String message);
    }

    public RegistroCondicaoTempoPresenter(BaseActivity activity){
        this.activity = activity;
        this.registroCondicaoTempoService = new RegistroCondicaoTempoService(activity);
    }

    /* Initialise car items in list. */
    public List<RegistroCondicaoTempoRecyclerViewItem> getRegistroCondicaoTempoRecyclerViewItem()
    {
        List<RegistroCondicaoTempoRecyclerViewItem> carItemList = new ArrayList<RegistroCondicaoTempoRecyclerViewItem>();
            carItemList.add(new RegistroCondicaoTempoRecyclerViewItem(FlagEstadoTempo.CEU_BRIGADEIRO.getBundle(), R.mipmap.ic_ceu_brigadeiro));
            carItemList.add(new RegistroCondicaoTempoRecyclerViewItem(FlagEstadoTempo.ENSOLARADO.getBundle(), R.mipmap.ic_ensolarado));
            carItemList.add(new RegistroCondicaoTempoRecyclerViewItem(FlagEstadoTempo.PARCIALMENTE_NUBLADO.getBundle(), R.mipmap.ic_parcialmente_nublado));
            carItemList.add(new RegistroCondicaoTempoRecyclerViewItem(FlagEstadoTempo.NUBLADO.getBundle(), R.mipmap.ic_chuva));
            carItemList.add(new RegistroCondicaoTempoRecyclerViewItem(FlagEstadoTempo.GAROA.getBundle(), R.mipmap.ic_chuva));
            carItemList.add(new RegistroCondicaoTempoRecyclerViewItem(FlagEstadoTempo.CHUVA.getBundle(), R.mipmap.ic_chuva));
            carItemList.add(new RegistroCondicaoTempoRecyclerViewItem(FlagEstadoTempo.INVERNADO.getBundle(), R.mipmap.ic_invernado));
            carItemList.add(new RegistroCondicaoTempoRecyclerViewItem(FlagEstadoTempo.TEMPESTADE.getBundle(), R.mipmap.ic_tempestade));
        return carItemList;
    }

    public Messenger validarSalvar(String flagDirecao, int position){
        Messenger messenger = new Messenger();

        return messenger;
    }

    public void salvar(String flagDirecao, int position){
        try{

            Messenger messenger = validarSalvar(flagDirecao, position);
            if (messenger == null || !messenger.isError()) {
                NumberUtils numberUtils = new NumberUtils();
                DateUtils dateUtils = new DateUtils();
                RegistroCondicaoTempo registroCondicaoTempo = new RegistroCondicaoTempo();
                registroCondicaoTempo.setFlagDirecao(flagDirecao);
                registroCondicaoTempo.setFlagCondicaoTempo(FlagEstadoTempo.getValueByIndice(position));
                registroCondicaoTempo.setDtRegistro(dateUtils.now());

                registroCondicaoTempoService.insert(registroCondicaoTempo);

                view.onRegistroCondicaoTempoSucesso(activity.getString(R.string.msg_operacao_sucesso));
            }else{
                this.activity.showMessenger(messenger);
            }
        }catch (Exception ex){
            Log.e(TAG, ex.toString(), ex);
            this.activity.onError(ex);
        }
    }
}

