package br.com.geodrone.service;

import android.content.Context;

import java.util.List;

import br.com.geodrone.model.EstacaoPluviometrica;
import br.com.geodrone.repository.CrudRepository;
import br.com.geodrone.repository.EstacaoPluviometricaRepository;

public class EstacaoPluviometricaService extends ServiceCrud<EstacaoPluviometrica, Long> {
    EstacaoPluviometricaRepository estacaoPluviometricaRepository = null;

    public EstacaoPluviometricaService(Context ctx){
        estacaoPluviometricaRepository = new EstacaoPluviometricaRepository(ctx);
    }

    public CrudRepository<EstacaoPluviometrica, Long> getCrudRepoitory(){
        return estacaoPluviometricaRepository;
    }

    public List<EstacaoPluviometrica> findAllByCliente(Long idCliente){
        return estacaoPluviometricaRepository.findAllByCliente(idCliente);
    }
}
