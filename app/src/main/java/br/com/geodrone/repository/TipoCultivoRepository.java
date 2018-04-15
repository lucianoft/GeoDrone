package br.com.geodrone.repository;

import android.content.Context;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

import br.com.geodrone.model.TipoCultivo;
import br.com.geodrone.model.daoGen.TipoCultivoDao;

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

    public TipoCultivo findOne(Long idTipoCultivo) {
        QueryBuilder<TipoCultivo> qrBuilder = getCrudDao().queryBuilder().where(TipoCultivoDao.Properties.Id.eq(idTipoCultivo));
        return qrBuilder.unique();
    }
}
