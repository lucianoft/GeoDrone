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
import br.com.geodrone.utils.Constantes;


public class GeoDroneApplication extends Application {
    public DaoSession daoSession;
    public static final boolean ENCRYPTED = false;

    @Override
    public void onCreate() {
        super.onCreate();

        //daoSession = new DaoMaster(new DbOpenHelper(this, "movies-db").getWritableDb()).newSession();

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, Constantes.BD_NOME); //The users-db here is the name of our database.
        Database db = helper.getWritableDb();
        DaoMaster.dropAllTables(db, true);
        DaoMaster.createAllTables(db, false);
        daoSession = new DaoMaster(db).newSession();

        /*DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? Constantes.BD_NOME : "notes-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();*/
        criarCliente();
        criarUsuario();
        criarEstacao();
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
        cliente.setIdClienteRef(id);
        cliente.setIndPessoaFisica(indPessoaFisica);
        cliente.setNomeRazaoSocial(nomeRazaoSocial);
        cliente.setCfp(cfp);
        cliente.setCnpj(cnpj);
        cliente.setNomeFantasia(nomeFantasia);
        cliente.setSegmento(segmento);
        cliente.setEmail(email);
        cliente.setTelefone(telefone);
        cliente.setCelular(celular);
        cliente.setFlagStatus(flagStatus);
        return cliente;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    private void criarUsuario() {
        if (daoSession.getUsuarioDao().loadAll().size() == 0) {
            Usuario usuario = new Usuario();
            usuario.setIdUsuarioRef(1L);
            usuario.setNome("admin");
            usuario.setSobrenome("admin");
            usuario.setEmail("admin@gmail.com");
            usuario.setSenha("admin");
            usuario.setTelefone("9999-9999");
            usuario.setIdClienteRef(1L);
            daoSession.getUsuarioDao().insert(usuario);
        }
    }

    private void criarEstacao() {
        if (daoSession.getPontoColetaChuvaDao().loadAll().size() == 0) {

            getDaoSession().getPontoColetaChuvaDao().insert(new PontoColetaChuva(
                    1L,
                    "AP",
                    -18.92272265,
                    -48.24413374,
                    new Date(),
                    new Date(),
                    new Date(),
                    1L,
                    1,
                    null));

            getDaoSession().getPontoColetaChuvaDao().insert(new PontoColetaChuva(
                    1L,
                    "CDN",
                    -18.929408,
                    -48.2413727,
                    new Date(),
                    new Date(),
                    new Date(),
                    1L,
                    1,
                    null));

            getDaoSession().getPontoColetaChuvaDao().insert(new PontoColetaChuva(
                    1L,
                    "CDN",
                    -18.929408,
                    -48.2413727,
                    new Date(),
                    new Date(),
                    new Date(),
                    1l,
                    1,
                    null));

            getDaoSession().getPontoColetaChuvaDao().insert(new PontoColetaChuva(
                    1L,
                    "MARIA DE NAZARE",
                    -18.915298,
                    -48.23462,
                    new Date(),
                    new Date(),
                    new Date(),
                    1l,
                    1,
                    null));


            getDaoSession().getPontoColetaChuvaDao().insert(new PontoColetaChuva(
                    1L,
                    "CASA DAS MASSAS",
                    -18.9267564,
                    -48.2536422,
                    new Date(),
                    new Date(),
                    new Date(),
                    1l,
                    1,
                    null));

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
            getDaoSession().getPragaDao().insert(new Praga(1L, 1L, "Acaro Rajado",	"Tetranychus urticae", 1));
            getDaoSession().getPragaDao().insert(new Praga(2L, 1L,"Acaro-branco",	"Polyphagotarsonemus latus", 1));
            getDaoSession().getPragaDao().insert(new Praga(3L, 1L,"Bicudo-da-soja",	"Sternechus subsignatus", 1));
            getDaoSession().getPragaDao().insert(new Praga(4L, 1L,"Broca-das-axilas",	"Epinotia aporema", 1));


            getDaoSession().getPragaDao().insert(new Praga(5L, 3L,"Acaro branco",	"Polyphagotarsonemus latus", 1));
            getDaoSession().getPragaDao().insert(new Praga(6L, 3L,"Acaro rajado e vermelho",	"Tetranychus urticae", 1));
            getDaoSession().getPragaDao().insert(new Praga(7L, 3L,"Bicudo do Algodoeiro",	"Anthonomus grandis", 1));
            getDaoSession().getPragaDao().insert(new Praga(8L, 3L,"Broca da Haste", "Conotrachelus denieri", 1));
            getDaoSession().getPragaDao().insert(new Praga(9L, 3L,"Broca da Raiz", "Eutinobothrus brasiliensis", 1));

            getDaoSession().getPragaDao().insert(new Praga(10L, 4L,"Cigarrinha-das-raízes",	"Mahanarva fimbriolata", 1));
            getDaoSession().getPragaDao().insert(new Praga(11L, 4L,"Coro-das-pastagens", 	"Diloboderus abderus", 1));
            getDaoSession().getPragaDao().insert(new Praga(12L, 4L,"Coro-da-soja", 	"Phyllophaga cuyabana", 1));
            getDaoSession().getPragaDao().insert(new Praga(13L, 4L,"Coro-do-trigo", 	"Phyllophaga triticophaga", 1));
        }
    }

    private void criarDoencas() {
        if (daoSession.getDoencaDao().loadAll().size() == 0) {
            getDaoSession().getDoencaDao().insert(new Doenca(1L, 1L, "Acaro Rajado",	"Tetranychus urticae", 1));
            getDaoSession().getDoencaDao().insert(new Doenca(2L, 1L,"Acaro-branco",	"Polyphagotarsonemus latus", 1));
            getDaoSession().getDoencaDao().insert(new Doenca(3L, 1L,"Bicudo-da-soja",	"Sternechus subsignatus", 1));
            getDaoSession().getDoencaDao().insert(new Doenca(4L, 1L,"Broca-das-axilas",	"Epinotia aporema", 1));


            getDaoSession().getDoencaDao().insert(new Doenca(5L, 3L,"Acaro branco",	"Polyphagotarsonemus latus", 1));
            getDaoSession().getDoencaDao().insert(new Doenca(6L, 3L,"Acaro rajado e vermelho",	"Tetranychus urticae", 1));
            getDaoSession().getDoencaDao().insert(new Doenca(7L, 3L,"Bicudo do Algodoeiro",	"Anthonomus grandis", 1));
            getDaoSession().getDoencaDao().insert(new Doenca(8L, 3L,"Broca da Haste", "Conotrachelus denieri", 1));
            getDaoSession().getDoencaDao().insert(new Doenca(9L, 3L,"Broca da Raiz", "Eutinobothrus brasiliensis", 1));

            getDaoSession().getDoencaDao().insert(new Doenca(10L, 4L,"Cigarrinha-das-raízes",	"Mahanarva fimbriolata", 1));
            getDaoSession().getDoencaDao().insert(new Doenca(11L, 4L,"Coro-das-pastagens", 	"Diloboderus abderus", 1));
            getDaoSession().getDoencaDao().insert(new Doenca(12L, 4L,"Coro-da-soja", 	"Phyllophaga cuyabana", 1));
            getDaoSession().getDoencaDao().insert(new Doenca(13L, 4L,"Coro-do-trigo", 	"Phyllophaga triticophaga", 1));
        }
    }
}