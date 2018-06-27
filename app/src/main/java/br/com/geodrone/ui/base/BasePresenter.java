package br.com.geodrone.ui.base;

import br.com.geodrone.model.Usuario;
import br.com.geodrone.model.constantes.FlagPerfilUsuario;

/**
 * Created by fernandes on 29/03/2018.
 */
public abstract class BasePresenter<T>{
    protected T view;

    public void dropView() {
        this.view = null;
    }

    public void onLoad() {
    }

    public void takeView(T view) {
        this.view = view;
        onLoad();
    }

    protected boolean hasView() {
        return view != null;
    }

    public boolean isPerfilMaster(Usuario usuario){
        if (FlagPerfilUsuario.MASTER.getValue().equals(usuario.getFlagPerfil())){
            return true;
        }
        return false;
    }

    public boolean isPerfilAdministrador(Usuario usuario){
        if (FlagPerfilUsuario.ADMINISTRADOR.getValue().equals(usuario.getFlagPerfil())){
            return true;
        }
        return false;
    }

    public boolean isPerfilCliente(Usuario usuario){
        if (FlagPerfilUsuario.CLIENTE.getValue().equals(usuario.getFlagPerfil())){
            return true;
        }
        return false;
    }

    public boolean isPerfilColetor(Usuario usuario){
        if (FlagPerfilUsuario.COLETOR.getValue().equals(usuario.getFlagPerfil())){
            return true;
        }
        return false;
    }
}
