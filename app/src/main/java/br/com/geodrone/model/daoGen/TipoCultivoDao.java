package br.com.geodrone.model.daoGen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import br.com.geodrone.model.TipoCultivo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "TB_TIPO_CULTIVO".
*/
public class TipoCultivoDao extends AbstractDao<TipoCultivo, Long> {

    public static final String TABLENAME = "TB_TIPO_CULTIVO";

    /**
     * Properties of entity TipoCultivo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property IdTipoCultivoRef = new Property(0, Long.class, "idTipoCultivoRef", true, "ID_TIPO_CULTIVO_REF");
        public final static Property Descricao = new Property(1, String.class, "descricao", false, "DESCRICAO");
        public final static Property IndAtivo = new Property(2, Integer.class, "indAtivo", false, "IND_ATIVO");
    }


    public TipoCultivoDao(DaoConfig config) {
        super(config);
    }
    
    public TipoCultivoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TB_TIPO_CULTIVO\" (" + //
                "\"ID_TIPO_CULTIVO_REF\" INTEGER PRIMARY KEY ," + // 0: idTipoCultivoRef
                "\"DESCRICAO\" TEXT NOT NULL ," + // 1: descricao
                "\"IND_ATIVO\" INTEGER NOT NULL );"); // 2: indAtivo
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TB_TIPO_CULTIVO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, TipoCultivo entity) {
        stmt.clearBindings();
 
        Long idTipoCultivoRef = entity.getIdTipoCultivoRef();
        if (idTipoCultivoRef != null) {
            stmt.bindLong(1, idTipoCultivoRef);
        }
        stmt.bindString(2, entity.getDescricao());
        stmt.bindLong(3, entity.getIndAtivo());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, TipoCultivo entity) {
        stmt.clearBindings();
 
        Long idTipoCultivoRef = entity.getIdTipoCultivoRef();
        if (idTipoCultivoRef != null) {
            stmt.bindLong(1, idTipoCultivoRef);
        }
        stmt.bindString(2, entity.getDescricao());
        stmt.bindLong(3, entity.getIndAtivo());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public TipoCultivo readEntity(Cursor cursor, int offset) {
        TipoCultivo entity = new TipoCultivo();
        readEntity(cursor, entity, offset);
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, TipoCultivo entity, int offset) {
        entity.setIdTipoCultivoRef(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDescricao(cursor.getString(offset + 1));
        entity.setIndAtivo(cursor.getInt(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(TipoCultivo entity, long rowId) {
        entity.setIdTipoCultivoRef(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(TipoCultivo entity) {
        if(entity != null) {
            return entity.getIdTipoCultivoRef();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(TipoCultivo entity) {
        return entity.getIdTipoCultivoRef() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}