package br.com.geodrone.repository;

import android.content.Context;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import br.com.geodrone.model.Praga;
import br.com.geodrone.model.Usuario;
import br.com.geodrone.model.daoGen.PragaDao;
import br.com.geodrone.model.daoGen.UsuarioDao;

/**
 * Created by fernandes on 27/03/2018.
 */

public class PragaRepository extends CrudRepository<Praga, Long>{
    public PragaRepository(Context ctx) {
        super(ctx);
    }

    @Override
    public AbstractDao<Praga, Long> getCrudDao() {
        return getDaoSession().getPragaDao();
    }

    public List<Praga> findAll() {
        QueryBuilder<Praga> qrBuilder = getDaoSession().getPragaDao().queryBuilder()
                .orderAsc(PragaDao.Properties.Descricao);

        return qrBuilder.list();
    }

    public List<Praga> findByTipoCultivo(Long IdTipoCultivoRef) {
        QueryBuilder<Praga> qrBuilder = getDaoSession().getPragaDao().queryBuilder()
                .where(PragaDao.Properties.IdTipoCultivoRef.eq(IdTipoCultivoRef))
                .orderAsc(PragaDao.Properties.Descricao);

        return qrBuilder.list();
    }
}