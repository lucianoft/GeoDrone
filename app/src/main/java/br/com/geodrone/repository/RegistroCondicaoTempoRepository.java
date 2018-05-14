package br.com.geodrone.repository;

import android.content.Context;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.Date;
import java.util.List;

import br.com.geodrone.model.RegistroCondicaoTempo;
import br.com.geodrone.model.daoGen.RegistroCondicaoTempoDao;

/**
 * Created by fernandes on 03/05/2018.
 */

public class RegistroCondicaoTempoRepository extends CrudRepository<RegistroCondicaoTempo, Long>{
    public RegistroCondicaoTempoRepository(Context ctx) {
        super(ctx);
    }

    @Override
    public AbstractDao<RegistroCondicaoTempo, Long> getCrudDao() {
        return getDaoSession().getRegistroCondicaoTempoDao();
    }

    public List<RegistroCondicaoTempo> findAllByClienteToSincronizar(Long IdClienteRef, Date dtSincronizacaoErp ) {
        QueryBuilder<RegistroCondicaoTempo> qb = getCrudDao().queryBuilder();
        qb.where(RegistroCondicaoTempoDao.Properties.IdCliente.eq(IdClienteRef),
                RegistroCondicaoTempoDao.Properties.DtAlteracao.gt(dtSincronizacaoErp));
        return qb.list();
    }
}
