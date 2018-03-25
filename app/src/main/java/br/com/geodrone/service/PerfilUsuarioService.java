package br.com.geodrone.service;

import android.content.Context;

import br.com.geodrone.model.PerfilUsuario;
import br.com.geodrone.repository.PerfilUsuarioRepository;
import br.com.geodrone.repository.CrudRepository;

/**
 * Created by fernandes on 25/03/2018.
 */

public class PerfilUsuarioService extends ServiceCrud<PerfilUsuario, Long> {
    PerfilUsuarioRepository perfilUsuarioRepository = null;

    public PerfilUsuarioService(Context ctx){
        perfilUsuarioRepository = new PerfilUsuarioRepository(ctx);
    }

    public CrudRepository<PerfilUsuario, Long> getCrudRepoitory(){
        return perfilUsuarioRepository;
    }
}
