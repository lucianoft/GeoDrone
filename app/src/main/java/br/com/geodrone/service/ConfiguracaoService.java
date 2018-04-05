package br.com.geodrone.service;

import android.content.Context;

import br.com.geodrone.model.Configuracao;
import br.com.geodrone.repository.ConfiguracaoRepository;
import br.com.geodrone.repository.CrudRepository;
import br.com.geodrone.service.util.CrudService;

/**
 * Created by fernandes on 25/03/2018.
 */

public class ConfiguracaoService extends CrudService<Configuracao, Long> {
    ConfiguracaoRepository configuracaoRepository = null;

    public ConfiguracaoService(Context ctx){
        configuracaoRepository = new ConfiguracaoRepository(ctx);
    }

    public CrudRepository<Configuracao, Long> getRepository(){
        return configuracaoRepository;
    }
}
