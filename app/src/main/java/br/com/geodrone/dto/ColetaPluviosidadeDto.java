package br.com.geodrone.dto;

import java.util.Date;

/**
 * Created by fernandes on 03/03/2018.
 */

public class ColetaPluviosidadeDto {
    private Long idPontoColetaChuva;
    private String descricao;

    private Double latitude;
    private Double longitude;

    private Date dtLeitura;
    private Double latLeitura;
    private Double lonLeitura;

    private String ultimaLeitura;

    public Long getIdPontoColetaChuva() {
        return idPontoColetaChuva;
    }

    public void setIdPontoColetaChuva(Long idPontoColetaChuva) {
        this.idPontoColetaChuva = idPontoColetaChuva;
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

    public Double getLatLeitura() {
        return latLeitura;
    }

    public void setLatLeitura(Double latLeitura) {
        this.latLeitura = latLeitura;
    }

    public Double getLonLeitura() {
        return lonLeitura;
    }

    public void setLonLeitura(Double lonLeitura) {
        this.lonLeitura = lonLeitura;
    }

    public String getUltimaLeitura() {
        return ultimaLeitura;
    }

    public void setUltimaLeitura(String ultimaLeitura) {
        this.ultimaLeitura = ultimaLeitura;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ColetaPluviosidadeDto that = (ColetaPluviosidadeDto) o;

        return idPontoColetaChuva != null ? idPontoColetaChuva.equals(that.idPontoColetaChuva) : that.idPontoColetaChuva == null;
    }

    @Override
    public int hashCode() {
        return idPontoColetaChuva != null ? idPontoColetaChuva.hashCode() : 0;
    }
}
