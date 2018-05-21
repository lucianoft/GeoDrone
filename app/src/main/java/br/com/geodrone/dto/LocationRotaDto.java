package br.com.geodrone.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fernandes on 20/05/2018.
 */

public class LocationRotaDto {

    private List<LocationPointDto> locationPointMonitoramentos;
    private List<LocationPointDto> locationPointRegChuvas;

    public List<LocationPointDto> getLocationPointMonitoramentos() {
        return locationPointMonitoramentos;
    }

    public void setLocationPointMonitoramentos(List<LocationPointDto> locationPointMonitoramentos) {
        this.locationPointMonitoramentos = locationPointMonitoramentos;
    }

    public void addLocationPointMonitoramento(LocationPointDto locationPointMonitoramento) {
        if (this.locationPointMonitoramentos == null){
            this.locationPointMonitoramentos = new ArrayList<>();
        }
        this.locationPointMonitoramentos.add(locationPointMonitoramento);
    }

    public List<LocationPointDto> getLocationPointRegChuvas() {
        return locationPointRegChuvas;
    }

    public void setLocationPointRegChuvas(List<LocationPointDto> locationPointRegChuvas) {
        this.locationPointRegChuvas = locationPointRegChuvas;
    }

    public void addLocationPointRegChuva(LocationPointDto locationPointRegChuva) {
        if (this.locationPointRegChuvas == null){
            this.locationPointRegChuvas = new ArrayList<>();
        }
        this.locationPointRegChuvas.add(locationPointRegChuva);
    }
}
