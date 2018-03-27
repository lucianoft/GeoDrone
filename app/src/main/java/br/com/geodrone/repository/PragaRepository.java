package br.com.geodrone.repository;

import android.content.Context;

import org.greenrobot.greendao.AbstractDao;

import br.com.geodrone.model.Praga;

/**
 * Created by fernandes on 27/03/2018.
 */

public class PragaRepository extends CrudRepository<Praga, Long>{
    public PragaRepository(Context ctx) {
        super(ctx);
    }

    @Override
    public AbstractDao<Praga, Long> getCrudDao() {
        return getDaoSession().getPragaDao();
    }
}