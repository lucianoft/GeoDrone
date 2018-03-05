package br.com.geodrone.dto;

import java.util.Date;

/**
 * Created by fernandes on 03/03/2018.
 */

public class PluviosidadeDiariaDto {
    private Long id;
    private String descricao;

    private Double latitude;
    private Double longitude;

    private Date dtLeitura;
    private Double latitudeLeitura;
    private Double longitudeLeitura;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getDtLeitura() {
        return dtLeitura;
    }

    public void setDtLeitura(Date dtLeitura) {
        this.dtLeitura = dtLeitura;
    }

    public Double getLatitudeLeitura() {
        return latitudeLeitura;
    }

    public void setLatitudeLeitura(Double latitudeLeitura) {
        this.latitudeLeitura = latitudeLeitura;
    }

    public Double getLongitudeLeitura() {
        return longitudeLeitura;
    }

    public void setLongitudeLeitura(Double longitudeLeitura) {
        this.longitudeLeitura = longitudeLeitura;
    }
}
