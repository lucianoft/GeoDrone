package br.com.geodrone.service;

import android.content.Context;

import br.com.geodrone.model.Praga;
import br.com.geodrone.repository.CrudRepository;
import br.com.geodrone.repository.PragaRepository;
import br.com.geodrone.service.util.CrudService;

/**
 * Created by fernandes on 27/03/2018.
 */
public class PragaService extends CrudService<Praga, Long> {
    PragaRepository pragaRepository = null;

    public PragaService(Context ctx){
        pragaRepository = new PragaRepository(ctx);
    }

    public CrudRepository<Praga, Long> getRepository(){
        return pragaRepository;
    }
}
