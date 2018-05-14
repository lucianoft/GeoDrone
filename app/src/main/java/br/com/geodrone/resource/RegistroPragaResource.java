package br.com.geodrone.resource;

import java.util.Date;

public class RegistroPragaResource {

    private Long idRegistroPraga;
    private Long idRegistroPragaDisp;
    private Long qtde;
    private String observacao;
    private Long idPraga;
    private Long idCliente;
    private Double latitude;
    private Double longitude;
    private Date dtRegistro;
    private Long idDispositivo;
    private Long idUsuarioReg;

    public Long getIdRegistroPraga() {
        return idRegistroPraga;
    }

    public void setIdRegistroPraga(Long idRegistroPraga) {
        this.idRegistroPraga = idRegistroPraga;
    }

    public Long getIdRegistroPragaDisp() {
        return idRegistroPragaDisp;
    }

    public void setIdRegistroPragaDisp(Long idRegistroPragaDisp) {
        this.idRegistroPragaDisp = idRegistroPragaDisp;
    }

    public Long getQtde() {
        return qtde;
    }

    public void setQtde(Long qtde) {
        this.qtde = qtde;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Long getIdPraga() {
        return idPraga;
    }

    public void setIdPraga(Long idPraga) {
        this.idPraga = idPraga;
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
