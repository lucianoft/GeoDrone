package br.com.geodrone.service;

import android.content.Context;

import br.com.geodrone.model.Configuracao;
import br.com.geodrone.repository.ConfiguracaoRepository;
import br.com.geodrone.repository.CrudRepository;

/**
 * Created by fernandes on 25/03/2018.
 */

public class ConfiguracaoService extends ServiceCrud<Configuracao, Long> {
    ConfiguracaoRepository configuracaoRepository = null;

    public ConfiguracaoService(Context ctx){
        configuracaoRepository = new ConfiguracaoRepository(ctx);
    }

    public CrudRepository<Configuracao, Long> getCrudRepoitory(){
        return configuracaoRepository;
    }
}
