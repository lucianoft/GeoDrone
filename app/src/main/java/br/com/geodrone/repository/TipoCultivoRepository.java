package br.com.geodrone.repository;

import android.content.Context;

import org.greenrobot.greendao.AbstractDao;

import br.com.geodrone.model.TipoCultivo;

/**
 * Created by fernandes on 27/03/2018.
 */

public class TipoCultivoRepository extends CrudRepository<TipoCultivo, Long>{
    public TipoCultivoRepository(Context ctx) {
        super(ctx);
    }

    @Override
    public AbstractDao<TipoCultivo, Long> getCrudDao() {
        return getDaoSession().getTipoCultivoDao();
    }
}
