package br.com.geodrone.service;

import android.content.Context;

import br.com.geodrone.model.DefensivoQuimico;
import br.com.geodrone.repository.CrudRepository;
import br.com.geodrone.repository.DefensivoQuimicoRepository;
import br.com.geodrone.service.util.CrudService;

/**
 * Created by fernandes on 17/06/2018.
 */

public class DefensivoQuimicoService extends CrudService<DefensivoQuimico, Long> {
    DefensivoQuimicoRepository defensivoQuimicoRepository = null;

    public DefensivoQuimicoService(Context ctx){
        defensivoQuimicoRepository = new DefensivoQuimicoRepository(ctx);
    }

    public CrudRepository<DefensivoQuimico, Long> getRepository(){
        return defensivoQuimicoRepository;
    }

}
