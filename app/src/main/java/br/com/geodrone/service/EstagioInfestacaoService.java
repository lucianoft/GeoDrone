package br.com.geodrone.service;

import android.content.Context;

import br.com.geodrone.model.EstagioInfestacao;
import br.com.geodrone.repository.CrudRepository;
import br.com.geodrone.repository.EstagioInfestacaoRepository;
import br.com.geodrone.service.util.CrudService;

/**
 * Created by fernandes on 04/06/2018.
 */

public class EstagioInfestacaoService extends CrudService<EstagioInfestacao, Long> {
    EstagioInfestacaoRepository estagioInfestacaoRepository = null;

    public EstagioInfestacaoService(Context ctx){
        estagioInfestacaoRepository = new EstagioInfestacaoRepository(ctx);
    }

    public CrudRepository<EstagioInfestacao, Long> getRepository(){
        return estagioInfestacaoRepository;
    }
}
