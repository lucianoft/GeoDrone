package br.com.geodrone.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by fernandes on 26/03/2018.
 */

@Entity(generateConstructors = false, createInDb = true, nameInDb = "TB_TIPO_CULTIVO")
public class TipoCultivo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id(autoincrement =  false)
    @Property(nameInDb = "ID_TIPO_CULTIVO")
    private Long idTipoCultivoRef;

    @Property(nameInDb = "DESCRICAO" )
    @NotNull
    private String descricao;

    @Property(nameInDb = "IND_ATIVO")
    @NotNull
    private Integer indAtivo;

    public TipoCultivo() {
    }

    public Long getIdTipoCultivoRef() {
        return idTipoCultivoRef;
    }

    public void setIdTipoCultivoRef(Long idTipoCultivoRef) {
        this.idTipoCultivoRef = idTipoCultivoRef;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIndAtivo() {
        return indAtivo;
    }

    public void setIndAtivo(Integer indAtivo) {
        this.indAtivo = indAtivo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TipoCultivo that = (TipoCultivo) o;

        return idTipoCultivoRef != null ? idTipoCultivoRef.equals(that.idTipoCultivoRef) : that.idTipoCultivoRef == null;
    }

    @Override
    public int hashCode() {
        return idTipoCultivoRef != null ? idTipoCultivoRef.hashCode() : 0;
    }
}
