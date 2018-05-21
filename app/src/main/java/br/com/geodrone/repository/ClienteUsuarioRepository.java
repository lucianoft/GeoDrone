package br.com.geodrone.repository;

import android.content.Context;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import br.com.geodrone.model.ClienteUsuario;
import br.com.geodrone.model.daoGen.ClienteUsuarioDao;

/**
 * Created by fernandes on 19/05/2018.
 */
public class ClienteUsuarioRepository extends CrudRepository<ClienteUsuario, Long>{
    public ClienteUsuarioRepository(Context ctx) {
        super(ctx);
    }

    @Override
    public AbstractDao<ClienteUsuario, Long> getCrudDao() {
        return getDaoSession().getClienteUsuarioDao();
    }

    public List<ClienteUsuario> findAllByUsuario(Long idUsuario) {
        QueryBuilder<ClienteUsuario> qrBuilder = getCrudDao()
                .queryBuilder()
                .where(ClienteUsuarioDao.Properties.IdUsuario.eq(idUsuario));
        return qrBuilder.list();
    }

    public ClienteUsuario findAllByUsuarioAndCliente(Long idUsuario, Long idCliente) {
        QueryBuilder<ClienteUsuario> qrBuilder = getCrudDao()
                .queryBuilder()
                .where(ClienteUsuarioDao.Properties.IdUsuario.eq(idUsuario),
                        ClienteUsuarioDao.Properties.IdCliente.eq(idCliente) );
        return qrBuilder.limit(1).unique();
    }
}
