package br.com.geodrone.service;

import android.content.Context;

import java.util.Date;
import java.util.List;

import br.com.geodrone.SessionGeooDrone;
import br.com.geodrone.dto.LocationPointDto;
import br.com.geodrone.dto.LocationRotaDto;
import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.RotaTrabalho;
import br.com.geodrone.model.Usuario;
import br.com.geodrone.model.constantes.FlagTipoRota;
import br.com.geodrone.repository.CrudRepository;
import br.com.geodrone.repository.RotaTrabalhoRepository;
import br.com.geodrone.service.util.CrudService;

/**
 * Created by fernandes on 25/03/2018.
 */

public class RotaTrabalhoService extends CrudService<RotaTrabalho, Long> {
    RotaTrabalhoRepository rotaTrabalhoRepository = null;

    public RotaTrabalhoService(Context ctx){
        rotaTrabalhoRepository = new RotaTrabalhoRepository(ctx);
    }

    public CrudRepository<RotaTrabalho, Long> getRepository(){
        return rotaTrabalhoRepository;
    }

    public LocationRotaDto getLocationRotaDto(Date dtInclusao){
        Usuario usuario = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_USUARIO);
        Cliente cliente = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_CLIENTE);
        LocationRotaDto locationRotaDto = new LocationRotaDto();

        List<RotaTrabalho> rotaTrabalhos = rotaTrabalhoRepository.findAllByUsuario(usuario.getId(),
                                                                                   cliente.getId(),
                                                                                   dtInclusao);
        if (rotaTrabalhos != null){
            for (RotaTrabalho rotaTrabalho: rotaTrabalhos) {
                LocationPointDto locationPointDto = new LocationPointDto();
                locationPointDto.setLatitude(rotaTrabalho.getLatitude());
                locationPointDto.setLongitude(rotaTrabalho.getLongitude());
                locationPointDto.setDtRegistro(rotaTrabalho.getDtRegistro());
                if (FlagTipoRota.MONITORAMENTO.value().equals(rotaTrabalho.getFlagTipo())){
                    locationRotaDto.addLocationPointMonitoramento(locationPointDto);
                }else{
                    locationRotaDto.addLocationPointRegChuva(locationPointDto);
                }
            }
        }

        return locationRotaDto;
    }
}
