package br.com.geodrone.repository;

import android.content.Context;

import org.greenrobot.greendao.AbstractDao;

import br.com.geodrone.model.RegistroDoenca;

/**
 * Created by fernandes on 30/03/2018.
 */

public class RegistroDoencaRepository extends CrudRepository<RegistroDoenca, Long>{
    public RegistroDoencaRepository(Context ctx) {
        super(ctx);
    }

    @Override
    public AbstractDao<RegistroDoenca, Long> getCrudDao() {
        return getDaoSession().getRegistroDoencaDao();
    }

}
