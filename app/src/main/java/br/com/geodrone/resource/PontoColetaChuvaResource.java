package br.com.geodrone.resource;

import java.io.Serializable;
import java.util.Date;

public class PontoColetaChuvaResource implements Serializable {

    private Long idPontoColetaChuvaDispositivo;
    private Long idPontoColetaChuva;
    private Long idDispositivo;
    private String descricao;
    private Date dtInstalacao;
    private Long idCliente;
    private Double latitude;
    private Double longitude;
    private Integer indAtivo;
    private Date dtInclusao;
    private Date dtAlteracao;

    public Long getIdPontoColetaChuvaDispositivo() {
        return idPontoColetaChuvaDispositivo;
    }

    public void setIdPontoColetaChuvaDispositivo(Long idPontoColetaChuvaDispositivo) {
        this.idPontoColetaChuvaDispositivo = idPontoColetaChuvaDispositivo;
    }

    public Long getIdPontoColetaChuva() {
        return idPontoColetaChuva;
    }

    public void setIdPontoColetaChuva(Long idPontoColetaChuva) {
        this.idPontoColetaChuva = idPontoColetaChuva;
    }

    public Long getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(Long idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDtInstalacao() {
        return dtInstalacao;
    }

    public void setDtInstalacao(Date dtInstalacao) {
        this.dtInstalacao = dtInstalacao;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
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

    public Integer getIndAtivo() {
        return indAtivo;
    }

    public void setIndAtivo(Integer indAtivo) {
        this.indAtivo = indAtivo;
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
}
