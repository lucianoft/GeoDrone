package br.com.geodrone.service;

import org.greenrobot.greendao.AbstractDao;

import java.util.List;

import br.com.geodrone.repository.CrudRepository;

/**
 * Created by lucianoft on 15/01/2018.
 */

public abstract class ServiceCrud<T, ID> {

    public abstract CrudRepository<T, ID> getCrudRepoitory();

    public T findById(ID id){
        return getCrudRepoitory().findById(id);
    };
    public T insert(T t){
        return getCrudRepoitory().insert(t);
    };
    public T update(T t){
        return getCrudRepoitory().update(t);
    };
    public void delete(T t){
        getCrudRepoitory().delete(t);
    };
    public List<T> findAll(){
        return getCrudRepoitory().findAll();
    };
}
