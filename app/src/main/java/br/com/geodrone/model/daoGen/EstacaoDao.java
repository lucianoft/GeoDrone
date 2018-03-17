package br.com.geodrone.model.daoGen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import br.com.geodrone.model.Estacao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "TB_ESTACAO".
*/
public class EstacaoDao extends AbstractDao<Estacao, Long> {

    public static final String TABLENAME = "TB_ESTACAO";

    /**
     * Properties of entity Estacao.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "ID_ESTACAO");
        public final static Property IdCliente = new Property(1, Long.class, "idCliente", false, "ID_CLIENTE");
        public final static Property Latitude = new Property(2, Long.class, "latitude", false, "LATITUDE");
        public final static Property Longitude = new Property(3, Long.class, "longitude", false, "LONGITUDE");
        public final static Property DtInstalacao = new Property(4, java.util.Date.class, "dtInstalacao", false, "DT_INSTALACAO");
        public final static Property DtInclusao = new Property(5, java.util.Date.class, "dtInclusao", false, "DT_INCLUSAO");
        public final static Property DtAlteracao = new Property(6, java.util.Date.class, "dtAlteracao", false, "DT_ALTERACAO");
        public final static Property IdEstacaoWeb = new Property(7, Long.class, "idEstacaoWeb", false, "ID_ESTACAO_WEB");
    }


    public EstacaoDao(DaoConfig config) {
        super(config);
    }
    
    public EstacaoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TB_ESTACAO\" (" + //
                "\"ID_ESTACAO\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"ID_CLIENTE\" INTEGER NOT NULL ," + // 1: idCliente
                "\"LATITUDE\" INTEGER NOT NULL ," + // 2: latitude
                "\"LONGITUDE\" INTEGER NOT NULL ," + // 3: longitude
                "\"DT_INSTALACAO\" INTEGER NOT NULL ," + // 4: dtInstalacao
                "\"DT_INCLUSAO\" INTEGER NOT NULL ," + // 5: dtInclusao
                "\"DT_ALTERACAO\" INTEGER NOT NULL ," + // 6: dtAlteracao
                "\"ID_ESTACAO_WEB\" INTEGER);"); // 7: idEstacaoWeb
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TB_ESTACAO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Estacao entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getIdCliente());
        stmt.bindLong(3, entity.getLatitude());
        stmt.bindLong(4, entity.getLongitude());
        stmt.bindLong(5, entity.getDtInstalacao().getTime());
        stmt.bindLong(6, entity.getDtInclusao().getTime());
        stmt.bindLong(7, entity.getDtAlteracao().getTime());
 
        Long idEstacaoWeb = entity.getIdEstacaoWeb();
        if (idEstacaoWeb != null) {
            stmt.bindLong(8, idEstacaoWeb);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Estacao entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getIdCliente());
        stmt.bindLong(3, entity.getLatitude());
        stmt.bindLong(4, entity.getLongitude());
        stmt.bindLong(5, entity.getDtInstalacao().getTime());
        stmt.bindLong(6, entity.getDtInclusao().getTime());
        stmt.bindLong(7, entity.getDtAlteracao().getTime());
 
        Long idEstacaoWeb = entity.getIdEstacaoWeb();
        if (idEstacaoWeb != null) {
            stmt.bindLong(8, idEstacaoWeb);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Estacao readEntity(Cursor cursor, int offset) {
        Estacao entity = new Estacao();
        readEntity(cursor, entity, offset);
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Estacao entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setIdCliente(cursor.getLong(offset + 1));
        entity.setLatitude(cursor.getLong(offset + 2));
        entity.setLongitude(cursor.getLong(offset + 3));
        entity.setDtInstalacao(new java.util.Date(cursor.getLong(offset + 4)));
        entity.setDtInclusao(new java.util.Date(cursor.getLong(offset + 5)));
        entity.setDtAlteracao(new java.util.Date(cursor.getLong(offset + 6)));
        entity.setIdEstacaoWeb(cursor.isNull(offset + 7) ? null : cursor.getLong(offset + 7));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Estacao entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Estacao entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Estacao entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}