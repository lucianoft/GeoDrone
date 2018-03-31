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
@Entity(generateConstructors = false, createInDb = true, nameInDb = "TB_DOENCA")
public class Doenca implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id(autoincrement =  false)
    @Property(nameInDb = "ID_DOENCA_REF")
    private Long idDoencaRef;

    @Property(nameInDb = "ID_TIPO_CULTIVO_REF")
    private Long idTipoCultivoRef;

    @Property(nameInDb = "DESCRICAO" )
    @NotNull
    private String descricao;


    @Property(nameInDb = "DESCRICAO_CIENTIFICA" )
    @NotNull
    private String descricaoCientifica;

    @Property(nameInDb = "IND_ATIVO")
    @NotNull
    private Integer indAtivo;

    public Doenca() {
    }

    public Doenca(Long idDoencaRef, Long idTipoCultivoRef, String descricao, String descricaoCientifica, Integer indAtivo) {
        this.idDoencaRef = idDoencaRef;
        this.idTipoCultivoRef = idTipoCultivoRef;
        this.descricao = descricao;
        this.descricaoCientifica = descricaoCientifica;
        this.indAtivo = indAtivo;
    }

    public Long getIdDoencaRef() {
        return idDoencaRef;
    }

    public void setIdDoencaRef(Long idDoencaRef) {
        this.idDoencaRef = idDoencaRef;
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

    public String getDescricaoCientifica() {
        return descricaoCientifica;
    }

    public void setDescricaoCientifica(String descricaoCientifica) {
        this.descricaoCientifica = descricaoCientifica;
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

        Doenca doenca = (Doenca) o;

        return idDoencaRef != null ? idDoencaRef.equals(doenca.idDoencaRef) : doenca.idDoencaRef == null;
    }

    @Override
    public int hashCode() {
        return idDoencaRef != null ? idDoencaRef.hashCode() : 0;
    }
}
