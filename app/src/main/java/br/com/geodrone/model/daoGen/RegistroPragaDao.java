package br.com.geodrone.model.daoGen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import br.com.geodrone.model.RegistroPraga;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "GEO_REGISTRO_PRAGA".
*/
public class RegistroPragaDao extends AbstractDao<RegistroPraga, Long> {

    public static final String TABLENAME = "GEO_REGISTRO_PRAGA";

    /**
     * Properties of entity RegistroPraga.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "ID_REGISTRO_PRAGA_DISP");
        public final static Property Qtde = new Property(1, Integer.class, "qtde", false, "QTDE");
        public final static Property Observacao = new Property(2, String.class, "observacao", false, "OBSERVACAO");
        public final static Property IdPraga = new Property(3, Long.class, "idPraga", false, "ID_PRAGA");
        public final static Property IdRegistroPraga = new Property(4, Long.class, "idRegistroPraga", false, "ID_REGISTRO_PRAGA");
        public final static Property IdCliente = new Property(5, Long.class, "idCliente", false, "ID_CLIENTE");
        public final static Property Latitude = new Property(6, Double.class, "latitude", false, "LATITUDE");
        public final static Property Longitude = new Property(7, Double.class, "longitude", false, "LONGITUDE");
        public final static Property IdDispositivo = new Property(8, Long.class, "idDispositivo", false, "ID_DISPOSITIVO");
        public final static Property DtInclusao = new Property(9, java.util.Date.class, "dtInclusao", false, "DT_INCLUSAO");
        public final static Property DtAlteracao = new Property(10, java.util.Date.class, "dtAlteracao", false, "DT_ALTERACAO");
        public final static Property VersaoSistema = new Property(11, Long.class, "versaoSistema", false, "VERSAO_SISTEMA");
        public final static Property IdUsuarioReg = new Property(12, Long.class, "idUsuarioReg", false, "ID_USUARIO_REG");
    }


    public RegistroPragaDao(DaoConfig config) {
        super(config);
    }
    
    public RegistroPragaDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"GEO_REGISTRO_PRAGA\" (" + //
                "\"ID_REGISTRO_PRAGA_DISP\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"QTDE\" INTEGER," + // 1: qtde
                "\"OBSERVACAO\" TEXT," + // 2: observacao
                "\"ID_PRAGA\" INTEGER," + // 3: idPraga
                "\"ID_REGISTRO_PRAGA\" INTEGER," + // 4: idRegistroPraga
                "\"ID_CLIENTE\" INTEGER NOT NULL ," + // 5: idCliente
                "\"LATITUDE\" REAL NOT NULL ," + // 6: latitude
                "\"LONGITUDE\" REAL NOT NULL ," + // 7: longitude
                "\"ID_DISPOSITIVO\" INTEGER NOT NULL ," + // 8: idDispositivo
                "\"DT_INCLUSAO\" INTEGER NOT NULL ," + // 9: dtInclusao
                "\"DT_ALTERACAO\" INTEGER NOT NULL ," + // 10: dtAlteracao
                "\"VERSAO_SISTEMA\" INTEGER NOT NULL ," + // 11: versaoSistema
                "\"ID_USUARIO_REG\" INTEGER NOT NULL );"); // 12: idUsuarioReg
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"GEO_REGISTRO_PRAGA\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, RegistroPraga entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer qtde = entity.getQtde();
        if (qtde != null) {
            stmt.bindLong(2, qtde);
        }
 
        String observacao = entity.getObservacao();
        if (observacao != null) {
            stmt.bindString(3, observacao);
        }
 
        Long idPraga = entity.getIdPraga();
        if (idPraga != null) {
            stmt.bindLong(4, idPraga);
        }
 
        Long idRegistroPraga = entity.getIdRegistroPraga();
        if (idRegistroPraga != null) {
            stmt.bindLong(5, idRegistroPraga);
        }
        stmt.bindLong(6, entity.getIdCliente());
        stmt.bindDouble(7, entity.getLatitude());
        stmt.bindDouble(8, entity.getLongitude());
        stmt.bindLong(9, entity.getIdDispositivo());
        stmt.bindLong(10, entity.getDtInclusao().getTime());
        stmt.bindLong(11, entity.getDtAlteracao().getTime());
        stmt.bindLong(12, entity.getVersaoSistema());
        stmt.bindLong(13, entity.getIdUsuarioReg());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, RegistroPraga entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer qtde = entity.getQtde();
        if (qtde != null) {
            stmt.bindLong(2, qtde);
        }
 
        String observacao = entity.getObservacao();
        if (observacao != null) {
            stmt.bindString(3, observacao);
        }
 
        Long idPraga = entity.getIdPraga();
        if (idPraga != null) {
            stmt.bindLong(4, idPraga);
        }
 
        Long idRegistroPraga = entity.getIdRegistroPraga();
        if (idRegistroPraga != null) {
            stmt.bindLong(5, idRegistroPraga);
        }
        stmt.bindLong(6, entity.getIdCliente());
        stmt.bindDouble(7, entity.getLatitude());
        stmt.bindDouble(8, entity.getLongitude());
        stmt.bindLong(9, entity.getIdDispositivo());
        stmt.bindLong(10, entity.getDtInclusao().getTime());
        stmt.bindLong(11, entity.getDtAlteracao().getTime());
        stmt.bindLong(12, entity.getVersaoSistema());
        stmt.bindLong(13, entity.getIdUsuarioReg());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public RegistroPraga readEntity(Cursor cursor, int offset) {
        RegistroPraga entity = new RegistroPraga();
        readEntity(cursor, entity, offset);
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, RegistroPraga entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setQtde(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setObservacao(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setIdPraga(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setIdRegistroPraga(cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4));
        entity.setIdCliente(cursor.getLong(offset + 5));
        entity.setLatitude(cursor.getDouble(offset + 6));
        entity.setLongitude(cursor.getDouble(offset + 7));
        entity.setIdDispositivo(cursor.getLong(offset + 8));
        entity.setDtInclusao(new java.util.Date(cursor.getLong(offset + 9)));
        entity.setDtAlteracao(new java.util.Date(cursor.getLong(offset + 10)));
        entity.setVersaoSistema(cursor.getLong(offset + 11));
        entity.setIdUsuarioReg(cursor.getLong(offset + 12));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(RegistroPraga entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(RegistroPraga entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(RegistroPraga entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
