package br.com.geodrone.service;

import java.util.List;

import br.com.geodrone.repository.CrudRepository;

/**
 * Created by lucianoft on 15/01/2018.
 */

public abstract class ServiceCrud<T, ID> {

    public abstract CrudRepository<T, ID> getCrudRepository();

    public T findById(ID id){
        return getCrudRepository().findById(id);
    };
    public T insert(T t){
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
