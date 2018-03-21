package br.com.geodrone.model.daoGen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import br.com.geodrone.model.DadosPluviometricos;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "TB_DADOS_PLUVIOMETRICOS".
*/
public class DadosPluviometricosDao extends AbstractDao<DadosPluviometricos, Long> {

    public static final String TABLENAME = "TB_DADOS_PLUVIOMETRICOS";

    /**
     * Properties of entity DadosPluviometricos.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "ID_DADOS_PLUVIOMETRICOS");
        public final static Property IdCliente = new Property(1, Long.class, "idCliente", false, "ID_CLIENTE");
        public final static Property Volume = new Property(2, Long.class, "volume", false, "VOLUME");
        public final static Property DtInclusao = new Property(3, java.util.Date.class, "dtInclusao", false, "DT_INCLUSAO");
        public final static Property DtAlteracao = new Property(4, java.util.Date.class, "dtAlteracao", false, "DT_ALTERACAO");
        public final static Property IdUsuario = new Property(5, Long.class, "idUsuario", false, "ID_USUARIO");
        public final static Property IdDadosPluviometricosWeb = new Property(6, Long.class, "idDadosPluviometricosWeb", false, "ID_DADOS_PLUVIOMETRICOS_WEB");
    }


    public DadosPluviometricosDao(DaoConfig config) {
        super(config);
    }
    
    public DadosPluviometricosDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TB_DADOS_PLUVIOMETRICOS\" (" + //
                "\"ID_DADOS_PLUVIOMETRICOS\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"ID_CLIENTE\" INTEGER NOT NULL ," + // 1: idCliente
                "\"VOLUME\" INTEGER NOT NULL ," + // 2: volume
                "\"DT_INCLUSAO\" INTEGER NOT NULL ," + // 3: dtInclusao
                "\"DT_ALTERACAO\" INTEGER NOT NULL ," + // 4: dtAlteracao
                "\"ID_USUARIO\" INTEGER," + // 5: idUsuario
                "\"ID_DADOS_PLUVIOMETRICOS_WEB\" INTEGER);"); // 6: idDadosPluviometricosWeb
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TB_DADOS_PLUVIOMETRICOS\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, DadosPluviometricos entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getIdCliente());
        stmt.bindLong(3, entity.getVolume());
        stmt.bindLong(4, entity.getDtInclusao().getTime());
        stmt.bindLong(5, entity.getDtAlteracao().getTime());
 
        Long idUsuario = entity.getIdUsuario();
        if (idUsuario != null) {
            stmt.bindLong(6, idUsuario);
        }
 
        Long idDadosPluviometricosWeb = entity.getIdDadosPluviometricosWeb();
        if (idDadosPluviometricosWeb != null) {
            stmt.bindLong(7, idDadosPluviometricosWeb);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, DadosPluviometricos entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getIdCliente());
        stmt.bindLong(3, entity.getVolume());
        stmt.bindLong(4, entity.getDtInclusao().getTime());
        stmt.bindLong(5, entity.getDtAlteracao().getTime());
 
        Long idUsuario = entity.getIdUsuario();
        if (idUsuario != null) {
            stmt.bindLong(6, idUsuario);
        }
 
        Long idDadosPluviometricosWeb = entity.getIdDadosPluviometricosWeb();
        if (idDadosPluviometricosWeb != null) {
            stmt.bindLong(7, idDadosPluviometricosWeb);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public DadosPluviometricos readEntity(Cursor cursor, int offset) {
        DadosPluviometricos entity = new DadosPluviometricos();
        readEntity(cursor, entity, offset);
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, DadosPluviometricos entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setIdCliente(cursor.getLong(offset + 1));
        entity.setVolume(cursor.getLong(offset + 2));
        entity.setDtInclusao(new java.util.Date(cursor.getLong(offset + 3)));
        entity.setDtAlteracao(new java.util.Date(cursor.getLong(offset + 4)));
        entity.setIdUsuario(cursor.isNull(offset + 5) ? null : cursor.getLong(offset + 5));
        entity.setIdDadosPluviometricosWeb(cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(DadosPluviometricos entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(DadosPluviometricos entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(DadosPluviometricos entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}