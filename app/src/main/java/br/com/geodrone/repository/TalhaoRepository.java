package br.com.geodrone.repository;

import android.content.Context;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import br.com.geodrone.model.Talhao;
import br.com.geodrone.model.daoGen.TalhaoDao;

/**
 * Created by luciano on 31/05/2018.
 */
public class TalhaoRepository extends CrudRepository<Talhao, Long>{
    public TalhaoRepository(Context ctx) {
        super(ctx);
    }

    @Override
    public AbstractDao<Talhao, Long> getCrudDao() {
        return getDaoSession().getTalhaoDao();
    }

    public List<Talhao> findAllByCliente(Long IdClienteRef) {
        QueryBuilder<Talhao> qrBuilder = getCrudDao()
                .queryBuilder()
                .where(TalhaoDao.Properties.IdCliente.eq(IdClienteRef));
        return qrBuilder.list();
    }
}
