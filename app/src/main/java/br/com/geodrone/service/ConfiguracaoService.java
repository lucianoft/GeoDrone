package br.com.geodrone.service;

import android.content.Context;

import br.com.geodrone.SessionGeooDrone;
import br.com.geodrone.model.Configuracao;
import br.com.geodrone.model.Dispositivo;
import br.com.geodrone.repository.ConfiguracaoRepository;
import br.com.geodrone.repository.CrudRepository;
import br.com.geodrone.service.util.CrudService;
import br.com.geodrone.utils.PreferencesUtils;

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

    public Configuracao getOneConfiguracao(){
        return configuracaoRepository.findOne();
    }

    public void atualizarUrlBase(String url){
        Configuracao configuracao = configuracaoRepository.findOne();
        if (configuracao != null){
            configuracao.setUrl(url);
            configuracaoRepository.update(configuracao);
            SessionGeooDrone.setAttribute(SessionGeooDrone.CHAVE_URL_BASE , url);
        }
    }
}
