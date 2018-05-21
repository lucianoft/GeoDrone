package br.com.geodrone.ui.rotatrabalhokml;

import android.location.Location;
import android.util.Log;

import java.util.Date;

import br.com.geodrone.dto.ColetaPluviosidadeDto;
import br.com.geodrone.dto.LocationRotaDto;
import br.com.geodrone.presenter.PontoColetaChuvaPresenter;
import br.com.geodrone.service.RegistroChuvaService;
import br.com.geodrone.service.RotaTrabalhoService;
import br.com.geodrone.ui.base.BaseFragmentActivity;
import br.com.geodrone.ui.base.BasePresenter;
import br.com.geodrone.utils.Messenger;

/**
 * Created by fernandes on 20/05/2018.
 */
public class RotaTrabalhoKmlPresenter extends BasePresenter<RotaTrabalhoKmlPresenter.View> {

    private static  String TAG = RotaTrabalhoKmlPresenter.class.getName();
    interface View {
        void onRotaTrabalhoKmlSucesso(LocationRotaDto locationRotaDto);
        void onRotaTrabalhoKmlErro(String message);
        void onRotaTrabalhoKmlErro(Messenger messenger);
    }

    private BaseFragmentActivity baseFragmentActivity;
    private RotaTrabalhoService rotaTrabalhoService;

    public RotaTrabalhoKmlPresenter(BaseFragmentActivity fragmentActivity){
        this.baseFragmentActivity = fragmentActivity;
        this.rotaTrabalhoService = new RotaTrabalhoService(fragmentActivity);
    }

    public void configLocationRota(Date date){
        try{
            view.onRotaTrabalhoKmlSucesso(rotaTrabalhoService.getLocationRotaDto(date));
        }catch (Exception ex){
            Log.e(TAG, ex.toString(), ex);
            view.onRotaTrabalhoKmlErro(ex.toString());
        }
    }
}
