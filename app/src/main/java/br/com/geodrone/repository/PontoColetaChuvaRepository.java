package br.com.geodrone.repository;

import android.content.Context;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

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
                                                          .where(PontoColetaChuvaDao.Properties.IdClienteRef.eq(IdClienteRef));
        return qrBuilder.list();
    }
}

