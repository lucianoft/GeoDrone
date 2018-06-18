package br.com.geodrone.repository;

import android.content.Context;

import org.greenrobot.greendao.AbstractDao;

import br.com.geodrone.model.DefensivoQuimico;

/**
 * Created by fernandes on 17/06/2018.
 */
public class DefensivoQuimicoRepository extends CrudRepository<DefensivoQuimico, Long>{
    public DefensivoQuimicoRepository(Context ctx) {
        super(ctx);
    }

    @Override
    public AbstractDao<DefensivoQuimico, Long> getCrudDao() {
        return getDaoSession().getDefensivoQuimicoDao();
    }
}
