package br.com.geodrone.resource;

import java.util.Date;

public class RegistroCondicaoTempoResource {

    private Long idRegistroCondicaoTempo;
    private Long idRegistroCondicaoTempoDisp;
    private String flagDirecao;
    private String flagCondicaoTempo;
    private String observacao;
    private Long idCliente;
    private Double latitude;
    private Double longitude;
    private Date dtRegistro;
    private Long idDispositivo;
    private Long idUsuarioReg;

    public Long getIdRegistroCondicaoTempo() {
        return idRegistroCondicaoTempo;
    }

    public void setIdRegistroCondicaoTempo(Long idRegistroCondicaoTempo) {
        this.idRegistroCondicaoTempo = idRegistroCondicaoTempo;
    }

    public Long getIdRegistroCondicaoTempoDisp() {
        return idRegistroCondicaoTempoDisp;
    }

    public void setIdRegistroCondicaoTempoDisp(Long idRegistroCondicaoTempoDisp) {
        this.idRegistroCondicaoTempoDisp = idRegistroCondicaoTempoDisp;
    }

    public String getFlagDirecao() {
        return flagDirecao;
    }

    public void setFlagDirecao(String flagDirecao) {
        this.flagDirecao = flagDirecao;
    }

    public String getFlagCondicaoTempo() {
        return flagCondicaoTempo;
    }

    public void setFlagCondicaoTempo(String flagCondicaoTempo) {
        this.flagCondicaoTempo = flagCondicaoTempo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
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

    public Long getIdUsuarioReg() {
        return idUsuarioReg;
    }

    public void setIdUsuarioReg(Long idUsuarioReg) {
        this.idUsuarioReg = idUsuarioReg;
    }
}
