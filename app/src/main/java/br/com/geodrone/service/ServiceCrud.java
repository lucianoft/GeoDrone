package br.com.geodrone.service;

import java.util.List;

/**
 * Created by lucianoft on 15/01/2018.
 */

public interface ServiceCrud<T, ID> {

    public T findById(ID id);
    public T insert(T t);
    public T update(T t);
    public void delete(T t);
    public List<T> findAll();
}
