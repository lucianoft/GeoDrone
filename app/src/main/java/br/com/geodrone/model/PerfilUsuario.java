package br.com.geodrone.model;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

import java.util.Date;

import br.com.geodrone.model.constantes.FlagPerfilUsuario;
import br.com.geodrone.model.converter.FlagPerfilUsuarioConverter;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by fernandes on 17/03/2018.
 */
@Entity(generateConstructors = false, createInDb = true, nameInDb = "TB_PERFIL_USUARIO")

public class PerfilUsuario {

    @Id(autoincrement =  false)
    @Property(nameInDb = "ID_PERFIL_USUARIO")
    private Long id;

    @Property(nameInDb = "DESCRICAO" )
    @NotNull
    private String descricao;

    @Property(nameInDb = "FLAG_TIPO")
    @Convert(converter = FlagPerfilUsuarioConverter.class, columnType = String.class)
    private FlagPerfilUsuario flagTipo;

    @NotNull()
    @Property(nameInDb = "DT_INCLUSAO")
    private Date dtInclusao;

    @NotNull()
    @Property(nameInDb = "DT_ALTERACAO")
    private Date dtAlteracao;

    @Property(nameInDb = "ID_USUARIO")
    private Long idUsuario;

    public PerfilUsuario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public FlagPerfilUsuario getFlagTipo() {
        return flagTipo;
    }

    public void setFlagTipo(FlagPerfilUsuario flagTipo) {
        this.flagTipo = flagTipo;
    }

    public Date getDtInclusao() {
        return dtInclusao;
    }

    public void setDtInclusao(Date dtInclusao) {
        this.dtInclusao = dtInclusao;
    }

    public Date getDtAlteracao() {
        return dtAlteracao;
    }

    public void setDtAlteracao(Date dtAlteracao) {
        this.dtAlteracao = dtAlteracao;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
}
