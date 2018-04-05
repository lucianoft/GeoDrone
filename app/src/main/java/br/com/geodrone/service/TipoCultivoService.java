package br.com.geodrone.service;

import android.content.Context;

import br.com.geodrone.model.TipoCultivo;
import br.com.geodrone.repository.CrudRepository;
import br.com.geodrone.repository.TipoCultivoRepository;
import br.com.geodrone.service.util.CrudService;

/**
 * Created by fernandes on 28/03/2018.
 */

public class TipoCultivoService extends CrudService<TipoCultivo, Long> {
    TipoCultivoRepository tipoCultivoRepository = null;

    public TipoCultivoService(Context ctx){
        tipoCultivoRepository = new TipoCultivoRepository(ctx);
    }

    public CrudRepository<TipoCultivo, Long> getRepository(){
        return tipoCultivoRepository;
    }
}