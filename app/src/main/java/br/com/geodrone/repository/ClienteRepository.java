package br.com.geodrone.repository;

import android.content.Context;

import org.greenrobot.greendao.AbstractDao;

import br.com.geodrone.model.Cliente;

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
}
