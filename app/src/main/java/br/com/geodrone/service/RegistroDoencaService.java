package br.com.geodrone.service;

import android.content.Context;

import br.com.geodrone.model.RegistroDoenca;
import br.com.geodrone.repository.CrudRepository;
import br.com.geodrone.repository.RegistroDoencaRepository;
import br.com.geodrone.service.util.CrudService;

/**
 * Created by fernandes on 30/03/2018.
 */

public class RegistroDoencaService extends CrudService<RegistroDoenca, Long> {
    RegistroDoencaRepository registroDoencaRepository = null;

    public RegistroDoencaService(Context ctx){
        registroDoencaRepository = new RegistroDoencaRepository(ctx);
    }

    public CrudRepository<RegistroDoenca, Long> getRepository(){
        return registroDoencaRepository;
    }
}
