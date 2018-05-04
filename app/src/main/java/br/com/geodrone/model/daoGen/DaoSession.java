package br.com.geodrone.model.daoGen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.Configuracao;
import br.com.geodrone.model.Dispositivo;
import br.com.geodrone.model.Doenca;
import br.com.geodrone.model.PontoColetaChuva;
import br.com.geodrone.model.Praga;
import br.com.geodrone.model.RegistroChuva;
import br.com.geodrone.model.RegistroCondicaoTempo;
import br.com.geodrone.model.RegistroDoenca;
import br.com.geodrone.model.RegistroImagem;
import br.com.geodrone.model.RegistroPraga;
import br.com.geodrone.model.RotaTrabalho;
import br.com.geodrone.model.TipoCultivo;
import br.com.geodrone.model.Usuario;

import br.com.geodrone.model.daoGen.ClienteDao;
import br.com.geodrone.model.daoGen.ConfiguracaoDao;
import br.com.geodrone.model.daoGen.DispositivoDao;
import br.com.geodrone.model.daoGen.DoencaDao;
import br.com.geodrone.model.daoGen.PontoColetaChuvaDao;
import br.com.geodrone.model.daoGen.PragaDao;
import br.com.geodrone.model.daoGen.RegistroChuvaDao;
import br.com.geodrone.model.daoGen.RegistroCondicaoTempoDao;
import br.com.geodrone.model.daoGen.RegistroDoencaDao;
import br.com.geodrone.model.daoGen.RegistroImagemDao;
import br.com.geodrone.model.daoGen.RegistroPragaDao;
import br.com.geodrone.model.daoGen.RotaTrabalhoDao;
import br.com.geodrone.model.daoGen.TipoCultivoDao;
import br.com.geodrone.model.daoGen.UsuarioDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig clienteDaoConfig;
    private final DaoConfig configuracaoDaoConfig;
    private final DaoConfig dispositivoDaoConfig;
    private final DaoConfig doencaDaoConfig;
    private final DaoConfig pontoColetaChuvaDaoConfig;
    private final DaoConfig pragaDaoConfig;
    private final DaoConfig registroChuvaDaoConfig;
    private final DaoConfig registroCondicaoTempoDaoConfig;
    private final DaoConfig registroDoencaDaoConfig;
    private final DaoConfig registroImagemDaoConfig;
    private final DaoConfig registroPragaDaoConfig;
    private final DaoConfig rotaTrabalhoDaoConfig;
    private final DaoConfig tipoCultivoDaoConfig;
    private final DaoConfig usuarioDaoConfig;

    private final ClienteDao clienteDao;
    private final ConfiguracaoDao configuracaoDao;
    private final DispositivoDao dispositivoDao;
    private final DoencaDao doencaDao;
    private final PontoColetaChuvaDao pontoColetaChuvaDao;
    private final PragaDao pragaDao;
    private final RegistroChuvaDao registroChuvaDao;
    private final RegistroCondicaoTempoDao registroCondicaoTempoDao;
    private final RegistroDoencaDao registroDoencaDao;
    private final RegistroImagemDao registroImagemDao;
    private final RegistroPragaDao registroPragaDao;
    private final RotaTrabalhoDao rotaTrabalhoDao;
    private final TipoCultivoDao tipoCultivoDao;
    private final UsuarioDao usuarioDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        clienteDaoConfig = daoConfigMap.get(ClienteDao.class).clone();
        clienteDaoConfig.initIdentityScope(type);

        configuracaoDaoConfig = daoConfigMap.get(ConfiguracaoDao.class).clone();
        configuracaoDaoConfig.initIdentityScope(type);

        dispositivoDaoConfig = daoConfigMap.get(DispositivoDao.class).clone();
        dispositivoDaoConfig.initIdentityScope(type);

        doencaDaoConfig = daoConfigMap.get(DoencaDao.class).clone();
        doencaDaoConfig.initIdentityScope(type);

        pontoColetaChuvaDaoConfig = daoConfigMap.get(PontoColetaChuvaDao.class).clone();
        pontoColetaChuvaDaoConfig.initIdentityScope(type);

        pragaDaoConfig = daoConfigMap.get(PragaDao.class).clone();
        pragaDaoConfig.initIdentityScope(type);

        registroChuvaDaoConfig = daoConfigMap.get(RegistroChuvaDao.class).clone();
        registroChuvaDaoConfig.initIdentityScope(type);

        registroCondicaoTempoDaoConfig = daoConfigMap.get(RegistroCondicaoTempoDao.class).clone();
        registroCondicaoTempoDaoConfig.initIdentityScope(type);

        registroDoencaDaoConfig = daoConfigMap.get(RegistroDoencaDao.class).clone();
        registroDoencaDaoConfig.initIdentityScope(type);

        registroImagemDaoConfig = daoConfigMap.get(RegistroImagemDao.class).clone();
        registroImagemDaoConfig.initIdentityScope(type);

        registroPragaDaoConfig = daoConfigMap.get(RegistroPragaDao.class).clone();
        registroPragaDaoConfig.initIdentityScope(type);

        rotaTrabalhoDaoConfig = daoConfigMap.get(RotaTrabalhoDao.class).clone();
        rotaTrabalhoDaoConfig.initIdentityScope(type);

        tipoCultivoDaoConfig = daoConfigMap.get(TipoCultivoDao.class).clone();
        tipoCultivoDaoConfig.initIdentityScope(type);

        usuarioDaoConfig = daoConfigMap.get(UsuarioDao.class).clone();
        usuarioDaoConfig.initIdentityScope(type);

        clienteDao = new ClienteDao(clienteDaoConfig, this);
        configuracaoDao = new ConfiguracaoDao(configuracaoDaoConfig, this);
        dispositivoDao = new DispositivoDao(dispositivoDaoConfig, this);
        doencaDao = new DoencaDao(doencaDaoConfig, this);
        pontoColetaChuvaDao = new PontoColetaChuvaDao(pontoColetaChuvaDaoConfig, this);
        pragaDao = new PragaDao(pragaDaoConfig, this);
        registroChuvaDao = new RegistroChuvaDao(registroChuvaDaoConfig, this);
        registroCondicaoTempoDao = new RegistroCondicaoTempoDao(registroCondicaoTempoDaoConfig, this);
        registroDoencaDao = new RegistroDoencaDao(registroDoencaDaoConfig, this);
        registroImagemDao = new RegistroImagemDao(registroImagemDaoConfig, this);
        registroPragaDao = new RegistroPragaDao(registroPragaDaoConfig, this);
        rotaTrabalhoDao = new RotaTrabalhoDao(rotaTrabalhoDaoConfig, this);
        tipoCultivoDao = new TipoCultivoDao(tipoCultivoDaoConfig, this);
        usuarioDao = new UsuarioDao(usuarioDaoConfig, this);

        registerDao(Cliente.class, clienteDao);
        registerDao(Configuracao.class, configuracaoDao);
        registerDao(Dispositivo.class, dispositivoDao);
        registerDao(Doenca.class, doencaDao);
        registerDao(PontoColetaChuva.class, pontoColetaChuvaDao);
        registerDao(Praga.class, pragaDao);
        registerDao(RegistroChuva.class, registroChuvaDao);
        registerDao(RegistroCondicaoTempo.class, registroCondicaoTempoDao);
        registerDao(RegistroDoenca.class, registroDoencaDao);
        registerDao(RegistroImagem.class, registroImagemDao);
        registerDao(RegistroPraga.class, registroPragaDao);
        registerDao(RotaTrabalho.class, rotaTrabalhoDao);
        registerDao(TipoCultivo.class, tipoCultivoDao);
        registerDao(Usuario.class, usuarioDao);
    }
    
    public void clear() {
        clienteDaoConfig.clearIdentityScope();
        configuracaoDaoConfig.clearIdentityScope();
        dispositivoDaoConfig.clearIdentityScope();
        doencaDaoConfig.clearIdentityScope();
        pontoColetaChuvaDaoConfig.clearIdentityScope();
        pragaDaoConfig.clearIdentityScope();
        registroChuvaDaoConfig.clearIdentityScope();
        registroCondicaoTempoDaoConfig.clearIdentityScope();
        registroDoencaDaoConfig.clearIdentityScope();
        registroImagemDaoConfig.clearIdentityScope();
        registroPragaDaoConfig.clearIdentityScope();
        rotaTrabalhoDaoConfig.clearIdentityScope();
        tipoCultivoDaoConfig.clearIdentityScope();
        usuarioDaoConfig.clearIdentityScope();
    }

    public ClienteDao getClienteDao() {
        return clienteDao;
    }

    public ConfiguracaoDao getConfiguracaoDao() {
        return configuracaoDao;
    }

    public DispositivoDao getDispositivoDao() {
        return dispositivoDao;
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

    public RegistroCondicaoTempoDao getRegistroCondicaoTempoDao() {
        return registroCondicaoTempoDao;
    }

    public RegistroDoencaDao getRegistroDoencaDao() {
        return registroDoencaDao;
    }

    public RegistroImagemDao getRegistroImagemDao() {
        return registroImagemDao;
    }

    public RegistroPragaDao getRegistroPragaDao() {
        return registroPragaDao;
    }

    public RotaTrabalhoDao getRotaTrabalhoDao() {
        return rotaTrabalhoDao;
    }

    public TipoCultivoDao getTipoCultivoDao() {
        return tipoCultivoDao;
    }

    public UsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

}
