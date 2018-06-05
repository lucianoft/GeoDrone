package br.com.geodrone.service;

import android.content.Context;

import br.com.geodrone.model.TipoDefensivo;
import br.com.geodrone.repository.CrudRepository;
import br.com.geodrone.repository.TipoDefensivoRepository;
import br.com.geodrone.service.util.CrudService;

/**
 * Created by fernandes on 04/06/2018.
 */

public class TipoDefensivoService extends CrudService<TipoDefensivo, Long> {
    TipoDefensivoRepository tipoDefensivoRepository = null;

    public TipoDefensivoService(Context ctx){
        tipoDefensivoRepository = new TipoDefensivoRepository(ctx);
    }

    public CrudRepository<TipoDefensivo, Long> getRepository(){
        return tipoDefensivoRepository;
    }
}
