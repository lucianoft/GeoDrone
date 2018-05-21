package br.com.geodrone.model.daoGen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import br.com.geodrone.model.Dispositivo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "GEO_DISPOSITIVO".
*/
public class DispositivoDao extends AbstractDao<Dispositivo, Long> {

    public static final String TABLENAME = "GEO_DISPOSITIVO";

    /**
     * Properties of entity Dispositivo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "ID_DISPOSITIVO");
        public final static Property Descricao = new Property(1, String.class, "descricao", false, "DESCRICAO");
        public final static Property DtSincronizacaoAndroid = new Property(2, java.util.Date.class, "dtSincronizacaoAndroid", false, "DT_SINCRONIZACAO_ANDROID");
        public final static Property DtSincronizacaoErp = new Property(3, java.util.Date.class, "dtSincronizacaoErp", false, "DT_SINCRONIZACAO_ERP");
        public final static Property DtInclusao = new Property(4, java.util.Date.class, "dtInclusao", false, "DT_INCLUSAO");
        public final static Property DtAlteracao = new Property(5, java.util.Date.class, "dtAlteracao", false, "DT_ALTERACAO");
        public final static Property VersaoSistema = new Property(6, Long.class, "versaoSistema", false, "VERSAO_SISTEMA");
    }


    public DispositivoDao(DaoConfig config) {
        super(config);
    }
    
    public DispositivoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"GEO_DISPOSITIVO\" (" + //
                "\"ID_DISPOSITIVO\" INTEGER PRIMARY KEY ," + // 0: id
                "\"DESCRICAO\" TEXT," + // 1: descricao
                "\"DT_SINCRONIZACAO_ANDROID\" INTEGER," + // 2: dtSincronizacaoAndroid
                "\"DT_SINCRONIZACAO_ERP\" INTEGER," + // 3: dtSincronizacaoErp
                "\"DT_INCLUSAO\" INTEGER NOT NULL ," + // 4: dtInclusao
                "\"DT_ALTERACAO\" INTEGER NOT NULL ," + // 5: dtAlteracao
                "\"VERSAO_SISTEMA\" INTEGER NOT NULL );"); // 6: versaoSistema
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"GEO_DISPOSITIVO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Dispositivo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String descricao = entity.getDescricao();
        if (descricao != null) {
            stmt.bindString(2, descricao);
        }
 
        java.util.Date dtSincronizacaoAndroid = entity.getDtSincronizacaoAndroid();
        if (dtSincronizacaoAndroid != null) {
            stmt.bindLong(3, dtSincronizacaoAndroid.getTime());
        }
 
        java.util.Date dtSincronizacaoErp = entity.getDtSincronizacaoErp();
        if (dtSincronizacaoErp != null) {
            stmt.bindLong(4, dtSincronizacaoErp.getTime());
        }
        stmt.bindLong(5, entity.getDtInclusao().getTime());
        stmt.bindLong(6, entity.getDtAlteracao().getTime());
        stmt.bindLong(7, entity.getVersaoSistema());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Dispositivo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String descricao = entity.getDescricao();
        if (descricao != null) {
            stmt.bindString(2, descricao);
        }
 
        java.util.Date dtSincronizacaoAndroid = entity.getDtSincronizacaoAndroid();
        if (dtSincronizacaoAndroid != null) {
            stmt.bindLong(3, dtSincronizacaoAndroid.getTime());
        }
 
        java.util.Date dtSincronizacaoErp = entity.getDtSincronizacaoErp();
        if (dtSincronizacaoErp != null) {
            stmt.bindLong(4, dtSincronizacaoErp.getTime());
        }
        stmt.bindLong(5, entity.getDtInclusao().getTime());
        stmt.bindLong(6, entity.getDtAlteracao().getTime());
        stmt.bindLong(7, entity.getVersaoSistema());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Dispositivo readEntity(Cursor cursor, int offset) {
        Dispositivo entity = new Dispositivo();
        readEntity(cursor, entity, offset);
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Dispositivo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDescricao(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setDtSincronizacaoAndroid(cursor.isNull(offset + 2) ? null : new java.util.Date(cursor.getLong(offset + 2)));
        entity.setDtSincronizacaoErp(cursor.isNull(offset + 3) ? null : new java.util.Date(cursor.getLong(offset + 3)));
        entity.setDtInclusao(new java.util.Date(cursor.getLong(offset + 4)));
        entity.setDtAlteracao(new java.util.Date(cursor.getLong(offset + 5)));
        entity.setVersaoSistema(cursor.getLong(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Dispositivo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Dispositivo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Dispositivo entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
