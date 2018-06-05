package br.com.geodrone.repository;

import android.content.Context;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

import br.com.geodrone.model.TipoDefensivo;
import br.com.geodrone.model.daoGen.TipoDefensivoDao;

/**
 * Created by fernandes on 04/06/2018.
 */

public class TipoDefensivoRepository extends CrudRepository<TipoDefensivo, Long>{
    public TipoDefensivoRepository(Context ctx) {
        super(ctx);
    }

    @Override
    public AbstractDao<TipoDefensivo, Long> getCrudDao() {
        return getDaoSession().getTipoDefensivoDao();
    }

    public TipoDefensivo findOne(Long idTipoDefensivo) {
        QueryBuilder<TipoDefensivo> qrBuilder = getCrudDao().queryBuilder().where(TipoDefensivoDao.Properties.Id.eq(idTipoDefensivo));
        return qrBuilder.unique();
    }
}