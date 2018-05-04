package br.com.geodrone.repository;

import android.content.Context;

import org.greenrobot.greendao.AbstractDao;

import br.com.geodrone.model.RegistroCondicaoTempo;

/**
 * Created by fernandes on 03/05/2018.
 */

public class RegistroCondicoesTempoRepository extends CrudRepository<RegistroCondicaoTempo, Long>{
    public RegistroCondicoesTempoRepository(Context ctx) {
        super(ctx);
    }

    @Override
    public AbstractDao<RegistroCondicaoTempo, Long> getCrudDao() {
        return getDaoSession().getRegistroCondicaoTempoDao();
    }

}
