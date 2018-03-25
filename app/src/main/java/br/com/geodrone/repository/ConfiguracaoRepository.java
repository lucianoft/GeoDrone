package br.com.geodrone.repository;

import android.content.Context;

import org.greenrobot.greendao.AbstractDao;

import br.com.geodrone.model.Configuracao;

/**
 * Created by fernandes on 25/03/2018.
 */

public class ConfiguracaoRepository extends CrudRepository<Configuracao, Long>{
    public ConfiguracaoRepository(Context ctx) {
        super(ctx);
    }

    @Override
    public AbstractDao<Configuracao, Long> getCrudDao() {
        return getDaoSession().getConfiguracaoDao();
    }
}
