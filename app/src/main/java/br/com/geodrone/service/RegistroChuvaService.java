package br.com.geodrone.service;

import android.content.Context;

import java.util.List;

import br.com.geodrone.model.PontoColetaChuva;
import br.com.geodrone.model.RegistroChuva;
import br.com.geodrone.repository.CrudRepository;
import br.com.geodrone.repository.RegistroChuvaRepository;
import br.com.geodrone.service.util.CrudService;

/**
 * Created by fernandes on 30/03/2018.
 */

public class RegistroChuvaService extends CrudService<RegistroChuva, Long> {
    RegistroChuvaRepository registroChuvaRepository = null;

    public RegistroChuvaService(Context ctx){
        registroChuvaRepository = new RegistroChuvaRepository(ctx);
    }

    public CrudRepository<RegistroChuva, Long> getRepository(){
        return registroChuvaRepository;
    }


    public RegistroChuva findOneByPontoColetaChuva(Long idPontoColetaChuvaDisp){
        return registroChuvaRepository.findOneByPontoColetaChuva(idPontoColetaChuvaDisp);
    }
}
