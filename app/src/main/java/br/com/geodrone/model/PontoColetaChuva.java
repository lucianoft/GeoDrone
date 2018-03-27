package br.com.geodrone.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;
import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;


/**
 * Created by fernandes on 16/03/2018.
 */
@Entity(generateConstructors = false, nameInDb = "TB_PONTO_COLETA_CHUVA")
public class PontoColetaChuva implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id(autoincrement =  true)
    @Property(nameInDb = "ID_PONTO_COLETA_CHUVA")
    private Long idPontoColetaChuva;

    @NotNull
    @Property(nameInDb = "ID_CLIENTE_REF")
    private Long idClienteRef;

    @NotNull
    @Property(nameInDb = "DESCRICAO")
    private String descricao;

    @NotNull
    @Property(nameInDb = "LATITUDE")
    private Double latitude;

    @NotNull
    @Property(nameInDb = "LONGITUDE")
    private Double longitude;

    @NotNull
    @Property(nameInDb = "DT_INSTALACAO")
    private Date dtInstalacao;

    @NotNull()
    @Property(nameInDb = "DT_INCLUSAO")
    private Date dtInclusao;

    @NotNull()
    @Property(nameInDb = "DT_ALTERACAO")
    private Date dtAlteracao;

    @Property(nameInDb = "ID_USUARIO_REF")
    private Long idUsuarioRef;

    @Property(nameInDb = "IND_ATIVO")
    @NotNull
    private Integer indAtivo;

    @Property(nameInDb = "ID_PONTO_COLETA_CHUVA_REF")
    private Long idPontoColetaChuvaRef;

    public PontoColetaChuva(Long idClienteRef, String descricao, Double latitude, Double longitude, Date dtInstalacao, Date dtInclusao, Date dtAlteracao, Long idUsuarioRef, Integer indAtivo, Long idPontoColetaChuvaRef) {
        this.idClienteRef = idClienteRef;
        this.descricao = descricao;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dtInstalacao = dtInstalacao;
        this.dtInclusao = dtInclusao;
        this.dtAlteracao = dtAlteracao;
        this.idUsuarioRef = idUsuarioRef;
        this.indAtivo = indAtivo;
        this.idPontoColetaChuvaRef = idPontoColetaChuvaRef;
    }

    public PontoColetaChuva() {
    }

    public Long getIdPontoColetaChuva() {
        return idPontoColetaChuva;
    }

    public void setIdPontoColetaChuva(Long idPontoColetaChuva) {
        this.idPontoColetaChuva = idPontoColetaChuva;
    }

    public Long getIdClienteRef() {
        return idClienteRef;
    }

    public void setIdClienteRef(Long idClienteRef) {
        this.idClienteRef = idClienteRef;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Date getDtInstalacao() {
        return dtInstalacao;
    }

    public void setDtInstalacao(Date dtInstalacao) {
        this.dtInstalacao = dtInstalacao;
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

    public Long getIdUsuarioRef() {
        return idUsuarioRef;
    }

    public void setIdUsuarioRef(Long idUsuarioRef) {
        this.idUsuarioRef = idUsuarioRef;
    }

    public Integer getIndAtivo() {
        return indAtivo;
    }

    public void setIndAtivo(Integer indAtivo) {
        this.indAtivo = indAtivo;
    }

    public Long getIdPontoColetaChuvaRef() {
        return idPontoColetaChuvaRef;
    }

    public void setIdPontoColetaChuvaRef(Long idPontoColetaChuvaRef) {
        this.idPontoColetaChuvaRef = idPontoColetaChuvaRef;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PontoColetaChuva that = (PontoColetaChuva) o;

        return idPontoColetaChuva != null ? idPontoColetaChuva.equals(that.idPontoColetaChuva) : that.idPontoColetaChuva == null;
    }

    @Override
    public int hashCode() {
        return idPontoColetaChuva != null ? idPontoColetaChuva.hashCode() : 0;
    }
}
