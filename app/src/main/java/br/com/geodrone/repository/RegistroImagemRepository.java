package br.com.geodrone.repository;

import android.content.Context;

import org.greenrobot.greendao.AbstractDao;

import br.com.geodrone.model.RegistroImagem;

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

}
