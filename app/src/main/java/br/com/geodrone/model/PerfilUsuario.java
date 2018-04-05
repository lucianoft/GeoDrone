package br.com.geodrone.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;
import java.util.Date;

import br.com.geodrone.model.api.AuditModel;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by fernandes on 17/03/2018.
 */
@Entity(generateConstructors = false, createInDb = true, nameInDb = "TB_PERFIL_USUARIO")
public class PerfilUsuario implements Serializable, AuditModel {

    private static final long serialVersionUID = 1L;

    @Id(autoincrement =  false)
    @Property(nameInDb = "ID_PERFIL_USUARIO")
    private Long id;

    @Property(nameInDb = "DESCRICAO" )
    @NotNull
    private String descricao;

    @Property(nameInDb = "FLAG_TIPO_PERFIL")
    private String flagTipoPerfil;

    @NotNull()
    @Property(nameInDb = "DT_INCLUSAO")
    private Date dtInclusao;

    @NotNull()
    @Property(nameInDb = "DT_ALTERACAO")
    private Date dtAlteracao;

    @Property(nameInDb = "VERSAO_SISTEMA")
    @NotNull
    private Long versaoSistema;

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

    public String getFlagTipoPerfil() {
        return flagTipoPerfil;
    }

    public void setFlagTipoPerfil(String flagTipoPerfil) {
        this.flagTipoPerfil = flagTipoPerfil;
    }

    @Override
    public Date getDtInclusao() {
        return dtInclusao;
    }

    @Override
    public void setDtInclusao(Date dtInclusao) {
        this.dtInclusao = dtInclusao;
    }

    @Override
    public Date getDtAlteracao() {
        return dtAlteracao;
    }

    @Override
    public void setDtAlteracao(Date dtAlteracao) {
        this.dtAlteracao = dtAlteracao;
    }

    @Override
    public Long getVersaoSistema() {
        return this.versaoSistema;
    }

    @Override
    public void setVersaoSistema(Long versaoSistema) {
        this.versaoSistema = versaoSistema;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PerfilUsuario that = (PerfilUsuario) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
