package br.com.geodrone.repository;

import android.content.Context;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.Date;
import java.util.List;

import br.com.geodrone.model.RegistroDoenca;
import br.com.geodrone.model.daoGen.RegistroDoencaDao;

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


    public List<RegistroDoenca> findAllByClienteToSincronizar(Long IdClienteRef, Date dtSincronizacaoErp ) {
        QueryBuilder<RegistroDoenca> qb = getCrudDao().queryBuilder();
        qb.where(RegistroDoencaDao.Properties.IdCliente.eq(IdClienteRef),
                RegistroDoencaDao.Properties.DtAlteracao.gt(dtSincronizacaoErp));
        return qb.list();
    }
}
