package br.com.geodrone.service;

import android.content.Context;

import br.com.geodrone.model.RotaTrabalho;
import br.com.geodrone.repository.CrudRepository;
import br.com.geodrone.repository.RotaTrabalhoRepository;

/**
 * Created by fernandes on 25/03/2018.
 */

public class RotaTrabalhoService extends ServiceCrud<RotaTrabalho, Long> {
    RotaTrabalhoRepository rotaTrabalhoRepository = null;

    public RotaTrabalhoService(Context ctx){
        rotaTrabalhoRepository = new RotaTrabalhoRepository(ctx);
    }

    public CrudRepository<RotaTrabalho, Long> getCrudRepository(){
        return rotaTrabalhoRepository;
    }
}
