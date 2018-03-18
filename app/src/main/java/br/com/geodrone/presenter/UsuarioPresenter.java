package br.com.geodrone.presenter;

import android.app.Activity;

import br.com.geodrone.model.Usuario;
import br.com.geodrone.service.UsuarioService;


public class UsuarioPresenter {

    UsuarioService usuarioService = null;

    public UsuarioPresenter(Activity activity) {
        this.usuarioService = new UsuarioService(activity);
    }

    public Usuario findByEmail(String email, String senha){
        this.usuarioService.findAll();
        return this.usuarioService.findByEmail(email);
    }
}

