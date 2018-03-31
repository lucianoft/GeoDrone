package br.com.geodrone;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rodolfo on 12/07/2017.
 */

public class Session {
    private static Map<String,Object> attributes = new HashMap<>();

    public static synchronized void setAttribute(String key, Object value){
        attributes.put(key, value);
    }

    public static synchronized <T> T getAttribute(String key){
        return (T)attributes.get(key);
    }
}
