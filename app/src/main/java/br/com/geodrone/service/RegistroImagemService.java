package br.com.geodrone.service;

import android.content.Context;

import br.com.geodrone.model.RegistroImagem;
import br.com.geodrone.repository.CrudRepository;
import br.com.geodrone.repository.RegistroImagemRepository;
import br.com.geodrone.service.util.CrudService;

/**
 * Created by fernandes on 01/04/2018.
 */

public class RegistroImagemService extends CrudService<RegistroImagem, Long> {
    RegistroImagemRepository registroImagemRepository = null;

    public RegistroImagemService(Context ctx){
        registroImagemRepository = new RegistroImagemRepository(ctx);
    }

    public CrudRepository<RegistroImagem, Long> getRepository(){
        return registroImagemRepository;
    }
}
