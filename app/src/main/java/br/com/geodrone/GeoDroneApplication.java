package br.com.geodrone;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.AbstractDaoMaster;
import org.greenrobot.greendao.database.Database;

import java.lang.reflect.Field;
import java.util.Date;

import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.Configuracao;
import br.com.geodrone.model.Dispositivo;
import br.com.geodrone.model.Doenca;
import br.com.geodrone.model.PontoColetaChuva;
import br.com.geodrone.model.Praga;
import br.com.geodrone.model.TipoCultivo;
import br.com.geodrone.model.Usuario;
import br.com.geodrone.model.constantes.FlagStatusCliente;
import br.com.geodrone.model.daoGen.DaoMaster;
import br.com.geodrone.model.daoGen.DaoSession;
import br.com.geodrone.model.daoGen.RotaTrabalhoDao;
import br.com.geodrone.utils.Constantes;
import br.com.geodrone.utils.PreferencesUtils;


public class GeoDroneApplication extends Application {
    public DaoSession daoSession;
    public static final boolean ENCRYPTED = false;

    private void setCursorWindowSize(int size) {
            try {
                final Class cls = Class.forName("android.database.CursorWindow");
                final Field fld = cls.getDeclaredField("sCursorWindowSize");
                fld.setAccessible(true);
                int before = fld.getInt(null); // default=2048*1024
                fld.setInt(null, size * 1024); // extend to
                int after = fld.getInt(null);
            } catch (Exception e) {
            }
    }
    @Override
    public void onCreate() {
        super.onCreate();

        clearDatabase("db_geodrone");
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, Constantes.BD_NOME); //The users-db here is the name of our database.
        Database db = helper.getWritableDb();
        //DaoMaster.dropAllTables(db, true);
        daoSession = new DaoMaster(db).newSession();
        try{
            daoSession.getRotaTrabalhoDao().loadAll();
        }catch (Exception ex){
            RotaTrabalhoDao.dropTable(db, true);
        }
        DaoMaster.createAllTables(db, true);

        //setCursorWindowSize(10240);
        /*criarCliente();
        criarDispositivo();
        criarUsuario();
        criarPontoColetaChuva();
        criarTipoCultivo();
        criarPragas();
        criarDoencas();*/
    }

    public void clearDatabase(String databaseName) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, databaseName);
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
        DaoMaster.createAllTables(db, true);

    }

    private void criarConfiguracao() {
        if (daoSession.getConfiguracaoDao().loadAll().size() == 0) {

            Configuracao configuracao = new Configuracao();
            configuracao.setUrl(Constantes.API_LOGIN_URL);
            configuracao.setDtInclusao(new Date());
            configuracao.setDtAlteracao(new Date());
            configuracao.setVersaoSistema(1L);

            getDaoSession().getConfiguracaoDao().insert(configuracao);
            SessionGeooDrone.setAttribute(SessionGeooDrone.CHAVE_URL_BASE, Constantes.API_LOGIN_URL);
        }
    }
    private void criarCliente() {
        if (daoSession.getClienteDao().loadAll().size() == 0) {

            Cliente cliente = newCliente(1L,
                    1,
                    "Admin",
                    191L,
                    null,
                    null,
                    "Agricultura de Sequeiro",
                    "faz.eidt@bol.com.br",
                    "9999",
                    "1222",
                    FlagStatusCliente.AGUARDANDO_APROVACAO);
            getDaoSession().getClienteDao().insert(cliente);
            SessionGeooDrone.setAttribute(SessionGeooDrone.CHAVE_CLIENTE, cliente);
        }
    }

    private Cliente newCliente(Long id, Integer indPessoaFisica, String nomeRazaoSocial, Long cfp,
                               Long cnpj, String nomeFantasia, String segmento, String email,
                               String telefone, String celular,
                               FlagStatusCliente flagStatus) {
        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setIndPessoaFisica(indPessoaFisica);
        cliente.setNomeRazaoSocial(nomeRazaoSocial);
        cliente.setCpf(cfp);
        cliente.setCnpj(cnpj);
        cliente.setSobrenome(nomeFantasia);
        //cliente.setSegmento(segmento);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);
        cliente.setCelular(celular);
        cliente.setFlagStatus(flagStatus.value());
        cliente.setDtInclusao(new Date());
        cliente.setDtAlteracao(new Date());
        cliente.setVersaoSistema(1L);

        return cliente;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    private void criarUsuario() {
        if (daoSession.getUsuarioDao().loadAll().size() == 0) {
            Usuario usuario = new Usuario();
            usuario.setId(1L);
            usuario.setNome("admin");
            usuario.setSobrenome("admin");
            usuario.setEmail("admin@gmail.com");
            usuario.setSenha("admin");
            usuario.setTelefone("9999-9999");
            usuario.setIdCliente(1L);
            usuario.setDtInclusao(new Date());
            usuario.setDtAlteracao(new Date());
            usuario.setVersaoSistema(1L);
            usuario.setIndAtivo(1);
            daoSession.getUsuarioDao().insert(usuario);

            SessionGeooDrone.setAttribute(SessionGeooDrone.CHAVE_USUARIO, usuario);

        }
    }

    private void criarDispositivo(){
        if (daoSession.getDispositivoDao().loadAll().size() == 0) {
            Dispositivo dispositivo = new Dispositivo();
            dispositivo.setId(1L);
            dispositivo.setIdCliente(1L);
            //dispositivo.setDtSincronizacao(new Date());
            dispositivo.setDtInclusao(new Date());
            dispositivo.setDtAlteracao(new Date());
            dispositivo.setVersaoSistema(1L);

            getDaoSession().getDispositivoDao().insert(dispositivo);
            SessionGeooDrone.setAttribute(SessionGeooDrone.CHAVE_DISPOSITIVO, dispositivo);
        }
    }
    private void criarPontoColetaChuva() {
        if (daoSession.getPontoColetaChuvaDao().loadAll().size() == 0) {

            getDaoSession().getPontoColetaChuvaDao().insert(criarPontoColetaChuva(1L,"AP", new Date(), 1L,-18.92272265,-48.24413374,1L));
            getDaoSession().getPontoColetaChuvaDao().insert(criarPontoColetaChuva(2L,
                    "CDN",
                    new Date(),
                    1L,
                    -18.929408,
                    -48.2413727,
                    1L));

            getDaoSession().getPontoColetaChuvaDao().insert(criarPontoColetaChuva(3L,
                    "MARIA DE NAZARE",
                    new Date(),
                    1L,
                    -18.915298,
                    -48.23462,
                    1L));
            getDaoSession().getPontoColetaChuvaDao().insert(criarPontoColetaChuva(4L,
                    "CASA DAS MASSAS",
                    new Date(),
                    1L,
                    -18.9267564,
                    -48.2536422,
                    1L));
        }

    }
    private void criarTipoCultivo() {
        if (daoSession.getTipoCultivoDao().loadAll().size() == 0) {
            daoSession.getTipoCultivoDao().insert(criarTipoCultivo(1L, "Soja"));
            daoSession.getTipoCultivoDao().insert(criarTipoCultivo(2L, "Feijao"));
            daoSession.getTipoCultivoDao().insert(criarTipoCultivo(3L, "Algodao"));
            daoSession.getTipoCultivoDao().insert(criarTipoCultivo(4L, "Milho"));
        }
    }

    private TipoCultivo criarTipoCultivo(Long id, String descricao){
        TipoCultivo tipoCultivo = new TipoCultivo();
        tipoCultivo.setId(id);
        tipoCultivo.setDescricao(descricao);
        tipoCultivo.setIndAtivo(1);
        tipoCultivo.setDtInclusao(new Date());
        tipoCultivo.setDtAlteracao(new Date());
        tipoCultivo.setVersaoSistema(1L);
        return tipoCultivo;
    }

    private PontoColetaChuva criarPontoColetaChuva(Long id, String descricao,
                                                   Date dtInstalacao,
                                                   Long idCliente,
                                                   Double latitude, Double longitude, Long idDispositivo) {
        PontoColetaChuva pontoColetaChuva = new PontoColetaChuva();
        pontoColetaChuva.setId(id);
        pontoColetaChuva.setDescricao(descricao);
        pontoColetaChuva.setDtInstalacao(dtInstalacao);
        pontoColetaChuva.setIdPontoColetaChuva(null);
        pontoColetaChuva.setIdCliente(idCliente);
        pontoColetaChuva.setLatitude(latitude);
        pontoColetaChuva.setLongitude(longitude);
        pontoColetaChuva.setIdDispositivo(idDispositivo);
        pontoColetaChuva.setIdUsuarioReg(1L);
        pontoColetaChuva.setIndAtivo(1);
        pontoColetaChuva.setDtInclusao(new Date());
        pontoColetaChuva.setDtAlteracao(new Date());
        pontoColetaChuva.setVersaoSistema(1L);

        return pontoColetaChuva;
    }

    private void criarPragas() {
        if (daoSession.getPragaDao().loadAll().size() == 0) {
            daoSession.getPragaDao().insert(criarPraga(1L, "Acaro Rajado",	"Tetranychus urticae", 1L));
            daoSession.getPragaDao().insert(criarPraga(2L, "Acaro-branco",	"Polyphagotarsonemus latus", 1L));
            daoSession.getPragaDao().insert(criarPraga(3L, "Bicudo-da-soja",	"Sternechus subsignatus", 1L));
            daoSession.getPragaDao().insert(criarPraga(4L, "Broca-das-axilas",	"Epinotia aporema", 1L));


            daoSession.getPragaDao().insert(criarPraga(5L, "Acaro branco",	"Polyphagotarsonemus latus",3L));
            daoSession.getPragaDao().insert(criarPraga(6L, "Acaro rajado e vermelho",	"Tetranychus urticae", 3L));
            daoSession.getPragaDao().insert(criarPraga(7L, "Bicudo do Algodoeiro",	"Anthonomus grandis", 3L));
            daoSession.getPragaDao().insert(criarPraga(8L, "Broca da Haste", "Conotrachelus denieri", 3L));
            daoSession.getPragaDao().insert(criarPraga(9L, "Broca da Raiz", "Eutinobothrus brasiliensis", 3L));

            daoSession.getPragaDao().insert(criarPraga(10L, "Cigarrinha-das-raízes",	"Mahanarva fimbriolata", 4L));
            daoSession.getPragaDao().insert(criarPraga(11L, "Coro-das-pastagens", 	"Diloboderus abderus", 4L));
            daoSession.getPragaDao().insert(criarPraga(12L, "Coro-da-soja", 	"Phyllophaga cuyabana", 4L));
            daoSession.getPragaDao().insert(criarPraga(13L, "Coro-do-trigo", 	"Phyllophaga triticophaga", 4L));
        }
    }

    private Praga criarPraga(Long id, String descricao, String descricaoCientifica, Long idTipoCultivo){
        Praga praga = new Praga();
        praga.setId(id);
        praga.setDescricao(descricao);
        praga.setDescricaoCientifica(descricaoCientifica);
        praga.setIdTipoCultivo(idTipoCultivo);
        praga.setIndAtivo(1);
        praga.setDtInclusao(new Date());
        praga.setDtAlteracao(new Date());
        praga.setVersaoSistema(1L);
        return praga;
    }

    
    private void criarDoencas() {
        if (daoSession.getDoencaDao().loadAll().size() == 0) {
            daoSession.getDoencaDao().insert(criarDoenca(1L, "Acaro Rajado",	"Tetranychus urticae", 1L));
            daoSession.getDoencaDao().insert(criarDoenca(2L, "Acaro-branco",	"Polyphagotarsonemus latus", 1L));
            daoSession.getDoencaDao().insert(criarDoenca(3L, "Bicudo-da-soja",	"Sternechus subsignatus", 1L));
            daoSession.getDoencaDao().insert(criarDoenca(4L, "Broca-das-axilas",	"Epinotia aporema", 1L));


            daoSession.getDoencaDao().insert(criarDoenca(5L, "Acaro branco",	"Polyphagotarsonemus latus",3L));
            daoSession.getDoencaDao().insert(criarDoenca(6L, "Acaro rajado e vermelho",	"Tetranychus urticae", 3L));
            daoSession.getDoencaDao().insert(criarDoenca(7L, "Bicudo do Algodoeiro",	"Anthonomus grandis", 3L));
            daoSession.getDoencaDao().insert(criarDoenca(8L, "Broca da Haste", "Conotrachelus denieri", 3L));
            daoSession.getDoencaDao().insert(criarDoenca(9L, "Broca da Raiz", "Eutinobothrus brasiliensis", 3L));

            daoSession.getDoencaDao().insert(criarDoenca(10L, "Cigarrinha-das-raízes",	"Mahanarva fimbriolata", 4L));
            daoSession.getDoencaDao().insert(criarDoenca(11L, "Coro-das-pastagens", 	"Diloboderus abderus", 4L));
            daoSession.getDoencaDao().insert(criarDoenca(12L, "Coro-da-soja", 	"Phyllophaga cuyabana", 4L));
            daoSession.getDoencaDao().insert(criarDoenca(13L, "Coro-do-trigo", 	"Phyllophaga triticophaga", 4L));
        }
    }

    private Doenca criarDoenca(Long id, String descricao, String descricaoCientifica, Long idTipoCultivo){
        Doenca doenca = new Doenca();
        doenca.setId(id);
        doenca.setDescricao(descricao);
        doenca.setDescricaoCientifica(descricaoCientifica);
        doenca.setIdTipoCultivo(idTipoCultivo);
        doenca.setIndAtivo(1);
        doenca.setDtInclusao(new Date());
        doenca.setDtAlteracao(new Date());
        doenca.setVersaoSistema(1L);
        return doenca;
    }


}