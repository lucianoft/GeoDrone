package br.com.geodrone.utils;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import br.com.geodrone.dto.ColetaPluviosidadeDto;

/**
 * Created by luciano on 24/03/2018.
 */

public class LocationUtils {

    public static double calculateDistance(Location location, Location location2){
        return location.distanceTo(location2);
    }

    public static Location createNewLocation(double latitude, double longitude) {
        Location location = new Location("dummyprovider");
        location.setLongitude(longitude);
        location.setLatitude(latitude);
        return location;
    }

    public static ColetaPluviosidadeDto localLessDistance(Location location, List<ColetaPluviosidadeDto> coletaPluviosidadeDtos){

        ColetaPluviosidadeDto c = null;
        if (coletaPluviosidadeDtos != null && !coletaPluviosidadeDtos.isEmpty()){
            c  = coletaPluviosidadeDtos.get(0);
        }
        for (ColetaPluviosidadeDto coletaPluviosidadeDto: coletaPluviosidadeDtos) {
            Location location1 = createNewLocation(c.getLatitude(), c.getLongitude());
            Location location2 = createNewLocation(coletaPluviosidadeDto.getLatitude(), coletaPluviosidadeDto.getLongitude());
            double distance1 = calculateDistance(location, location1);
            double distance2 = calculateDistance(location, location2);
            if (distance2 < distance1){
                c = coletaPluviosidadeDto;
            }
        }
        return c;
    }
}
