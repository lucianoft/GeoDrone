package br.com.geodrone.model.daoGen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import br.com.geodrone.model.Usuario;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "TB_USUARIO".
*/
public class UsuarioDao extends AbstractDao<Usuario, Long> {

    public static final String TABLENAME = "TB_USUARIO";

    /**
     * Properties of entity Usuario.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "ID_USUARIO");
        public final static Property Nome = new Property(1, String.class, "nome", false, "NOME");
        public final static Property Sobrenome = new Property(2, String.class, "sobrenome", false, "SOBRENOME");
        public final static Property Email = new Property(3, String.class, "email", false, "EMAIL");
        public final static Property Senha = new Property(4, String.class, "senha", false, "SENHA");
    }


    public UsuarioDao(DaoConfig config) {
        super(config);
    }
    
    public UsuarioDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TB_USUARIO\" (" + //
                "\"ID_USUARIO\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"NOME\" TEXT NOT NULL ," + // 1: nome
                "\"SOBRENOME\" TEXT," + // 2: sobrenome
                "\"EMAIL\" TEXT NOT NULL ," + // 3: email
                "\"SENHA\" TEXT NOT NULL );"); // 4: senha
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TB_USUARIO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Usuario entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getNome());
 
        String sobrenome = entity.getSobrenome();
        if (sobrenome != null) {
            stmt.bindString(3, sobrenome);
        }
        stmt.bindString(4, entity.getEmail());
        stmt.bindString(5, entity.getSenha());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Usuario entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getNome());
 
        String sobrenome = entity.getSobrenome();
        if (sobrenome != null) {
            stmt.bindString(3, sobrenome);
        }
        stmt.bindString(4, entity.getEmail());
        stmt.bindString(5, entity.getSenha());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Usuario readEntity(Cursor cursor, int offset) {
        Usuario entity = new Usuario();
        readEntity(cursor, entity, offset);
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Usuario entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setNome(cursor.getString(offset + 1));
        entity.setSobrenome(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setEmail(cursor.getString(offset + 3));
        entity.setSenha(cursor.getString(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Usuario entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Usuario entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Usuario entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
