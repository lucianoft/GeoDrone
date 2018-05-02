package br.com.geodrone.ui.registrochuva;

import android.location.Location;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.SessionGeooDrone;
import br.com.geodrone.activity.utils.LocationUtils;
import br.com.geodrone.dto.ColetaPluviosidadeDto;
import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.PontoColetaChuva;
import br.com.geodrone.model.RegistroChuva;
import br.com.geodrone.model.RotaTrabalho;
import br.com.geodrone.presenter.PontoColetaChuvaPresenter;
import br.com.geodrone.service.RegistroChuvaService;
import br.com.geodrone.service.RotaTrabalhoService;
import br.com.geodrone.ui.base.BaseFragmentActivity;
import br.com.geodrone.ui.base.BasePresenter;
import br.com.geodrone.utils.DateUtils;
import br.com.geodrone.utils.Messenger;
import br.com.geodrone.utils.NumberUtils;
import br.com.geodrone.utils.StringUtils;

/**
 * Created by fernandes on 01/04/2018.
 */
public class RegistroPluviosidadePresenter extends BasePresenter<RegistroPluviosidadePresenter.View> {

    private static  String TAG = RegistroPluviosidadePresenter.class.getName();
    interface View {
        void onRegitroChuvaSucesso(String message);
        void onChangeLocation(Location location);
        void onShowDialogInfPluviosidade(ColetaPluviosidadeDto coletaPluviosidadeDto);
        Location getLocalizacaoAtual();

    }

    private ColetaPluviosidadeDto coletaPluviosidadeDtoOld = null;
    private Location locationOld = null;
    private RotaTrabalhoService rotaTrabalhoService;
    private BaseFragmentActivity fragmentActivity;
    private PontoColetaChuvaPresenter estacaoPluviometricaPresenter;
    private RegistroChuvaService registroChuvaService;
    List<ColetaPluviosidadeDto> coletaPluviosidadeDtos = new ArrayList<>();

    public RegistroPluviosidadePresenter(BaseFragmentActivity fragmentActivity){
        this.fragmentActivity = fragmentActivity;
        this.rotaTrabalhoService = new RotaTrabalhoService(fragmentActivity);
        estacaoPluviometricaPresenter = new PontoColetaChuvaPresenter(fragmentActivity);
        registroChuvaService = new RegistroChuvaService(fragmentActivity);
    }

    private float distancia(Location location){
        float distancia = 0;
        if (locationOld != null){
            distancia = location.distanceTo(locationOld);
        }
        return distancia;
    }

    public void onChangeLocation(Location location){
        try {
            if (locationOld != null || distancia(location) > 10f) {
                RotaTrabalho rotaTrabalho = new RotaTrabalho();
                rotaTrabalho.setLatitude(location.getLatitude());
                rotaTrabalho.setLongitude(location.getLongitude());
                rotaTrabalhoService.insert(rotaTrabalho);
            }
            Log.i(TAG, "Rota de trabalho grava com sucesso");
        }catch (Exception ex){
            Log.i(TAG, "Erro ao gravar Rota de trabalho");
            Log.e(TAG, ex.toString(), ex);
        }
        onShowPontoColeta(location);
        locationOld = location;

    }

    private void onShowPontoColeta(Location location) {
        double lat = location.getLatitude();
        double lng = location.getLongitude();
        LatLng locAtual = new LatLng(lat, lng);
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locAtual, Constantes.ZOOM_MAP));


        ColetaPluviosidadeDto coletaPluviosidadeDto = LocationUtils.localLessDistance(location, this.coletaPluviosidadeDtos);
        if ( coletaPluviosidadeDto != null) {
            Location locationMenor = LocationUtils.createNewLocation(coletaPluviosidadeDto.getLatitude(), coletaPluviosidadeDto.getLongitude());
            double distancia = LocationUtils.calculateDistance(location, locationMenor);
            if (distancia <= 10) {
                if (coletaPluviosidadeDtoOld == null || !coletaPluviosidadeDto.equals(coletaPluviosidadeDtoOld)) {
                    view.onShowDialogInfPluviosidade(coletaPluviosidadeDto);
                }
                coletaPluviosidadeDtoOld = coletaPluviosidadeDto;
            }
        }
    }

    public ColetaPluviosidadeDto getPontosColetaDto(Location location){
        if( coletaPluviosidadeDtos != null){
            NumberUtils numberUtils = new NumberUtils();
            for (ColetaPluviosidadeDto coletaPluviosidadeDto: coletaPluviosidadeDtos) {
                if (numberUtils.isEqualsDouble(coletaPluviosidadeDto.getLatitude(), location.getLatitude()) &&
                        numberUtils.isEqualsDouble(coletaPluviosidadeDto.getLongitude(), location.getLongitude())){
                    return coletaPluviosidadeDto;
                }
            }
        }
        return null;

    }

    public List<ColetaPluviosidadeDto> getPontosColeta(){
        DateUtils dateUtils = new DateUtils();
        Cliente cliente = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_CLIENTE);

        List<PontoColetaChuva> estacaoPluviometricas = estacaoPluviometricaPresenter.findAllByCliente(cliente.getId());
        for (PontoColetaChuva pontoColetaChuva : estacaoPluviometricas){

            Location locationAtual = view.getLocalizacaoAtual();

            ColetaPluviosidadeDto pluviosidadeDiariaDto = new ColetaPluviosidadeDto();
            pluviosidadeDiariaDto.setIdPontoColetaChuva(pontoColetaChuva.getId());
            pluviosidadeDiariaDto.setDescricao(pontoColetaChuva.getDescricao());
            pluviosidadeDiariaDto.setLatitude(pontoColetaChuva.getLatitude());
            pluviosidadeDiariaDto.setLongitude(pontoColetaChuva.getLongitude());
            if(locationAtual != null){
                pluviosidadeDiariaDto.setLatitudeLeitura(locationAtual.getLatitude());
                pluviosidadeDiariaDto.setLongitudeLeitura(locationAtual.getLongitude());

            }
            RegistroChuva registroChuva = registroChuvaService.findOneByPontoColetaChuva(pontoColetaChuva.getIdPontoColetaChuva());
            if (registroChuva != null){
                pluviosidadeDiariaDto.setDtLeitura(registroChuva.getDtRegistro());
                if (dateUtils.equals(dateUtils.nowTrunc(), dateUtils.trunc(registroChuva.getDtRegistro()))){
                    pluviosidadeDiariaDto.setIndColetaDia(1);
                }
            }
            coletaPluviosidadeDtos.add(pluviosidadeDiariaDto);
        }
        return coletaPluviosidadeDtos;
    }

    public void salvar(ColetaPluviosidadeDto coletaPluviosidadeDto, String observacao, String qtde){
        try{

            Messenger messenger = validarSalvar(coletaPluviosidadeDto, observacao, qtde);
            if (messenger == null || !messenger.isError()) {
                NumberUtils numberUtils = new NumberUtils();
                RegistroChuva registroChuva = new RegistroChuva();
                registroChuva.setIdPontoColetaChuvaDisp(coletaPluviosidadeDto.getIdPontoColetaChuva());
                registroChuva.setObservacao(observacao);
                registroChuva.setVolume(numberUtils.parseInt(qtde));

                registroChuvaService.insert(registroChuva);

                view.onRegitroChuvaSucesso(fragmentActivity.getString(R.string.msg_operacao_sucesso));
            }else{
                this.fragmentActivity.showMessenger(messenger);
            }
        }catch (Exception ex){
            Log.e(TAG, ex.toString(), ex);
            this.fragmentActivity.onError(ex);
        }
    }

    private Messenger validarSalvar(ColetaPluviosidadeDto coletaPluviosidadeDto, String observacao, String qtde) {
        NumberUtils numberUtils = new NumberUtils();
        Messenger messenger = new Messenger();
        if (coletaPluviosidadeDto == null){
            messenger.addError(fragmentActivity.getString(R.string.msg_obr_ponto_coleta_chuva));
        }
        if (StringUtils.isEmpty(qtde)){
            messenger.addError(fragmentActivity.getString(R.string.msg_obr_volume));
        }
        if (!StringUtils.isEmpty(qtde) && !numberUtils.isNumber(qtde)){
            messenger.addError(fragmentActivity.getString(R.string.msg_obr_volume));
        }
        return messenger;
    }

}
