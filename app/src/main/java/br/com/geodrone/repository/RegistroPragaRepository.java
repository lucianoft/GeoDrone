package br.com.geodrone.repository;

import android.content.Context;

import org.greenrobot.greendao.AbstractDao;

import br.com.geodrone.model.RegistroPraga;

/**
 * Created by fernandes on 30/03/2018.
 */

public class RegistroPragaRepository extends CrudRepository<RegistroPraga, Long>{
    public RegistroPragaRepository(Context ctx) {
        super(ctx);
    }

    @Override
    public AbstractDao<RegistroPraga, Long> getCrudDao() {
        return getDaoSession().getRegistroPragaDao();
    }

}
