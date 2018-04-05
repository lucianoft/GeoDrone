package br.com.geodrone.service;

import android.content.Context;

import br.com.geodrone.model.Doenca;
import br.com.geodrone.repository.CrudRepository;
import br.com.geodrone.repository.DoencaRepository;
import br.com.geodrone.service.util.CrudService;

/**
 * Created by fernandes on 29/03/2018.
 */

public class DoencaService extends CrudService<Doenca, Long> {
    DoencaRepository doencaRepository = null;

    public DoencaService(Context ctx){
        doencaRepository = new DoencaRepository(ctx);
    }

    public CrudRepository<Doenca, Long> getRepository(){
        return doencaRepository;
    }
}