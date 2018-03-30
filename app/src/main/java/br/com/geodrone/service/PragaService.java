package br.com.geodrone.service;

import android.content.Context;

import br.com.geodrone.model.Praga;
import br.com.geodrone.repository.CrudRepository;
import br.com.geodrone.repository.PragaRepository;

/**
 * Created by fernandes on 27/03/2018.
 */
public class PragaService extends ServiceCrud<Praga, Long> {
    PragaRepository pragaRepository = null;

    public PragaService(Context ctx){
        pragaRepository = new PragaRepository(ctx);
    }

    public CrudRepository<Praga, Long> getCrudRepository(){
        return pragaRepository;
    }
}
