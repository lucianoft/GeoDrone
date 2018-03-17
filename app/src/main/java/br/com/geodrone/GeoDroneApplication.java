package br.com.geodrone;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.database.Database;

import br.com.geodrone.model.Usuario;
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

        daoSession = new DaoMaster(new DbOpenHelper(this, "movies-db").getWritableDb()).newSession();

/*
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this,"movies-db"); //The users-db here is the name of our database.
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
*/

        // USER CREATION FOR DEMO PURPOSE
        if(daoSession.getUsuarioDao().loadAll().size() == 0){
            Usuario usuario = new Usuario();
            usuario.setId(1L);
            usuario.setEmail("admin@gmail.com");
            usuario.setSenha("1234");
            usuario.setSobrenome("admin");
            usuario.setNome("admin");

            daoSession.getUsuarioDao().insert(usuario);
        }
        /*DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ? Constantes.BD_NOME : "notes-db");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();*/
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

}