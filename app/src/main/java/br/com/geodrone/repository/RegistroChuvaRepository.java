package br.com.geodrone.repository;

import android.content.Context;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.Date;
import java.util.List;

import br.com.geodrone.model.RegistroChuva;
import br.com.geodrone.model.daoGen.RegistroChuvaDao;

/**
 * Created by fernandes on 30/03/2018.
 */

public class RegistroChuvaRepository extends CrudRepository<RegistroChuva, Long>{
    public RegistroChuvaRepository(Context ctx) {
        super(ctx);
    }

    @Override
    public AbstractDao<RegistroChuva, Long> getCrudDao() {
        return getDaoSession().getRegistroChuvaDao();
    }

    public RegistroChuva findOneByPontoColetaChuva(Long idPontoColetaChuvaDisp) {
        QueryBuilder<RegistroChuva> qrBuilder = getCrudDao().queryBuilder()
                                                     .where(RegistroChuvaDao.Properties.IdPontoColetaChuvaDisp.eq(idPontoColetaChuvaDisp))
                                                     .orderDesc(RegistroChuvaDao.Properties.DtRegistro)
                                                     .limit(1);
        return qrBuilder.unique();
    }

    public List<RegistroChuva> findAllByClienteToSincronizar(Long IdClienteRef, Date dtSincronizacaoErp ) {
        QueryBuilder<RegistroChuva> qb = getCrudDao().queryBuilder();
        qb.where(RegistroChuvaDao.Properties.IdCliente.eq(IdClienteRef),
                RegistroChuvaDao.Properties.DtAlteracao.gt(dtSincronizacaoErp));
        return qb.list();
    }
}
