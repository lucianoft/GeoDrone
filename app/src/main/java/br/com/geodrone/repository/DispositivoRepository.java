package br.com.geodrone.repository;

import android.content.Context;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

import br.com.geodrone.model.Dispositivo;
import br.com.geodrone.model.Usuario;
import br.com.geodrone.model.daoGen.UsuarioDao;

/**
 * Created by fernandes on 09/04/2018.
 */
public class DispositivoRepository extends CrudRepository<Dispositivo, Long>{
    public DispositivoRepository(Context ctx) {
        super(ctx);
    }

    @Override
    public AbstractDao<Dispositivo, Long> getCrudDao() {
        return getDaoSession().getDispositivoDao();
    }

    public Dispositivo findOne() {
        QueryBuilder<Dispositivo> qrBuilder = getCrudDao().queryBuilder();
        return qrBuilder.unique();
    }
}

