package br.com.geodrone.model.daoGen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.DadosPluviometricos;
import br.com.geodrone.model.EstacaoPluviometrica;
import br.com.geodrone.model.PerfilUsuario;
import br.com.geodrone.model.Usuario;

import br.com.geodrone.model.daoGen.ClienteDao;
import br.com.geodrone.model.daoGen.DadosPluviometricosDao;
import br.com.geodrone.model.daoGen.EstacaoPluviometricaDao;
import br.com.geodrone.model.daoGen.PerfilUsuarioDao;
import br.com.geodrone.model.daoGen.UsuarioDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig clienteDaoConfig;
    private final DaoConfig dadosPluviometricosDaoConfig;
    private final DaoConfig estacaoPluviometricaDaoConfig;
    private final DaoConfig perfilUsuarioDaoConfig;
    private final DaoConfig usuarioDaoConfig;

    private final ClienteDao clienteDao;
    private final DadosPluviometricosDao dadosPluviometricosDao;
    private final EstacaoPluviometricaDao estacaoPluviometricaDao;
    private final PerfilUsuarioDao perfilUsuarioDao;
    private final UsuarioDao usuarioDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        clienteDaoConfig = daoConfigMap.get(ClienteDao.class).clone();
        clienteDaoConfig.initIdentityScope(type);

        dadosPluviometricosDaoConfig = daoConfigMap.get(DadosPluviometricosDao.class).clone();
        dadosPluviometricosDaoConfig.initIdentityScope(type);

        estacaoPluviometricaDaoConfig = daoConfigMap.get(EstacaoPluviometricaDao.class).clone();
        estacaoPluviometricaDaoConfig.initIdentityScope(type);

        perfilUsuarioDaoConfig = daoConfigMap.get(PerfilUsuarioDao.class).clone();
        perfilUsuarioDaoConfig.initIdentityScope(type);

        usuarioDaoConfig = daoConfigMap.get(UsuarioDao.class).clone();
        usuarioDaoConfig.initIdentityScope(type);

        clienteDao = new ClienteDao(clienteDaoConfig, this);
        dadosPluviometricosDao = new DadosPluviometricosDao(dadosPluviometricosDaoConfig, this);
        estacaoPluviometricaDao = new EstacaoPluviometricaDao(estacaoPluviometricaDaoConfig, this);
        perfilUsuarioDao = new PerfilUsuarioDao(perfilUsuarioDaoConfig, this);
        usuarioDao = new UsuarioDao(usuarioDaoConfig, this);

        registerDao(Cliente.class, clienteDao);
        registerDao(DadosPluviometricos.class, dadosPluviometricosDao);
        registerDao(EstacaoPluviometrica.class, estacaoPluviometricaDao);
        registerDao(PerfilUsuario.class, perfilUsuarioDao);
        registerDao(Usuario.class, usuarioDao);
    }
    
    public void clear() {
        clienteDaoConfig.clearIdentityScope();
        dadosPluviometricosDaoConfig.clearIdentityScope();
        estacaoPluviometricaDaoConfig.clearIdentityScope();
        perfilUsuarioDaoConfig.clearIdentityScope();
        usuarioDaoConfig.clearIdentityScope();
    }

    public ClienteDao getClienteDao() {
        return clienteDao;
    }

    public DadosPluviometricosDao getDadosPluviometricosDao() {
        return dadosPluviometricosDao;
    }

    public EstacaoPluviometricaDao getEstacaoPluviometricaDao() {
        return estacaoPluviometricaDao;
    }

    public PerfilUsuarioDao getPerfilUsuarioDao() {
        return perfilUsuarioDao;
    }

    public UsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

}
