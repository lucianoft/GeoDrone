package br.com.geodrone.resource;

import java.util.Date;

public class RegistroChuvaResource {

    private Long idRegistroChuva;
    private Long idRegistroChuvaDisp;
    private Integer volume;
    private Long idPontoColetaChuva;
    private Long idPontoColetaChuvaDisp;
    private Long idCliente;
    private Double latitude;
    private Double longitude;
    private Date dtRegistro;
    private Long idDispositivo;
    private Date dtInclusao;
    private Date dtAlteracao;
    private Long idUsuarioReg;

    public Long getIdRegistroChuva() {
        return idRegistroChuva;
    }

    public void setIdRegistroChuva(Long idRegistroChuva) {
        this.idRegistroChuva = idRegistroChuva;
    }

    public Long getIdRegistroChuvaDisp() {
        return idRegistroChuvaDisp;
    }

    public void setIdRegistroChuvaDisp(Long idRegistroChuvaDisp) {
        this.idRegistroChuvaDisp = idRegistroChuvaDisp;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Long getIdPontoColetaChuva() {
        return idPontoColetaChuva;
    }

    public void setIdPontoColetaChuva(Long idPontoColetaChuva) {
        this.idPontoColetaChuva = idPontoColetaChuva;
    }

    public Long getIdPontoColetaChuvaDisp() {
        return idPontoColetaChuvaDisp;
    }

    public void setIdPontoColetaChuvaDisp(Long idPontoColetaChuvaDisp) {
        this.idPontoColetaChuvaDisp = idPontoColetaChuvaDisp;
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

    public Date getDtRegistro() {
        return dtRegistro;
    }

    public void setDtRegistro(Date dtRegistro) {
        this.dtRegistro = dtRegistro;
    }

    public Long getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(Long idDispositivo) {
        this.idDispositivo = idDispositivo;
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

    public Long getIdUsuarioReg() {
        return idUsuarioReg;
    }

    public void setIdUsuarioReg(Long idUsuarioReg) {
        this.idUsuarioReg = idUsuarioReg;
    }
}
