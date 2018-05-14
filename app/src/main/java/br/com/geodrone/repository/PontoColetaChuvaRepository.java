package br.com.geodrone.repository;

import android.content.Context;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.Date;
import java.util.List;

import br.com.geodrone.model.PontoColetaChuva;
import br.com.geodrone.model.daoGen.PontoColetaChuvaDao;

/**
 * Created by fernandes on 20/03/2018.
 */

public class PontoColetaChuvaRepository extends CrudRepository<PontoColetaChuva, Long>{
    public PontoColetaChuvaRepository(Context ctx) {
        super(ctx);
    }

    @Override
    public AbstractDao<PontoColetaChuva, Long> getCrudDao() {
        return getDaoSession().getPontoColetaChuvaDao();
    }

    public List<PontoColetaChuva> findAllByCliente(Long IdClienteRef) {
        QueryBuilder<PontoColetaChuva> qrBuilder = getCrudDao()
                                                          .queryBuilder()
                                                          .where(PontoColetaChuvaDao.Properties.IdCliente.eq(IdClienteRef));
        return qrBuilder.list();
    }

    public PontoColetaChuva findByIdPontoColetaChuvaWeb(Long idPontoColetaChuva) {
        QueryBuilder<PontoColetaChuva> qrBuilder = getCrudDao()
                .queryBuilder()
                .where(PontoColetaChuvaDao.Properties.IdPontoColetaChuva.eq(idPontoColetaChuva))
                .limit(1);
        return qrBuilder.unique();
    }

    public List<PontoColetaChuva> findAllByClienteToSincronizar(Long IdClienteRef, Date dtSincronizacaoErp ) {
        QueryBuilder<PontoColetaChuva> qb = getCrudDao().queryBuilder();
        qb.where(PontoColetaChuvaDao.Properties.IdCliente.eq(IdClienteRef),
                 PontoColetaChuvaDao.Properties.DtAlteracao.gt(dtSincronizacaoErp));
        return qb.list();
    }
}

