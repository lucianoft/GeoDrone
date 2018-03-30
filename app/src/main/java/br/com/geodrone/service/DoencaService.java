package br.com.geodrone.service;

import android.content.Context;

import br.com.geodrone.model.Doenca;
import br.com.geodrone.repository.CrudRepository;
import br.com.geodrone.repository.DoencaRepository;

/**
 * Created by fernandes on 29/03/2018.
 */

public class DoencaService extends ServiceCrud<Doenca, Long> {
    DoencaRepository doencaRepository = null;

    public DoencaService(Context ctx){
        doencaRepository = new DoencaRepository(ctx);
    }

    public CrudRepository<Doenca, Long> getCrudRepository(){
        return doencaRepository;
    }
}