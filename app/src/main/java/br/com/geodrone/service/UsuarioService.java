package br.com.geodrone.service;

import android.app.Activity;
import android.content.Context;


import java.util.List;

import br.com.geodrone.model.Usuario;
import br.com.geodrone.repository.UsuarioRepository;

public class UsuarioService implements ServiceCrud<Usuario, Long>{

    UsuarioRepository usuarioRepository = null;

    public UsuarioService(Context ctx){
        usuarioRepository = new UsuarioRepository(ctx);
    }

    @Override
    public Usuario findById(Long idUsuario) {
        return usuarioRepository.findById(idUsuario);
    }

    @Override
    public Usuario insert(Usuario usuario) {
        usuarioRepository.insert(usuario);
        return usuario;
    }

    @Override
    public Usuario update(Usuario usuario) {
        usuarioRepository.update(usuario);
        return usuario;
    }

    @Override
    public void delete(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public Usuario findByEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        return usuario;
    }
}
