package br.com.geodrone.repository;

import android.content.Context;

import org.greenrobot.greendao.AbstractDao;

import br.com.geodrone.model.Praga;
import br.com.geodrone.model.RegistroChuva;

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

}
