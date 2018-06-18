package br.com.geodrone.resource;

import java.util.Date;

/**
 * Created by fernandes on 17/06/2018.
 */
public class RegistroDefensivoResource {

    private Long idRegistroDefensivo;
    private Long idRegistroDefensivoDisp;
    private Double dose;
    private Long idDefensivoQuimico;
    private Long idCliente;
    private Long idTalhao;
    private Double latitude;
    private Double longitude;
    private Date dtRegistro;
    private Long idDispositivo;
    private Long idUsuarioReg;

    public Long getIdRegistroDefensivo() {
        return idRegistroDefensivo;
    }

    public void setIdRegistroDefensivo(Long idRegistroDefensivo) {
        this.idRegistroDefensivo = idRegistroDefensivo;
    }

    public Long getIdRegistroDefensivoDisp() {
        return idRegistroDefensivoDisp;
    }

    public void setIdRegistroDefensivoDisp(Long idRegistroDefensivoDisp) {
        this.idRegistroDefensivoDisp = idRegistroDefensivoDisp;
    }

    public Double getDose() {
        return dose;
    }

    public void setDose(Double dose) {
        this.dose = dose;
    }

    public Long getIdDefensivoQuimico() {
        return idDefensivoQuimico;
    }

    public void setIdDefensivoQuimico(Long idDefensivoQuimico) {
        this.idDefensivoQuimico = idDefensivoQuimico;
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
