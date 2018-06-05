package br.com.geodrone.repository;

import android.content.Context;

import org.greenrobot.greendao.AbstractDao;

import br.com.geodrone.model.EstagioInfestacao;

/**
 * Created by fernandes on 04/06/2018.
 */

public class EstagioInfestacaoRepository extends CrudRepository<EstagioInfestacao, Long>{
    public EstagioInfestacaoRepository(Context ctx) {
        super(ctx);
    }

    @Override
    public AbstractDao<EstagioInfestacao, Long> getCrudDao() {
        return getDaoSession().getEstagioInfestacaoDao();
    }
}
