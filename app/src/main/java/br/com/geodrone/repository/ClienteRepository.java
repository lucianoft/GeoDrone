package br.com.geodrone.repository;

import android.content.Context;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.ClienteUsuario;
import br.com.geodrone.model.daoGen.ClienteDao;
import br.com.geodrone.model.daoGen.ClienteUsuarioDao;

/**
 * Created by fernandes on 25/03/2018.
 */

public class ClienteRepository extends CrudRepository<Cliente, Long>{
    public ClienteRepository(Context ctx) {
        super(ctx);
    }

    @Override
    public AbstractDao<Cliente, Long> getCrudDao() {
        return getDaoSession().getClienteDao();
    }

    public List<Cliente> findAllByIds(List<Long> idClientes) {
        QueryBuilder<Cliente> qrBuilder = getCrudDao()
                .queryBuilder()
                .where(ClienteDao.Properties.Id.in(idClientes))
                .orderAsc(ClienteDao.Properties.NomeRazaoSocial);
        return qrBuilder.list();
    }
}
