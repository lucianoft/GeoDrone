package br.com.geodrone.service;

import android.content.Context;

import java.util.List;

import br.com.geodrone.model.PontoColetaChuva;
import br.com.geodrone.repository.CrudRepository;
import br.com.geodrone.repository.PontoColetaChuvaRepository;
import br.com.geodrone.service.util.CrudService;

public class PontoColetaChuvaService extends CrudService<PontoColetaChuva, Long> {
    PontoColetaChuvaRepository pontoColetaChuvaRepository = null;

    public PontoColetaChuvaService(Context ctx){
        pontoColetaChuvaRepository = new PontoColetaChuvaRepository(ctx);
    }

    public CrudRepository<PontoColetaChuva, Long> getRepository(){
        return pontoColetaChuvaRepository;
    }

    public List<PontoColetaChuva> findAllByCliente(Long idCliente){
        return pontoColetaChuvaRepository.findAllByCliente(idCliente);
    }

    public PontoColetaChuva findByIdPontoColetaChuvaWeb(Long idPontoColetaChuva) {
        return pontoColetaChuvaRepository.findByIdPontoColetaChuvaWeb(idPontoColetaChuva);
    }
}
