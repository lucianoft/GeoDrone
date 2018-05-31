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
 * DAO for table "GEO_ROTA_TRABALHO".
*/
public class RotaTrabalhoDao extends AbstractDao<RotaTrabalho, Long> {

    public static final String TABLENAME = "GEO_ROTA_TRABALHO";

    /**
     * Properties of entity RotaTrabalho.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "ID_ROTA_TRABALHO_DISP");
        public final static Property IdRotaTrabalho = new Property(1, Long.class, "idRotaTrabalho", false, "ID_ROTA_TRABALHO");
        public final static Property IdTalhaoDisp = new Property(2, Long.class, "idTalhaoDisp", false, "ID_TALHAO_DISP");
        public final static Property IdCliente = new Property(3, Long.class, "idCliente", false, "ID_CLIENTE");
        public final static Property FlagTipo = new Property(4, String.class, "flagTipo", false, "FLAG_TIPO");
        public final static Property FlagOperacaoRota = new Property(5, String.class, "flagOperacaoRota", false, "FLAG_OPERACAO_ROTA");
        public final static Property DtRegistro = new Property(6, java.util.Date.class, "dtRegistro", false, "DT_REGISTRO");
        public final static Property Latitude = new Property(7, Double.class, "latitude", false, "LATITUDE");
        public final static Property Longitude = new Property(8, Double.class, "longitude", false, "LONGITUDE");
        public final static Property IdDispositivo = new Property(9, Long.class, "idDispositivo", false, "ID_DISPOSITIVO");
        public final static Property DtInclusao = new Property(10, java.util.Date.class, "dtInclusao", false, "DT_INCLUSAO");
        public final static Property DtAlteracao = new Property(11, java.util.Date.class, "dtAlteracao", false, "DT_ALTERACAO");
        public final static Property VersaoSistema = new Property(12, Long.class, "versaoSistema", false, "VERSAO_SISTEMA");
        public final static Property IdUsuarioReg = new Property(13, Long.class, "idUsuarioReg", false, "ID_USUARIO_REG");
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
        db.execSQL("CREATE TABLE " + constraint + "\"GEO_ROTA_TRABALHO\" (" + //
                "\"ID_ROTA_TRABALHO_DISP\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"ID_ROTA_TRABALHO\" INTEGER," + // 1: idRotaTrabalho
                "\"ID_TALHAO_DISP\" INTEGER," + // 2: idTalhaoDisp
                "\"ID_CLIENTE\" INTEGER NOT NULL ," + // 3: idCliente
                "\"FLAG_TIPO\" TEXT," + // 4: flagTipo
                "\"FLAG_OPERACAO_ROTA\" TEXT," + // 5: flagOperacaoRota
                "\"DT_REGISTRO\" INTEGER NOT NULL ," + // 6: dtRegistro
                "\"LATITUDE\" REAL NOT NULL ," + // 7: latitude
                "\"LONGITUDE\" REAL NOT NULL ," + // 8: longitude
                "\"ID_DISPOSITIVO\" INTEGER NOT NULL ," + // 9: idDispositivo
                "\"DT_INCLUSAO\" INTEGER NOT NULL ," + // 10: dtInclusao
                "\"DT_ALTERACAO\" INTEGER NOT NULL ," + // 11: dtAlteracao
                "\"VERSAO_SISTEMA\" INTEGER NOT NULL ," + // 12: versaoSistema
                "\"ID_USUARIO_REG\" INTEGER NOT NULL );"); // 13: idUsuarioReg
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"GEO_ROTA_TRABALHO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, RotaTrabalho entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long idRotaTrabalho = entity.getIdRotaTrabalho();
        if (idRotaTrabalho != null) {
            stmt.bindLong(2, idRotaTrabalho);
        }
 
        Long idTalhaoDisp = entity.getIdTalhaoDisp();
        if (idTalhaoDisp != null) {
            stmt.bindLong(3, idTalhaoDisp);
        }
        stmt.bindLong(4, entity.getIdCliente());
 
        String flagTipo = entity.getFlagTipo();
        if (flagTipo != null) {
            stmt.bindString(5, flagTipo);
        }
 
        String flagOperacaoRota = entity.getFlagOperacaoRota();
        if (flagOperacaoRota != null) {
            stmt.bindString(6, flagOperacaoRota);
        }
        stmt.bindLong(7, entity.getDtRegistro().getTime());
        stmt.bindDouble(8, entity.getLatitude());
        stmt.bindDouble(9, entity.getLongitude());
        stmt.bindLong(10, entity.getIdDispositivo());
        stmt.bindLong(11, entity.getDtInclusao().getTime());
        stmt.bindLong(12, entity.getDtAlteracao().getTime());
        stmt.bindLong(13, entity.getVersaoSistema());
        stmt.bindLong(14, entity.getIdUsuarioReg());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, RotaTrabalho entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long idRotaTrabalho = entity.getIdRotaTrabalho();
        if (idRotaTrabalho != null) {
            stmt.bindLong(2, idRotaTrabalho);
        }
 
        Long idTalhaoDisp = entity.getIdTalhaoDisp();
        if (idTalhaoDisp != null) {
            stmt.bindLong(3, idTalhaoDisp);
        }
        stmt.bindLong(4, entity.getIdCliente());
 
        String flagTipo = entity.getFlagTipo();
        if (flagTipo != null) {
            stmt.bindString(5, flagTipo);
        }
 
        String flagOperacaoRota = entity.getFlagOperacaoRota();
        if (flagOperacaoRota != null) {
            stmt.bindString(6, flagOperacaoRota);
        }
        stmt.bindLong(7, entity.getDtRegistro().getTime());
        stmt.bindDouble(8, entity.getLatitude());
        stmt.bindDouble(9, entity.getLongitude());
        stmt.bindLong(10, entity.getIdDispositivo());
        stmt.bindLong(11, entity.getDtInclusao().getTime());
        stmt.bindLong(12, entity.getDtAlteracao().getTime());
        stmt.bindLong(13, entity.getVersaoSistema());
        stmt.bindLong(14, entity.getIdUsuarioReg());
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
        entity.setIdRotaTrabalho(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setIdTalhaoDisp(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setIdCliente(cursor.getLong(offset + 3));
        entity.setFlagTipo(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setFlagOperacaoRota(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setDtRegistro(new java.util.Date(cursor.getLong(offset + 6)));
        entity.setLatitude(cursor.getDouble(offset + 7));
        entity.setLongitude(cursor.getDouble(offset + 8));
        entity.setIdDispositivo(cursor.getLong(offset + 9));
        entity.setDtInclusao(new java.util.Date(cursor.getLong(offset + 10)));
        entity.setDtAlteracao(new java.util.Date(cursor.getLong(offset + 11)));
        entity.setVersaoSistema(cursor.getLong(offset + 12));
        entity.setIdUsuarioReg(cursor.getLong(offset + 13));
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
