package br.com.geodrone.model.daoGen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import br.com.geodrone.model.constantes.FlagStatusCliente;
import br.com.geodrone.model.converter.FlagStatusClienteConverter;

import br.com.geodrone.model.Cliente;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "TB_CLIENTE".
*/
public class ClienteDao extends AbstractDao<Cliente, Long> {

    public static final String TABLENAME = "TB_CLIENTE";

    /**
     * Properties of entity Cliente.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "ID_CLIENTE");
        public final static Property IndPessoaFisica = new Property(1, Integer.class, "indPessoaFisica", false, "IND_PESSOA_FISICA");
        public final static Property NomeRazaoSocial = new Property(2, String.class, "nomeRazaoSocial", false, "NOME_RAZAO_SOCIAL");
        public final static Property Cfp = new Property(3, Long.class, "cfp", false, "CPF");
        public final static Property Cnpj = new Property(4, Long.class, "cnpj", false, "CNPJ");
        public final static Property NomeFantasia = new Property(5, String.class, "nomeFantasia", false, "SOBRENOME");
        public final static Property Segmento = new Property(6, String.class, "segmento", false, "SEGMENTO");
        public final static Property Email = new Property(7, String.class, "email", false, "EMAIL");
        public final static Property Telefone = new Property(8, String.class, "telefone", false, "TELEFONE");
        public final static Property Celular = new Property(9, String.class, "celular", false, "CELULAR");
        public final static Property FlagStatus = new Property(10, String.class, "flagStatus", false, "FLAG_STATUS");
    }

    private final FlagStatusClienteConverter flagStatusConverter = new FlagStatusClienteConverter();

    public ClienteDao(DaoConfig config) {
        super(config);
    }
    
    public ClienteDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TB_CLIENTE\" (" + //
                "\"ID_CLIENTE\" INTEGER PRIMARY KEY ," + // 0: id
                "\"IND_PESSOA_FISICA\" INTEGER NOT NULL ," + // 1: indPessoaFisica
                "\"NOME_RAZAO_SOCIAL\" TEXT NOT NULL ," + // 2: nomeRazaoSocial
                "\"CPF\" INTEGER," + // 3: cfp
                "\"CNPJ\" INTEGER," + // 4: cnpj
                "\"SOBRENOME\" TEXT," + // 5: nomeFantasia
                "\"SEGMENTO\" TEXT," + // 6: segmento
                "\"EMAIL\" TEXT NOT NULL ," + // 7: email
                "\"TELEFONE\" TEXT," + // 8: telefone
                "\"CELULAR\" TEXT," + // 9: celular
                "\"FLAG_STATUS\" TEXT NOT NULL );"); // 10: flagStatus
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TB_CLIENTE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Cliente entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getIndPessoaFisica());
        stmt.bindString(3, entity.getNomeRazaoSocial());
 
        Long cfp = entity.getCfp();
        if (cfp != null) {
            stmt.bindLong(4, cfp);
        }
 
        Long cnpj = entity.getCnpj();
        if (cnpj != null) {
            stmt.bindLong(5, cnpj);
        }
 
        String nomeFantasia = entity.getNomeFantasia();
        if (nomeFantasia != null) {
            stmt.bindString(6, nomeFantasia);
        }
 
        String segmento = entity.getSegmento();
        if (segmento != null) {
            stmt.bindString(7, segmento);
        }
        stmt.bindString(8, entity.getEmail());
 
        String telefone = entity.getTelefone();
        if (telefone != null) {
            stmt.bindString(9, telefone);
        }
 
        String celular = entity.getCelular();
        if (celular != null) {
            stmt.bindString(10, celular);
        }
        stmt.bindString(11, flagStatusConverter.convertToDatabaseValue(entity.getFlagStatus()));
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Cliente entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getIndPessoaFisica());
        stmt.bindString(3, entity.getNomeRazaoSocial());
 
        Long cfp = entity.getCfp();
        if (cfp != null) {
            stmt.bindLong(4, cfp);
        }
 
        Long cnpj = entity.getCnpj();
        if (cnpj != null) {
            stmt.bindLong(5, cnpj);
        }
 
        String nomeFantasia = entity.getNomeFantasia();
        if (nomeFantasia != null) {
            stmt.bindString(6, nomeFantasia);
        }
 
        String segmento = entity.getSegmento();
        if (segmento != null) {
            stmt.bindString(7, segmento);
        }
        stmt.bindString(8, entity.getEmail());
 
        String telefone = entity.getTelefone();
        if (telefone != null) {
            stmt.bindString(9, telefone);
        }
 
        String celular = entity.getCelular();
        if (celular != null) {
            stmt.bindString(10, celular);
        }
        stmt.bindString(11, flagStatusConverter.convertToDatabaseValue(entity.getFlagStatus()));
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Cliente readEntity(Cursor cursor, int offset) {
        Cliente entity = new Cliente();
        readEntity(cursor, entity, offset);
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Cliente entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setIndPessoaFisica(cursor.getInt(offset + 1));
        entity.setNomeRazaoSocial(cursor.getString(offset + 2));
        entity.setCfp(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setCnpj(cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4));
        entity.setNomeFantasia(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setSegmento(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setEmail(cursor.getString(offset + 7));
        entity.setTelefone(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setCelular(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setFlagStatus(flagStatusConverter.convertToEntityProperty(cursor.getString(offset + 10)));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Cliente entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Cliente entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Cliente entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
