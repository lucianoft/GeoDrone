package br.com.geodrone.repository;

import android.content.Context;

import org.greenrobot.greendao.AbstractDao;

import br.com.geodrone.model.Doenca;

/**
 * Created by fernandes on 27/03/2018.
 */

public class DoencaRepository extends CrudRepository<Doenca, Long>{
    public DoencaRepository(Context ctx) {
        super(ctx);
    }

    @Override
    public AbstractDao<Doenca, Long> getCrudDao() {
        return getDaoSession().getDoencaDao();
    }
}
