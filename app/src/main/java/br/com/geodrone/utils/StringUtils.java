package br.com.geodrone.utils;

/**
 * Created by luciano on 31/03/2018.
 */

public class StringUtils {

    public static boolean  isEmpty(String s){
        return s == null || s.trim().equals("");
    }
}
