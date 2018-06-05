package br.com.geodrone.model.daoGen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import br.com.geodrone.model.RegistroDefensivo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "GEO_REGISTRO_DEFENSIVO".
*/
public class RegistroDefensivoDao extends AbstractDao<RegistroDefensivo, Long> {

    public static final String TABLENAME = "GEO_REGISTRO_DEFENSIVO";

    /**
     * Properties of entity RegistroDefensivo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "ID_REGISTRO_DEFENSIVO_DISP");
        public final static Property IdRipoDefensivo = new Property(1, Long.class, "idRipoDefensivo", false, "ID_TIPO_DEFENSIVO");
        public final static Property DtRegistro = new Property(2, java.util.Date.class, "dtRegistro", false, "DT_REGISTRO");
        public final static Property Dose = new Property(3, Double.class, "dose", false, "DOSE");
        public final static Property IdRegistroDefensivo = new Property(4, Long.class, "idRegistroDefensivo", false, "ID_REGISTRO_DEFENSIVO");
        public final static Property IdCliente = new Property(5, Long.class, "idCliente", false, "ID_CLIENTE");
        public final static Property IdDispositivo = new Property(6, Long.class, "idDispositivo", false, "ID_DISPOSITIVO");
        public final static Property DtInclusao = new Property(7, java.util.Date.class, "dtInclusao", false, "DT_INCLUSAO");
        public final static Property DtAlteracao = new Property(8, java.util.Date.class, "dtAlteracao", false, "DT_ALTERACAO");
        public final static Property VersaoSistema = new Property(9, Long.class, "versaoSistema", false, "VERSAO_SISTEMA");
        public final static Property IdUsuarioReg = new Property(10, Long.class, "idUsuarioReg", false, "ID_USUARIO_REG");
    }


    public RegistroDefensivoDao(DaoConfig config) {
        super(config);
    }
    
    public RegistroDefensivoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"GEO_REGISTRO_DEFENSIVO\" (" + //
                "\"ID_REGISTRO_DEFENSIVO_DISP\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"ID_TIPO_DEFENSIVO\" INTEGER," + // 1: idRipoDefensivo
                "\"DT_REGISTRO\" INTEGER NOT NULL ," + // 2: dtRegistro
                "\"DOSE\" REAL NOT NULL ," + // 3: dose
                "\"ID_REGISTRO_DEFENSIVO\" INTEGER," + // 4: idRegistroDefensivo
                "\"ID_CLIENTE\" INTEGER NOT NULL ," + // 5: idCliente
                "\"ID_DISPOSITIVO\" INTEGER NOT NULL ," + // 6: idDispositivo
                "\"DT_INCLUSAO\" INTEGER NOT NULL ," + // 7: dtInclusao
                "\"DT_ALTERACAO\" INTEGER NOT NULL ," + // 8: dtAlteracao
                "\"VERSAO_SISTEMA\" INTEGER NOT NULL ," + // 9: versaoSistema
                "\"ID_USUARIO_REG\" INTEGER NOT NULL );"); // 10: idUsuarioReg
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"GEO_REGISTRO_DEFENSIVO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, RegistroDefensivo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long idRipoDefensivo = entity.getIdRipoDefensivo();
        if (idRipoDefensivo != null) {
            stmt.bindLong(2, idRipoDefensivo);
        }
        stmt.bindLong(3, entity.getDtRegistro().getTime());
        stmt.bindDouble(4, entity.getDose());
 
        Long idRegistroDefensivo = entity.getIdRegistroDefensivo();
        if (idRegistroDefensivo != null) {
            stmt.bindLong(5, idRegistroDefensivo);
        }
        stmt.bindLong(6, entity.getIdCliente());
        stmt.bindLong(7, entity.getIdDispositivo());
        stmt.bindLong(8, entity.getDtInclusao().getTime());
        stmt.bindLong(9, entity.getDtAlteracao().getTime());
        stmt.bindLong(10, entity.getVersaoSistema());
        stmt.bindLong(11, entity.getIdUsuarioReg());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, RegistroDefensivo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long idRipoDefensivo = entity.getIdRipoDefensivo();
        if (idRipoDefensivo != null) {
            stmt.bindLong(2, idRipoDefensivo);
        }
        stmt.bindLong(3, entity.getDtRegistro().getTime());
        stmt.bindDouble(4, entity.getDose());
 
        Long idRegistroDefensivo = entity.getIdRegistroDefensivo();
        if (idRegistroDefensivo != null) {
            stmt.bindLong(5, idRegistroDefensivo);
        }
        stmt.bindLong(6, entity.getIdCliente());
        stmt.bindLong(7, entity.getIdDispositivo());
        stmt.bindLong(8, entity.getDtInclusao().getTime());
        stmt.bindLong(9, entity.getDtAlteracao().getTime());
        stmt.bindLong(10, entity.getVersaoSistema());
        stmt.bindLong(11, entity.getIdUsuarioReg());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public RegistroDefensivo readEntity(Cursor cursor, int offset) {
        RegistroDefensivo entity = new RegistroDefensivo();
        readEntity(cursor, entity, offset);
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, RegistroDefensivo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setIdRipoDefensivo(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setDtRegistro(new java.util.Date(cursor.getLong(offset + 2)));
        entity.setDose(cursor.getDouble(offset + 3));
        entity.setIdRegistroDefensivo(cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4));
        entity.setIdCliente(cursor.getLong(offset + 5));
        entity.setIdDispositivo(cursor.getLong(offset + 6));
        entity.setDtInclusao(new java.util.Date(cursor.getLong(offset + 7)));
        entity.setDtAlteracao(new java.util.Date(cursor.getLong(offset + 8)));
        entity.setVersaoSistema(cursor.getLong(offset + 9));
        entity.setIdUsuarioReg(cursor.getLong(offset + 10));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(RegistroDefensivo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(RegistroDefensivo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(RegistroDefensivo entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
