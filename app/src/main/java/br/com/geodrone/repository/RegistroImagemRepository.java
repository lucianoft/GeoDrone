package br.com.geodrone.repository;

import android.content.Context;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.Date;
import java.util.List;

import br.com.geodrone.model.RegistroImagem;
import br.com.geodrone.model.daoGen.RegistroImagemDao;

/**
 * Created by fernandes on 01/04/2018.
 */
public class RegistroImagemRepository extends CrudRepository<RegistroImagem, Long>{
    public RegistroImagemRepository(Context ctx) {
        super(ctx);
    }

    @Override
    public AbstractDao<RegistroImagem, Long> getCrudDao() {
        return getDaoSession().getRegistroImagemDao();
    }


    public List<RegistroImagem> findAllByClienteToSincronizar(Long IdClienteRef, Date dtSincronizacaoErp ) {
        //QueryBuilder.LOG_SQL = true;
        //QueryBuilder.LOG_VALUES = true;
        QueryBuilder<RegistroImagem> qb = getCrudDao().queryBuilder();
        qb.where(RegistroImagemDao.Properties.IdCliente.eq(IdClienteRef),
                RegistroImagemDao.Properties.DtAlteracao.gt(dtSincronizacaoErp));
        return qb.list();
    }
}
