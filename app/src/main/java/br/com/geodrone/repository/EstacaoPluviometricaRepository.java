package br.com.geodrone.repository;

import android.content.Context;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import br.com.geodrone.model.EstacaoPluviometrica;
import br.com.geodrone.model.Usuario;
import br.com.geodrone.model.daoGen.EstacaoPluviometricaDao;
import br.com.geodrone.model.daoGen.UsuarioDao;

/**
 * Created by fernandes on 20/03/2018.
 */

public class EstacaoPluviometricaRepository extends CrudRepository<EstacaoPluviometrica, Long>{
    public EstacaoPluviometricaRepository(Context ctx) {
        super(ctx);
    }

    @Override
    public AbstractDao<EstacaoPluviometrica, Long> getCrudDao() {
        return getDaoSession().getEstacaoPluviometricaDao();
    }

    public List<EstacaoPluviometrica> findAllByCliente(Long idCliente) {
        QueryBuilder<EstacaoPluviometrica> qrBuilder = getCrudDao()
                                                          .queryBuilder()
                                                          .where(EstacaoPluviometricaDao.Properties.IdCliente.eq(idCliente));
        return qrBuilder.list();
    }
}

