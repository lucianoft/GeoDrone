package br.com.geodrone.resource;

import java.util.Date;

/**
 * Created by fernandes on 13/05/2018.
 */

public class RegistroImagemResource {

    private Long idRegistroImagemDisp;
    private Long idRegistroImagem;
    private String observacao;
    private byte[] imagem;
    private Date dtRegistro;
    private Long idCliente;
    private Double latitude;
    private Double longitude;
    private Long idDispositivo;
    private Date dtInclusao;
    private Date dtAlteracao;
    private Long idUsuarioReg;

    public Long getIdRegistroImagemDisp() {
        return idRegistroImagemDisp;
    }

    public void setIdRegistroImagemDisp(Long idRegistroImagemDisp) {
        this.idRegistroImagemDisp = idRegistroImagemDisp;
    }

    public Long getIdRegistroImagem() {
        return idRegistroImagem;
    }

    public void setIdRegistroImagem(Long idRegistroImagem) {
        this.idRegistroImagem = idRegistroImagem;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public Date getDtRegistro() {
        return dtRegistro;
    }

    public void setDtRegistro(Date dtRegistro) {
        this.dtRegistro = dtRegistro;
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
