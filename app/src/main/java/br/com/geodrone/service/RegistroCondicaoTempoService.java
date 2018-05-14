package br.com.geodrone.service;

import android.content.Context;

import br.com.geodrone.model.RegistroCondicaoTempo;
import br.com.geodrone.repository.CrudRepository;
import br.com.geodrone.repository.RegistroCondicaoTempoRepository;
import br.com.geodrone.service.util.CrudService;

/**
 * Created by fernandes on 03/05/2018.
 */

public class RegistroCondicaoTempoService extends CrudService<RegistroCondicaoTempo, Long> {
    RegistroCondicaoTempoRepository registroChuvaRepository = null;

    public RegistroCondicaoTempoService(Context ctx){
        registroChuvaRepository = new RegistroCondicaoTempoRepository(ctx);
    }

    public CrudRepository<RegistroCondicaoTempo, Long> getRepository(){
        return registroChuvaRepository;
    }

}
