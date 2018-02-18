package br.com.geodrone.repository;

import android.app.Activity;


import org.greenrobot.greendao.AbstractDao;

import java.util.List;

import br.com.geodrone.GeoDroneApplication;
import br.com.geodrone.model.daoGen.DaoSession;

/**
 * Created by lucianoft on 15/01/2018.
 */

public abstract class CrudRepository<T, ID> {

    public AbstractDao<T, ID> dao = null;
    DaoSession daoSession = null;

    public CrudRepository(Activity activity){
        daoSession = ((GeoDroneApplication)activity.getApplication()).getDaoSession();
    }

    public abstract AbstractDao<T, ID> getCrudDao();

    public DaoSession getDaoSession(){
        return daoSession;
    }

    public T findById(ID id) {
        return getCrudDao().load(id);
    }

    public T insert(T T) {
        getCrudDao().save(T);
        return T;
    }

    public T update(T T) {
        getCrudDao().update(T);
        return T;
    }

    public void delete(T T) {
        getCrudDao().delete(T);
    }

    public List<T> findAll(){
        return getCrudDao().loadAll();
    }
}
