package br.com.geodrone.repository;

import android.content.Context;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

import br.com.geodrone.model.Doenca;
import br.com.geodrone.model.daoGen.DoencaDao;

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

    public Doenca findOne(Long idDoenca) {
        QueryBuilder<Doenca> qrBuilder = getCrudDao().queryBuilder().where(DoencaDao.Properties.Id.eq(idDoenca));
        return qrBuilder.unique();
    }
}
