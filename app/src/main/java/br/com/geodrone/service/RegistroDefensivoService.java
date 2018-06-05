package br.com.geodrone.service;

import android.content.Context;

import br.com.geodrone.model.RegistroDefensivo;
import br.com.geodrone.repository.CrudRepository;
import br.com.geodrone.repository.RegistroDefensivoRepository;
import br.com.geodrone.service.util.CrudService;

/**
 * Created by fernandes on 04/06/2018.
 */
public class RegistroDefensivoService extends CrudService<RegistroDefensivo, Long> {
    RegistroDefensivoRepository registroDefensivoRepository = null;

    public RegistroDefensivoService(Context ctx){
        registroDefensivoRepository = new RegistroDefensivoRepository(ctx);
    }

    public CrudRepository<RegistroDefensivo, Long> getRepository(){
        return registroDefensivoRepository;
    }
}

