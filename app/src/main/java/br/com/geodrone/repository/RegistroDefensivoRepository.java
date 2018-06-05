package br.com.geodrone.repository;

import android.content.Context;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.Date;
import java.util.List;

import br.com.geodrone.model.RegistroDefensivo;
import br.com.geodrone.model.daoGen.RegistroDefensivoDao;

/**
 * Created by fernandes on 04/06/2018.
 */

public class RegistroDefensivoRepository extends CrudRepository<RegistroDefensivo, Long>{
    public RegistroDefensivoRepository(Context ctx) {
        super(ctx);
    }

    @Override
    public AbstractDao<RegistroDefensivo, Long> getCrudDao() {
        return getDaoSession().getRegistroDefensivoDao();
    }


    public List<RegistroDefensivo> findAllByClienteToSincronizar(Long IdClienteRef, Date dtSincronizacaoErp ) {
        QueryBuilder<RegistroDefensivo> qb = getCrudDao().queryBuilder();
        qb.where(RegistroDefensivoDao.Properties.IdCliente.eq(IdClienteRef),
                RegistroDefensivoDao.Properties.DtAlteracao.gt(dtSincronizacaoErp));
        return qb.list();
    }
}