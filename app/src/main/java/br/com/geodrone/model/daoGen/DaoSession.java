package br.com.geodrone.model.daoGen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.PerfilUsuario;
import br.com.geodrone.model.Usuario;
import br.com.geodrone.model.Configuracao;
import br.com.geodrone.model.RotaTrabalho;
import br.com.geodrone.model.Doenca;
import br.com.geodrone.model.PontoColetaChuva;
import br.com.geodrone.model.Praga;
import br.com.geodrone.model.RegistroChuva;
import br.com.geodrone.model.TipoCultivo;

import br.com.geodrone.model.daoGen.ClienteDao;
import br.com.geodrone.model.daoGen.PerfilUsuarioDao;
import br.com.geodrone.model.daoGen.UsuarioDao;
import br.com.geodrone.model.daoGen.ConfiguracaoDao;
import br.com.geodrone.model.daoGen.RotaTrabalhoDao;
import br.com.geodrone.model.daoGen.DoencaDao;
import br.com.geodrone.model.daoGen.PontoColetaChuvaDao;
import br.com.geodrone.model.daoGen.PragaDao;
import br.com.geodrone.model.daoGen.RegistroChuvaDao;
import br.com.geodrone.model.daoGen.TipoCultivoDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig clienteDaoConfig;
    private final DaoConfig perfilUsuarioDaoConfig;
    private final DaoConfig usuarioDaoConfig;
    private final DaoConfig configuracaoDaoConfig;
    private final DaoConfig rotaTrabalhoDaoConfig;
    private final DaoConfig doencaDaoConfig;
    private final DaoConfig pontoColetaChuvaDaoConfig;
    private final DaoConfig pragaDaoConfig;
    private final DaoConfig registroChuvaDaoConfig;
    private final DaoConfig tipoCultivoDaoConfig;

    private final ClienteDao clienteDao;
    private final PerfilUsuarioDao perfilUsuarioDao;
    private final UsuarioDao usuarioDao;
    private final ConfiguracaoDao configuracaoDao;
    private final RotaTrabalhoDao rotaTrabalhoDao;
    private final DoencaDao doencaDao;
    private final PontoColetaChuvaDao pontoColetaChuvaDao;
    private final PragaDao pragaDao;
    private final RegistroChuvaDao registroChuvaDao;
    private final TipoCultivoDao tipoCultivoDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        clienteDaoConfig = daoConfigMap.get(ClienteDao.class).clone();
        clienteDaoConfig.initIdentityScope(type);

        perfilUsuarioDaoConfig = daoConfigMap.get(PerfilUsuarioDao.class).clone();
        perfilUsuarioDaoConfig.initIdentityScope(type);

        usuarioDaoConfig = daoConfigMap.get(UsuarioDao.class).clone();
        usuarioDaoConfig.initIdentityScope(type);

        configuracaoDaoConfig = daoConfigMap.get(ConfiguracaoDao.class).clone();
        configuracaoDaoConfig.initIdentityScope(type);

        rotaTrabalhoDaoConfig = daoConfigMap.get(RotaTrabalhoDao.class).clone();
        rotaTrabalhoDaoConfig.initIdentityScope(type);

        doencaDaoConfig = daoConfigMap.get(DoencaDao.class).clone();
        doencaDaoConfig.initIdentityScope(type);

        pontoColetaChuvaDaoConfig = daoConfigMap.get(PontoColetaChuvaDao.class).clone();
        pontoColetaChuvaDaoConfig.initIdentityScope(type);

        pragaDaoConfig = daoConfigMap.get(PragaDao.class).clone();
        pragaDaoConfig.initIdentityScope(type);

        registroChuvaDaoConfig = daoConfigMap.get(RegistroChuvaDao.class).clone();
        registroChuvaDaoConfig.initIdentityScope(type);

        tipoCultivoDaoConfig = daoConfigMap.get(TipoCultivoDao.class).clone();
        tipoCultivoDaoConfig.initIdentityScope(type);

        clienteDao = new ClienteDao(clienteDaoConfig, this);
        perfilUsuarioDao = new PerfilUsuarioDao(perfilUsuarioDaoConfig, this);
        usuarioDao = new UsuarioDao(usuarioDaoConfig, this);
        configuracaoDao = new ConfiguracaoDao(configuracaoDaoConfig, this);
        rotaTrabalhoDao = new RotaTrabalhoDao(rotaTrabalhoDaoConfig, this);
        doencaDao = new DoencaDao(doencaDaoConfig, this);
        pontoColetaChuvaDao = new PontoColetaChuvaDao(pontoColetaChuvaDaoConfig, this);
        pragaDao = new PragaDao(pragaDaoConfig, this);
        registroChuvaDao = new RegistroChuvaDao(registroChuvaDaoConfig, this);
        tipoCultivoDao = new TipoCultivoDao(tipoCultivoDaoConfig, this);

        registerDao(Cliente.class, clienteDao);
        registerDao(PerfilUsuario.class, perfilUsuarioDao);
        registerDao(Usuario.class, usuarioDao);
        registerDao(Configuracao.class, configuracaoDao);
        registerDao(RotaTrabalho.class, rotaTrabalhoDao);
        registerDao(Doenca.class, doencaDao);
        registerDao(PontoColetaChuva.class, pontoColetaChuvaDao);
        registerDao(Praga.class, pragaDao);
        registerDao(RegistroChuva.class, registroChuvaDao);
        registerDao(TipoCultivo.class, tipoCultivoDao);
    }
    
    public void clear() {
        clienteDaoConfig.clearIdentityScope();
        perfilUsuarioDaoConfig.clearIdentityScope();
        usuarioDaoConfig.clearIdentityScope();
        configuracaoDaoConfig.clearIdentityScope();
        rotaTrabalhoDaoConfig.clearIdentityScope();
        doencaDaoConfig.clearIdentityScope();
        pontoColetaChuvaDaoConfig.clearIdentityScope();
        pragaDaoConfig.clearIdentityScope();
        registroChuvaDaoConfig.clearIdentityScope();
        tipoCultivoDaoConfig.clearIdentityScope();
    }

    public ClienteDao getClienteDao() {
        return clienteDao;
    }

    public PerfilUsuarioDao getPerfilUsuarioDao() {
        return perfilUsuarioDao;
    }

    public UsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

    public ConfiguracaoDao getConfiguracaoDao() {
        return configuracaoDao;
    }

    public RotaTrabalhoDao getRotaTrabalhoDao() {
        return rotaTrabalhoDao;
    }

    public DoencaDao getDoencaDao() {
        return doencaDao;
    }

    public PontoColetaChuvaDao getPontoColetaChuvaDao() {
        return pontoColetaChuvaDao;
    }

    public PragaDao getPragaDao() {
        return pragaDao;
    }

    public RegistroChuvaDao getRegistroChuvaDao() {
        return registroChuvaDao;
    }

    public TipoCultivoDao getTipoCultivoDao() {
        return tipoCultivoDao;
    }

}
