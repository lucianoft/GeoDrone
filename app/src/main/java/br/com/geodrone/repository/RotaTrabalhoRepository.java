package br.com.geodrone.repository;

import android.content.Context;

import org.greenrobot.greendao.AbstractDao;

import br.com.geodrone.model.RotaTrabalho;

/**
 * Created by fernandes on 25/03/2018.
 */

public class RotaTrabalhoRepository extends CrudRepository<RotaTrabalho, Long>{
    public RotaTrabalhoRepository(Context ctx) {
        super(ctx);
    }

    @Override
    public AbstractDao<RotaTrabalho, Long> getCrudDao() {
        return getDaoSession().getRotaTrabalhoDao();
    }
}
