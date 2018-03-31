package br.com.geodrone.service;

import android.content.Context;

import br.com.geodrone.model.RegistroDoenca;
import br.com.geodrone.repository.CrudRepository;
import br.com.geodrone.repository.RegistroDoencaRepository;

/**
 * Created by fernandes on 30/03/2018.
 */

public class RegistroDoencaService extends ServiceCrud<RegistroDoenca, Long> {
    RegistroDoencaRepository registroDoencaRepository = null;

    public RegistroDoencaService(Context ctx){
        registroDoencaRepository = new RegistroDoencaRepository(ctx);
    }

    public CrudRepository<RegistroDoenca, Long> getCrudRepository(){
        return registroDoencaRepository;
    }
}
