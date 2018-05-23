package br.com.geodrone.utils;

import java.util.List;

import br.com.geodrone.dto.LocationPointDto;
import br.com.geodrone.dto.LocationRotaDto;

/**
 * Created by fernandes on 20/05/2018.
 */

public class KmlUtils {

    public static String getLocationRotaDto(LocationRotaDto locationRotaDto){
        NumberUtils numberUtils = new NumberUtils();
        DateUtils dateUtils = new DateUtils();
        StringBuilder content = new StringBuilder();
        String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<kml xmlns=\"http://www.opengis.net/kml/2.2\">";
        content.append(kmlstart);
        content.append("<Document>");
        if (locationRotaDto.getLocationPointMonitoramentos() != null && !locationRotaDto.getLocationPointMonitoramentos().isEmpty()) {
            for (int i = 0; i < locationRotaDto.getLocationPointMonitoramentos().size(); i++) {
                if (i % 5 == 0 || i == locationRotaDto.getLocationPointMonitoramentos().size() - 1) {
                    LocationPointDto locationPoint = locationRotaDto.getLocationPointMonitoramentos().get(i);
                    content.append("<Placemark>");
                    content.append(System.getProperty("line.separator"));
                    content.append("<name>Ponto " + i + " </name>");
                    content.append(System.getProperty("line.separator"));
                    content.append("<description>" + dateUtils.format(locationPoint.getDtRegistro(), "dd/MM/yyyy hh:mm") + "</description>");
                    content.append(System.getProperty("line.separator"));
                    //content.append("<styleUrl>#transGreenPoly</styleUrl>");
                    content.append("<Point>");
                    content.append(System.getProperty("line.separator"));
                    content.append("<coordinates>");
                    content.append(System.getProperty("line.separator"));
                    content.append(numberUtils.toString(locationPoint.getLongitude()));
                    content.append(",");
                    content.append(numberUtils.toString(locationPoint.getLatitude()));
                    content.append(",0");
                    content.append(System.getProperty("line.separator"));
                    content.append("</coordinates>");
                    content.append(System.getProperty("line.separator"));
                    content.append("</Point>");
                    content.append(System.getProperty("line.separator"));
                    content.append("</Placemark>");
                    content.append(System.getProperty("line.separator"));
                }
            }
        }

        if (locationRotaDto.getLocationPointRegChuvas() != null && !locationRotaDto.getLocationPointRegChuvas().isEmpty()) {
            for (int i = 0; i < locationRotaDto.getLocationPointRegChuvas().size(); i++) {
                if (i % 5 == 0 || i == locationRotaDto.getLocationPointRegChuvas().size() - 1) {
                    LocationPointDto locationPoint = locationRotaDto.getLocationPointRegChuvas().get(i);

                    content.append("<Placemark>");
                    content.append(System.getProperty("line.separator"));
                    content.append("<name>Ponto " + i + " </name>");
                    content.append(System.getProperty("line.separator"));
                    content.append("<description>" + dateUtils.format(locationPoint.getDtRegistro(), "dd/MM/yyyy hh:mm") + "</description>");
                    content.append(System.getProperty("line.separator"));
                    //content.append("<styleUrl>#transGreenPoly</styleUrl>");
                    content.append("<Point>");
                    content.append(System.getProperty("line.separator"));
                    content.append("<coordinates>");
                    content.append(System.getProperty("line.separator"));
                    content.append(numberUtils.toString(locationPoint.getLongitude()));
                    content.append(",");
                    content.append(numberUtils.toString(locationPoint.getLatitude()));
                    content.append(",0");
                    content.append(System.getProperty("line.separator"));
                    content.append("</coordinates>");
                    content.append(System.getProperty("line.separator"));
                    content.append("</Point>");
                    content.append(System.getProperty("line.separator"));
                    content.append("</Placemark>");
                    content.append(System.getProperty("line.separator"));
                }
            }
        }

       /* if (locationRotaDto.getLocationPointMonitoramentos() != null && !locationRotaDto.getLocationPointMonitoramentos().isEmpty()) {
            content.append("<Placemark>");
            //content.append("<Style><LineStyle><color>#004080</color><width>8</width></LineStyle></Style>");
                   *//* "<styleUrl>#street</styleUrl>"+*//*
            content.append("<LineString>");
            //content.append("<extrude>1</extrude>");
            content.append("<tessellate>1</tessellate>");
            content.append("<altitudeMode>absolute</altitudeMode>");

            content.append("<coordinates>");
            for (LocationPointDto locationPoint : locationRotaDto.getLocationPointMonitoramentos()) {
                content.append(numberUtils.toString(locationPoint.getLongitude()));
                content.append(",");
                content.append(numberUtils.toString(locationPoint.getLatitude()));
                content.append(",0");
                content.append(System.getProperty("line.separator"));
            }
            content.append("</coordinates>");
            content.append("</LineString>");
            content.append("</Placemark>");
        }

        if (locationRotaDto.getLocationPointRegChuvas() != null && !locationRotaDto.getLocationPointRegChuvas().isEmpty()) {
            content.append("<Placemark>");
            //content.append("<Style><LineStyle><color>#009933</color><width>8</width></LineStyle></Style>");
                   *//* "<styleUrl>#street</styleUrl>"+*//*
            content.append("<LineString>");
            //content.append("<extrude>1</extrude>");
            content.append("<tessellate>0</tessellate>");
            content.append("<coordinates>");
            for (LocationPointDto locationPoint : locationRotaDto.getLocationPointRegChuvas()) {
                content.append(numberUtils.toString(locationPoint.getLongitude()));
                content.append(",");
                content.append(numberUtils.toString(locationPoint.getLatitude()));
                content.append(",0");
                content.append(System.getProperty("line.separator"));
            }
            content.append("</coordinates>");
            content.append("</LineString>");
            content.append("</Placemark>");
        }*/
        //content.append("</Folder>");
        content.append("</Document>");

        content.append("</kml>");
        return content.toString();
    }

}
