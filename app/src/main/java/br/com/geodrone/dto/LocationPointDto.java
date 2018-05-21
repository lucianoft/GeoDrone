package br.com.geodrone.dto;

import java.util.Date;

/**
 * Created by fernandes on 20/05/2018.
 */

public class LocationPointDto {

    private Double latitude;
    private Double longitude;
    private Date dtRegistro;

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
}
