package br.com.geodrone.service;

import java.util.Date;
import java.util.List;

import br.com.geodrone.Session;
import br.com.geodrone.model.AuditApi;
import br.com.geodrone.model.Usuario;
import br.com.geodrone.repository.CrudRepository;
import br.com.geodrone.utils.PreferencesUtils;

/**
 * Created by lucianoft on 15/01/2018.
 */

public abstract class ServiceCrud<T, ID> {

    public abstract CrudRepository<T, ID> getCrudRepository();

    public T findById(ID id){
        return getCrudRepository().findById(id);
    };
    public T insert(T t){
        if (t instanceof AuditApi){
            Date now = new Date();
            Usuario usuario = Session.getAttribute(PreferencesUtils.CHAVE_USUARIO);

            ((AuditApi)t).setIdUsuario(usuario != null ? usuario.getIdUsuarioRef() : null);
            ((AuditApi)t).setDtInclusao(now);
            ((AuditApi)t).setDtAlteracao(now);
        }
        return getCrudRepository().insert(t);
    };
    public T update(T t){
        return getCrudRepository().update(t);
    };
    public void delete(T t){
        getCrudRepository().delete(t);
    };
    public List<T> findAll(){
        return getCrudRepository().findAll();
    };
}
