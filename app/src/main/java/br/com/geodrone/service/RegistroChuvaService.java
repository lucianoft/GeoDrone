package br.com.geodrone.service;

import android.content.Context;

import br.com.geodrone.model.RegistroChuva;
import br.com.geodrone.repository.CrudRepository;
import br.com.geodrone.repository.RegistroChuvaRepository;

/**
 * Created by fernandes on 30/03/2018.
 */

public class RegistroChuvaService extends ServiceCrud<RegistroChuva, Long> {
    RegistroChuvaRepository registroChuvaRepository = null;

    public RegistroChuvaService(Context ctx){
        registroChuvaRepository = new RegistroChuvaRepository(ctx);
    }

    public CrudRepository<RegistroChuva, Long> getCrudRepository(){
        return registroChuvaRepository;
    }
}
