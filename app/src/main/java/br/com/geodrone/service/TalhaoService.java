package br.com.geodrone.service;

import android.content.Context;

import java.util.List;

import br.com.geodrone.SessionGeooDrone;
import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.Talhao;
import br.com.geodrone.model.Usuario;
import br.com.geodrone.repository.CrudRepository;
import br.com.geodrone.repository.TalhaoRepository;
import br.com.geodrone.service.util.CrudService;

/**
 * Created by luciano on 31/05/2018.
 */

public class TalhaoService  extends CrudService<Talhao, Long> {
    TalhaoRepository talhaoRepository = null;

    public TalhaoService(Context ctx){
        talhaoRepository = new TalhaoRepository(ctx);
    }

    public CrudRepository<Talhao, Long> getRepository(){
        return talhaoRepository;
    }

    public List<Talhao> findAllByCliente(Long idCliente){
        return talhaoRepository.findAllByCliente(idCliente);
    }
}