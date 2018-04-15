package br.com.geodrone;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rodolfo on 12/07/2017.
 */

public class SessionGeooDrone {

    public static final String CHAVE_USUARIO = "USUARIO";
    public static final String CHAVE_NOME_USUARIO  = "NOME_USUARIO";
    public static final String CHAVE_ID_CLIENTE    = "ID_CLIENTE";
    public static final String CHAVE_CLIENTE       = "CLIENTE";
    public static final String CHAVE_DISPOSITIVO   = "DISPOSITIVO";
    public static final String CHAVE_LOCALIZACAO_ATUAL      = "LOCALIZACAO_ATUAL";
    public static final String CHAVE_ACESS_TOKEN = "ACESS_TOKEN ";
    public static final String CHAVE_URL_BASE = "URL_BASE";

    private static Map<String,Object> attributes = new HashMap<>();

    public static synchronized void setAttribute(String key, Object value){
        attributes.put(key, value);
    }

    public static synchronized <T> T getAttribute(String key){
        return (T)attributes.get(key);
    }
}
