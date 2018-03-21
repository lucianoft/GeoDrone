package br.com.geodrone.service;

import android.app.Activity;
import android.content.Context;


import java.util.List;

import br.com.geodrone.model.Usuario;
import br.com.geodrone.repository.CrudRepository;
import br.com.geodrone.repository.UsuarioRepository;

public class UsuarioService extends ServiceCrud<Usuario, Long>{

    UsuarioRepository usuarioRepository = null;

    public UsuarioService(Context ctx){
        usuarioRepository = new UsuarioRepository(ctx);
    }

    public CrudRepository<Usuario, Long> getCrudRepoitory(){
        return usuarioRepository;
    }

    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}
