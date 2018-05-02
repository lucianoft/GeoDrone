package br.com.geodrone.repository;

import android.content.Context;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

import br.com.geodrone.model.Doenca;
import br.com.geodrone.model.Praga;
import br.com.geodrone.model.RegistroChuva;
import br.com.geodrone.model.daoGen.DoencaDao;
import br.com.geodrone.model.daoGen.RegistroChuvaDao;

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

    public RegistroChuva findOneByPontoColetaChuva(Long idPontoColetaChuvaDisp) {
        QueryBuilder<RegistroChuva> qrBuilder = getCrudDao().queryBuilder()
                                                     .where(RegistroChuvaDao.Properties.IdPontoColetaChuvaDisp.eq(idPontoColetaChuvaDisp))
                                                     .orderDesc(RegistroChuvaDao.Properties.DtRegistro)
                                                     .limit(1);
        return qrBuilder.unique();
    }

}
