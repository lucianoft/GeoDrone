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
        daoSession = new DaoMaster(db).newSession();
        //DaoMaster.dropAllTables(db, true);

        try{
            daoSession.getClienteDao().loadAll();
            daoSession.getUsuarioDao().loadAll();
            daoSession.getRotaTrabalhoDao().loadAll();
            daoSession.getRegistroDoencaDao().loadAll();
            daoSession.getRegistroDoencaDao().loadAll();
        }catch (Exception ex){
            DaoMaster.dropAllTables(db, true);
        }

        DaoMaster.createAllTables(db, true);
        criarConfiguracao();

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

    public DaoSession getDaoSession() {
        return daoSession;
    }
}