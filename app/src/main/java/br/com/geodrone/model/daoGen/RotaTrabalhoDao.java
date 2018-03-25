package br.com.geodrone.model.daoGen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import br.com.geodrone.model.RotaTrabalho;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "TB_ROTA_TRABALHO".
*/
public class RotaTrabalhoDao extends AbstractDao<RotaTrabalho, Long> {

    public static final String TABLENAME = "TB_ROTA_TRABALHO";

    /**
     * Properties of entity RotaTrabalho.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "ID_ROTA_TRABALHO");
        public final static Property IdCliente = new Property(1, Long.class, "idCliente", false, "ID_CLIENTE");
        public final static Property Latitude = new Property(2, Double.class, "latitude", false, "LATITUDE");
        public final static Property Longitude = new Property(3, Double.class, "longitude", false, "LONGITUDE");
        public final static Property DtInclusao = new Property(4, java.util.Date.class, "dtInclusao", false, "DT_INCLUSAO");
        public final static Property DtAlteracao = new Property(5, java.util.Date.class, "dtAlteracao", false, "DT_ALTERACAO");
        public final static Property IdUsuario = new Property(6, Long.class, "idUsuario", false, "ID_USUARIO");
        public final static Property IdDispositivo = new Property(7, Long.class, "idDispositivo", false, "ID_DISPOSITIVO");
        public final static Property IdRotaTrabalhoWeb = new Property(8, Long.class, "idRotaTrabalhoWeb", false, "ID_ROTA_TRABALHO_WEB");
    }


    public RotaTrabalhoDao(DaoConfig config) {
        super(config);
    }
    
    public RotaTrabalhoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TB_ROTA_TRABALHO\" (" + //
                "\"ID_ROTA_TRABALHO\" INTEGER PRIMARY KEY ," + // 0: id
                "\"ID_CLIENTE\" INTEGER NOT NULL ," + // 1: idCliente
                "\"LATITUDE\" REAL NOT NULL ," + // 2: latitude
                "\"LONGITUDE\" REAL NOT NULL ," + // 3: longitude
                "\"DT_INCLUSAO\" INTEGER NOT NULL ," + // 4: dtInclusao
                "\"DT_ALTERACAO\" INTEGER NOT NULL ," + // 5: dtAlteracao
                "\"ID_USUARIO\" INTEGER," + // 6: idUsuario
                "\"ID_DISPOSITIVO\" INTEGER," + // 7: idDispositivo
                "\"ID_ROTA_TRABALHO_WEB\" INTEGER);"); // 8: idRotaTrabalhoWeb
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TB_ROTA_TRABALHO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, RotaTrabalho entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getIdCliente());
        stmt.bindDouble(3, entity.getLatitude());
        stmt.bindDouble(4, entity.getLongitude());
        stmt.bindLong(5, entity.getDtInclusao().getTime());
        stmt.bindLong(6, entity.getDtAlteracao().getTime());
 
        Long idUsuario = entity.getIdUsuario();
        if (idUsuario != null) {
            stmt.bindLong(7, idUsuario);
        }
 
        Long idDispositivo = entity.getIdDispositivo();
        if (idDispositivo != null) {
            stmt.bindLong(8, idDispositivo);
        }
 
        Long idRotaTrabalhoWeb = entity.getIdRotaTrabalhoWeb();
        if (idRotaTrabalhoWeb != null) {
            stmt.bindLong(9, idRotaTrabalhoWeb);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, RotaTrabalho entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getIdCliente());
        stmt.bindDouble(3, entity.getLatitude());
        stmt.bindDouble(4, entity.getLongitude());
        stmt.bindLong(5, entity.getDtInclusao().getTime());
        stmt.bindLong(6, entity.getDtAlteracao().getTime());
 
        Long idUsuario = entity.getIdUsuario();
        if (idUsuario != null) {
            stmt.bindLong(7, idUsuario);
        }
 
        Long idDispositivo = entity.getIdDispositivo();
        if (idDispositivo != null) {
            stmt.bindLong(8, idDispositivo);
        }
 
        Long idRotaTrabalhoWeb = entity.getIdRotaTrabalhoWeb();
        if (idRotaTrabalhoWeb != null) {
            stmt.bindLong(9, idRotaTrabalhoWeb);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public RotaTrabalho readEntity(Cursor cursor, int offset) {
        RotaTrabalho entity = new RotaTrabalho();
        readEntity(cursor, entity, offset);
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, RotaTrabalho entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setIdCliente(cursor.getLong(offset + 1));
        entity.setLatitude(cursor.getDouble(offset + 2));
        entity.setLongitude(cursor.getDouble(offset + 3));
        entity.setDtInclusao(new java.util.Date(cursor.getLong(offset + 4)));
        entity.setDtAlteracao(new java.util.Date(cursor.getLong(offset + 5)));
        entity.setIdUsuario(cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6));
        entity.setIdDispositivo(cursor.isNull(offset + 7) ? null : cursor.getLong(offset + 7));
        entity.setIdRotaTrabalhoWeb(cursor.isNull(offset + 8) ? null : cursor.getLong(offset + 8));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(RotaTrabalho entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(RotaTrabalho entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(RotaTrabalho entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
