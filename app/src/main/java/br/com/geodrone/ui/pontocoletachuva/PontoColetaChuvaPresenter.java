package br.com.geodrone.ui.pontocoletachuva;

import android.location.Location;

import java.util.ArrayList;
import java.util.List;

import br.com.geodrone.SessionGeooDrone;
import br.com.geodrone.dto.ColetaPluviosidadeDto;
import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.PontoColetaChuva;
import br.com.geodrone.model.RegistroChuva;
import br.com.geodrone.service.PontoColetaChuvaService;
import br.com.geodrone.service.RegistroChuvaService;
import br.com.geodrone.service.RotaTrabalhoService;
import br.com.geodrone.ui.base.BaseFragmentActivity;
import br.com.geodrone.ui.base.BasePresenter;
import br.com.geodrone.utils.DateUtils;
import br.com.geodrone.utils.NumberUtils;

/**
 * Created by fernandes on 05/05/2018.
 */

public class PontoColetaChuvaPresenter extends BasePresenter<PontoColetaChuvaPresenter.View> {

    private static  String TAG = PontoColetaChuvaPresenter.class.getName();
    interface View {
        void onCadastroPontoColetaSucesso(String message);
        void onChangeLocation(Location location);
        void onShowDialogInfPluviosidade(ColetaPluviosidadeDto coletaPluviosidadeDto);
        Location getLocalizacaoAtual();
    }

    private ColetaPluviosidadeDto coletaPluviosidadeDtoOld = null;
    private Location locationOld = null;
    private RotaTrabalhoService rotaTrabalhoService;
    private BaseFragmentActivity fragmentActivity;
    private PontoColetaChuvaService pontoColetaChuvaService;
    private RegistroChuvaService registroChuvaService;
    List<ColetaPluviosidadeDto> coletaPluviosidadeDtos = new ArrayList<>();

    public PontoColetaChuvaPresenter(BaseFragmentActivity fragmentActivity){
        this.fragmentActivity = fragmentActivity;
        this.rotaTrabalhoService = new RotaTrabalhoService(fragmentActivity);
        pontoColetaChuvaService = new PontoColetaChuvaService(fragmentActivity);
        registroChuvaService = new RegistroChuvaService(fragmentActivity);
    }

    public List<PontoColetaChuva> findAllByCliente(Long idCliente){
        return pontoColetaChuvaService.findAllByCliente(idCliente);
    }

    public List<ColetaPluviosidadeDto> getPontosColeta(){
        DateUtils dateUtils = new DateUtils();
        Cliente cliente = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_CLIENTE);

        List<PontoColetaChuva> estacaoPluviometricas = findAllByCliente(cliente.getId());
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
}
