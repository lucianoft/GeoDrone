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
 * Created by luciano on 31/03/2018.
 */

@Entity(generateConstructors = false, createInDb = true, nameInDb = "TB_DISPOSITIVO")
public class Dispositivo implements Serializable , AuditModel {

    private static final long serialVersionUID = 1L;

    @Id(autoincrement =  true)
    @Property(nameInDb = "ID_DISPOSITIVO")
    private Long idRef;

    @Property(nameInDb = "DT_INCLUSAO")
    @NotNull
    private Date dtInclusao;

    @Property(nameInDb = "DT_ALTERACAO")
    @NotNull
    private Date dtAlteracao;

    @Property(nameInDb = "VERSAO_SISTEMA")
    @NotNull
    private Long versaoSistema;

    public Dispositivo() {
    }

    public Long getIdRef() {
        return idRef;
    }

    public void setIdRef(Long idRef) {
        this.idRef = idRef;
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
        return versaoSistema;
    }

    @Override
    public void setVersaoSistema(Long versaoSistema) {
        this.versaoSistema = versaoSistema;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dispositivo that = (Dispositivo) o;

        return idRef != null ? idRef.equals(that.idRef) : that.idRef == null;
    }

    @Override
    public int hashCode() {
        return idRef != null ? idRef.hashCode() : 0;
    }
}
