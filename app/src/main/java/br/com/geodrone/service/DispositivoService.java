package br.com.geodrone.service;

import android.content.Context;

import br.com.geodrone.SessionGeooDrone;
import br.com.geodrone.model.Dispositivo;
import br.com.geodrone.repository.DispositivoRepository;
import br.com.geodrone.repository.CrudRepository;
import br.com.geodrone.service.util.CrudService;
import br.com.geodrone.utils.DateUtils;

/**
 * Created by fernandes on 09/04/2018.
 */
public class DispositivoService extends CrudService<Dispositivo, Long> {
    DispositivoRepository dispositivoRepository = null;

    public DispositivoService(Context ctx){
        dispositivoRepository = new DispositivoRepository(ctx);
    }

    public CrudRepository<Dispositivo, Long> getRepository(){
        return dispositivoRepository;
    }


    public boolean isPrimeiroLogin(){
        return dispositivoRepository.isPrimeiroLogin();
    }

    public Dispositivo findOneDispositivo(){
        return dispositivoRepository.findOneByCliente();
    }

    public void atualizarDtSincAndroid(){
        Dispositivo dispositivo = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_DISPOSITIVO);
        dispositivo.setDtSincronizacaoAndroid(new DateUtils().now());
        dispositivo = dispositivoRepository.update(dispositivo);

        SessionGeooDrone.setAttribute(SessionGeooDrone.CHAVE_DISPOSITIVO , dispositivo);

    }

    public void atualizarDtSincWeb(){
        Dispositivo dispositivo = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_DISPOSITIVO);
        dispositivo.setDtSincronizacaoErp(new DateUtils().now());
        dispositivo = dispositivoRepository.update(dispositivo);

        SessionGeooDrone.setAttribute(SessionGeooDrone.CHAVE_DISPOSITIVO , dispositivo);

    }
}

