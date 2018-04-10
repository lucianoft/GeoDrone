package br.com.geodrone.repository;

import android.app.Activity;
import android.content.Context;


import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

import br.com.geodrone.model.Usuario;
import br.com.geodrone.model.daoGen.UsuarioDao;

public class UsuarioRepository extends CrudRepository<Usuario, Long>{

    public UsuarioRepository(Context ctx) {
        super(ctx);
    }

    @Override
    public AbstractDao<Usuario, Long> getCrudDao() {
        return getDaoSession().getUsuarioDao();
    }

    public Usuario findByEmail(String email) {
        QueryBuilder<Usuario> qrBuilder = getCrudDao().queryBuilder().where(UsuarioDao.Properties.Email.eq(email));
        return qrBuilder.unique();
    }
}
