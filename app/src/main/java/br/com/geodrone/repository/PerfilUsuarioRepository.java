package br.com.geodrone.repository;

import android.content.Context;

import org.greenrobot.greendao.AbstractDao;

import br.com.geodrone.model.PerfilUsuario;

/**
 * Created by fernandes on 25/03/2018.
 */

public class PerfilUsuarioRepository extends CrudRepository<PerfilUsuario, Long>{
    public PerfilUsuarioRepository(Context ctx) {
        super(ctx);
    }

    @Override
    public AbstractDao<PerfilUsuario, Long> getCrudDao() {
        return getDaoSession().getPerfilUsuarioDao();
    }
}
