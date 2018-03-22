package br.com.geodrone;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.database.Database;

import java.time.LocalDate;
import java.util.Date;

import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.EstacaoPluviometrica;
import br.com.geodrone.model.Usuario;
import br.com.geodrone.model.constantes.FlagStatusCliente;
import br.com.geodrone.model.converter.FlagStatusClienteConverter;
import br.com.geodrone.model.daoGen.DaoMaster;
import br.com.geodrone.model.daoGen.DaoSession;
import br.com.geodrone.model.daoGen.DbOpenHelper;
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
        daoSession = new DaoMaster(db).newSession();

        /*DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? Constantes.BD_NOME : "notes-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();*/
        criarCliente();
        criarUsuario();
        criarEstacao();
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
            usuario.setId(1L);
            usuario.setNome("admin");
            usuario.setSobrenome("admin");
            usuario.setEmail("admin@gmail.com");
            usuario.setSenha("admin");
            usuario.setTelefone("9999-9999");
            daoSession.getUsuarioDao().insert(usuario);
        }
    }

    private void criarEstacao() {
        if (daoSession.getEstacaoPluviometricaDao().loadAll().size() == 0) {

            getDaoSession().getEstacaoPluviometricaDao().insert(new EstacaoPluviometrica(null,
                    1L,
                    "AP",
                    -18.92272265,
                    -48.24413374,
                    new Date(),
                    new Date(),
                    new Date(),
                    1l,
                    null));

            getDaoSession().getEstacaoPluviometricaDao().insert(new EstacaoPluviometrica(null,
                    1L,
                    "CDN",
                    -18.929408,
                    -48.2413727,
                    new Date(),
                    new Date(),
                    new Date(),
                    1l,
                    null));

            getDaoSession().getEstacaoPluviometricaDao().insert(new EstacaoPluviometrica(null,
                    1L,
                    "CDN",
                    -18.929408,
                    -48.2413727,
                    new Date(),
                    new Date(),
                    new Date(),
                    1l,
                    null));

            getDaoSession().getEstacaoPluviometricaDao().insert(new EstacaoPluviometrica(null,
                    1L,
                    "MARIA DE NAZARE",
                    -18.915298,
                    -48.23462,
                     new Date(),
                     new Date(),
                     new Date(),
                    1l,
                    null));


            getDaoSession().getEstacaoPluviometricaDao().insert(new EstacaoPluviometrica(null,
                    1L,
                    "CASA DAS MASSAS",
                    -18.9267564,
                    -48.2536422,
                    new Date(),
                    new Date(),
                    new Date(),
                    1l,
                    null));

        }

    }
}