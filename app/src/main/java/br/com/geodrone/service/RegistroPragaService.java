package br.com.geodrone.service;

import android.content.Context;

import br.com.geodrone.model.RegistroPraga;
import br.com.geodrone.repository.CrudRepository;
import br.com.geodrone.repository.RegistroPragaRepository;

/**
 * Created by fernandes on 30/03/2018.
 */

public class RegistroPragaService extends ServiceCrud<RegistroPraga, Long> {
    RegistroPragaRepository registroPragaRepository = null;

    public RegistroPragaService(Context ctx){
        registroPragaRepository = new RegistroPragaRepository(ctx);
    }

    public CrudRepository<RegistroPraga, Long> getCrudRepository(){
        return registroPragaRepository;
    }
}
