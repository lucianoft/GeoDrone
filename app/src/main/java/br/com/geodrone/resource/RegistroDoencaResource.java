package br.com.geodrone.resource;

import java.util.Date;

public class RegistroDoencaResource {

    private Long idRegistroDoenca;
    private Long idRegistroDoencaDisp;
    private Long qtde;
    private String observacao;
    private Long idDoenca;
    private Long idCliente;
    private Long idTalhao;
    private Double latitude;
    private Double longitude;
    private Date dtRegistro;
    private Long idDispositivo;
    private Long idUsuarioReg;

    public Long getIdRegistroDoenca() {
        return idRegistroDoenca;
    }

    public void setIdRegistroDoenca(Long idRegistroDoenca) {
        this.idRegistroDoenca = idRegistroDoenca;
    }

    public Long getIdRegistroDoencaDisp() {
        return idRegistroDoencaDisp;
    }

    public void setIdRegistroDoencaDisp(Long idRegistroDoencaDisp) {
        this.idRegistroDoencaDisp = idRegistroDoencaDisp;
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

    public Long getIdDoenca() {
        return idDoenca;
    }

    public void setIdDoenca(Long idDoenca) {
        this.idDoenca = idDoenca;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdTalhao() {
        return idTalhao;
    }

    public void setIdTalhao(Long idTalhao) {
        this.idTalhao = idTalhao;
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
