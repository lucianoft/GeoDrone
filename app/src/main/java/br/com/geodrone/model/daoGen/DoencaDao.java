package br.com.geodrone.model.daoGen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import br.com.geodrone.model.Doenca;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "GEO_DOENCA".
*/
public class DoencaDao extends AbstractDao<Doenca, Long> {

    public static final String TABLENAME = "GEO_DOENCA";

    /**
     * Properties of entity Doenca.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "ID_DOENCA");
        public final static Property Descricao = new Property(1, String.class, "descricao", false, "DESCRICAO");
        public final static Property DescricaoCientifica = new Property(2, String.class, "descricaoCientifica", false, "DESCRICAO_CIENTIFICA");
        public final static Property IdTipoCultivo = new Property(3, Long.class, "idTipoCultivo", false, "ID_TIPO_CULTIVO");
        public final static Property IndAtivo = new Property(4, Integer.class, "indAtivo", false, "IND_ATIVO");
        public final static Property DtInclusao = new Property(5, java.util.Date.class, "dtInclusao", false, "DT_INCLUSAO");
        public final static Property DtAlteracao = new Property(6, java.util.Date.class, "dtAlteracao", false, "DT_ALTERACAO");
        public final static Property VersaoSistema = new Property(7, Long.class, "versaoSistema", false, "VERSAO_SISTEMA");
    }


    public DoencaDao(DaoConfig config) {
        super(config);
    }
    
    public DoencaDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"GEO_DOENCA\" (" + //
                "\"ID_DOENCA\" INTEGER PRIMARY KEY ," + // 0: id
                "\"DESCRICAO\" TEXT," + // 1: descricao
                "\"DESCRICAO_CIENTIFICA\" TEXT," + // 2: descricaoCientifica
                "\"ID_TIPO_CULTIVO\" INTEGER," + // 3: idTipoCultivo
                "\"IND_ATIVO\" INTEGER NOT NULL ," + // 4: indAtivo
                "\"DT_INCLUSAO\" INTEGER NOT NULL ," + // 5: dtInclusao
                "\"DT_ALTERACAO\" INTEGER NOT NULL ," + // 6: dtAlteracao
                "\"VERSAO_SISTEMA\" INTEGER NOT NULL );"); // 7: versaoSistema
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"GEO_DOENCA\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Doenca entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String descricao = entity.getDescricao();
        if (descricao != null) {
            stmt.bindString(2, descricao);
        }
 
        String descricaoCientifica = entity.getDescricaoCientifica();
        if (descricaoCientifica != null) {
            stmt.bindString(3, descricaoCientifica);
        }
 
        Long idTipoCultivo = entity.getIdTipoCultivo();
        if (idTipoCultivo != null) {
            stmt.bindLong(4, idTipoCultivo);
        }
        stmt.bindLong(5, entity.getIndAtivo());
        stmt.bindLong(6, entity.getDtInclusao().getTime());
        stmt.bindLong(7, entity.getDtAlteracao().getTime());
        stmt.bindLong(8, entity.getVersaoSistema());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Doenca entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String descricao = entity.getDescricao();
        if (descricao != null) {
            stmt.bindString(2, descricao);
        }
 
        String descricaoCientifica = entity.getDescricaoCientifica();
        if (descricaoCientifica != null) {
            stmt.bindString(3, descricaoCientifica);
        }
 
        Long idTipoCultivo = entity.getIdTipoCultivo();
        if (idTipoCultivo != null) {
            stmt.bindLong(4, idTipoCultivo);
        }
        stmt.bindLong(5, entity.getIndAtivo());
        stmt.bindLong(6, entity.getDtInclusao().getTime());
        stmt.bindLong(7, entity.getDtAlteracao().getTime());
        stmt.bindLong(8, entity.getVersaoSistema());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Doenca readEntity(Cursor cursor, int offset) {
        Doenca entity = new Doenca();
        readEntity(cursor, entity, offset);
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Doenca entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setDescricao(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setDescricaoCientifica(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setIdTipoCultivo(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setIndAtivo(cursor.getInt(offset + 4));
        entity.setDtInclusao(new java.util.Date(cursor.getLong(offset + 5)));
        entity.setDtAlteracao(new java.util.Date(cursor.getLong(offset + 6)));
        entity.setVersaoSistema(cursor.getLong(offset + 7));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Doenca entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Doenca entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Doenca entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
