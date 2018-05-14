package br.com.geodrone.repository;

import android.content.Context;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.Date;
import java.util.List;

import br.com.geodrone.model.RegistroPraga;
import br.com.geodrone.model.daoGen.RegistroPragaDao;

/**
 * Created by fernandes on 30/03/2018.
 */

public class RegistroPragaRepository extends CrudRepository<RegistroPraga, Long>{
    public RegistroPragaRepository(Context ctx) {
        super(ctx);
    }

    @Override
    public AbstractDao<RegistroPraga, Long> getCrudDao() {
        return getDaoSession().getRegistroPragaDao();
    }


    public List<RegistroPraga> findAllByClienteToSincronizar(Long IdClienteRef, Date dtSincronizacaoErp ) {
        QueryBuilder<RegistroPraga> qb = getCrudDao().queryBuilder();
        qb.where(RegistroPragaDao.Properties.IdCliente.eq(IdClienteRef),
                RegistroPragaDao.Properties.DtAlteracao.gt(dtSincronizacaoErp));
        return qb.list();
    }

}
