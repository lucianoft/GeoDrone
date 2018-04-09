package br.com.geodrone;

import android.app.Application;

import org.greenrobot.greendao.database.Database;

import java.util.Date;

import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.Doenca;
import br.com.geodrone.model.PontoColetaChuva;
import br.com.geodrone.model.Praga;
import br.com.geodrone.model.TipoCultivo;
import br.com.geodrone.model.Usuario;
import br.com.geodrone.model.constantes.FlagStatusCliente;
import br.com.geodrone.model.daoGen.DaoMaster;
import br.com.geodrone.model.daoGen.DaoSession;
import br.com.geodrone.service.ClienteService;
import br.com.geodrone.service.DoencaService;
import br.com.geodrone.service.PontoColetaChuvaService;
import br.com.geodrone.service.PragaService;
import br.com.geodrone.service.TipoCultivoService;
import br.com.geodrone.service.UsuarioService;
import br.com.geodrone.utils.Constantes;


public class GeoDroneApplication extends Application {
    public DaoSession daoSession;
    public static final boolean ENCRYPTED = false;

    ClienteService clienteService;
    UsuarioService usuarioService;
    PontoColetaChuvaService pontoColetaChuvaService;
    TipoCultivoService tipoCultivoService;
    PragaService pragaService;
    DoencaService doencaService;

    @Override
    public void onCreate() {
        super.onCreate();

        //daoSession = new DaoMaster(new DbOpenHelper(this, "movies-db").getWritableDb()).newSession();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, Constantes.BD_NOME); //The users-db here is the name of our database.
        Database db = helper.getWritableDb();
        DaoMaster.dropAllTables(db, true);
        DaoMaster.createAllTables(db, false);
        daoSession = new DaoMaster(db).newSession();


        clienteService = new ClienteService(this);
        usuarioService = new UsuarioService(this);
        pontoColetaChuvaService  new PontoColetaChuvaService(this);
        tipoCultivoService = new TipoCultivoService(this);
        pragaService = new PragaService(this);
        doencaService = new DoencaService(this);

        /*DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? Constantes.BD_NOME : "notes-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();*/
        criarCliente();
        criarUsuario();
        criarPontoColetaChuva();
        criarTipoCultivo();
        criarPragas();
        criarDoencas();
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
            daoSession.getClienteDao().insert(cliente);
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
            daoSession.getUsuarioDao().insert(usuario);
        }
    }

    private void criarPontoColetaChuva() {
        if (daoSession.getPontoColetaChuvaDao().loadAll().size() == 0) {

            getDaoSession().getPontoColetaChuvaDao().insert(new PontoColetaChuva(
                    1L,
                    "AP",
                    new Date(),
                    1L,
                    1L,
                    -18.92272265,
                    -48.24413374,
                    1L,
                    1));

            getDaoSession().getPontoColetaChuvaDao().insert(new PontoColetaChuva(
                    2L,
                    "CDN",
                    new Date(),
                    1L,
                    1L,
                    -18.929408,
                    -48.2413727,
                    1L,
                    1));

            getDaoSession().getPontoColetaChuvaDao().insert(new PontoColetaChuva(
                    3L,
                    "MARIA DE NAZARE",
                    new Date(),
                    1L,
                    1L,
                    -18.915298,
                    -48.23462,
                    1L,
                    1));

            getDaoSession().getPontoColetaChuvaDao().insert(new PontoColetaChuva(
                    4L,
                    "CASA DAS MASSAS",
                    new Date(),
                    1L,
                    1L,
                    -18.9267564,
                    -48.2536422,
                    1L,
                    1));
        }

    }
    private void criarTipoCultivo() {
        if (daoSession.getTipoCultivoDao().loadAll().size() == 0) {
            getDaoSession().getTipoCultivoDao().insert(new TipoCultivo(1L, "Soja", 1));
            getDaoSession().getTipoCultivoDao().insert(new TipoCultivo(2L, "Feijao", 1));
            getDaoSession().getTipoCultivoDao().insert(new TipoCultivo(3L, "Algodao", 1));
            getDaoSession().getTipoCultivoDao().insert(new TipoCultivo(4L, "Milho", 1));
        }
    }


    private void criarPragas() {
        if (daoSession.getPragaDao().loadAll().size() == 0) {
            getDaoSession().getPragaDao().insert(new Praga(1L, "Acaro Rajado",	"Tetranychus urticae", 1L, 1));
            getDaoSession().getPragaDao().insert(new Praga(2L, "Acaro-branco",	"Polyphagotarsonemus latus", 1L, 1));
            getDaoSession().getPragaDao().insert(new Praga(3L, "Bicudo-da-soja",	"Sternechus subsignatus", 1L, 1));
            getDaoSession().getPragaDao().insert(new Praga(4L, "Broca-das-axilas",	"Epinotia aporema", 1L, 1));


            getDaoSession().getPragaDao().insert(new Praga(5L, "Acaro branco",	"Polyphagotarsonemus latus",3L, 1));
            getDaoSession().getPragaDao().insert(new Praga(6L, "Acaro rajado e vermelho",	"Tetranychus urticae", 3L,1));
            getDaoSession().getPragaDao().insert(new Praga(7L, "Bicudo do Algodoeiro",	"Anthonomus grandis", 3L,1));
            getDaoSession().getPragaDao().insert(new Praga(8L, "Broca da Haste", "Conotrachelus denieri", 3L,1));
            getDaoSession().getPragaDao().insert(new Praga(9L, "Broca da Raiz", "Eutinobothrus brasiliensis", 3L,1));

            getDaoSession().getPragaDao().insert(new Praga(10L, "Cigarrinha-das-raízes",	"Mahanarva fimbriolata", 4L,1));
            getDaoSession().getPragaDao().insert(new Praga(11L, "Coro-das-pastagens", 	"Diloboderus abderus", 4L,1));
            getDaoSession().getPragaDao().insert(new Praga(12L, "Coro-da-soja", 	"Phyllophaga cuyabana", 4L,1));
            getDaoSession().getPragaDao().insert(new Praga(13L, "Coro-do-trigo", 	"Phyllophaga triticophaga", 4L,1));
        }
    }

    private void criarDoencas() {
        if (daoSession.getDoencaDao().loadAll().size() == 0) {
            getDaoSession().getDoencaDao().insert(new Doenca(1L, "Acaro Rajado",	"Tetranychus urticae", 1L, 1));
            getDaoSession().getDoencaDao().insert(new Doenca(2L, "Acaro-branco",	"Polyphagotarsonemus latus", 1L, 1));
            getDaoSession().getDoencaDao().insert(new Doenca(3L, "Bicudo-da-soja",	"Sternechus subsignatus", 1L, 1));
            getDaoSession().getDoencaDao().insert(new Doenca(4L, "Broca-das-axilas",	"Epinotia aporema", 1L, 1));


            getDaoSession().getDoencaDao().insert(new Doenca(5L, "Acaro branco",	"Polyphagotarsonemus latus",3L, 1));
            getDaoSession().getDoencaDao().insert(new Doenca(6L, "Acaro rajado e vermelho",	"Tetranychus urticae", 3L,1));
            getDaoSession().getDoencaDao().insert(new Doenca(7L, "Bicudo do Algodoeiro",	"Anthonomus grandis", 3L,1));
            getDaoSession().getDoencaDao().insert(new Doenca(8L, "Broca da Haste", "Conotrachelus denieri", 3L,1));
            getDaoSession().getDoencaDao().insert(new Doenca(9L, "Broca da Raiz", "Eutinobothrus brasiliensis", 3L,1));

            getDaoSession().getDoencaDao().insert(new Doenca(10L, "Cigarrinha-das-raízes",	"Mahanarva fimbriolata", 4L,1));
            getDaoSession().getDoencaDao().insert(new Doenca(11L, "Coro-das-pastagens", 	"Diloboderus abderus", 4L,1));
            getDaoSession().getDoencaDao().insert(new Doenca(12L, "Coro-da-soja", 	"Phyllophaga cuyabana", 4L,1));
            getDaoSession().getDoencaDao().insert(new Doenca(13L, "Coro-do-trigo", 	"Phyllophaga triticophaga", 4L,1));
        }
    }

}