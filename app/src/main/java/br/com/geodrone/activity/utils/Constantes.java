package br.com.geodrone.activity.utils;

/**
 * Created by fernandes on 09/03/2018.
 */

public interface Constantes {

    // The minimum distance to change Updates in meters
    public static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10; // 10 meters
    // The minimum time between updates in milliseconds
    public static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1; // 1 minute
    public static final int ZOOM_MAP = 17;


    public static final int ACTIVITY_CODE_PICK_IMAGE = 1234;
    public static final int ACTIVITY_REQUEST_TAKE_PHOTO = 1235;
    public static final int ACTIVITY_CODE_TIRAR_FOTO = 1235;


    public static final String PARAM_CAMINHO_ARQUIVO = "pathImagem";

    public static final String CHAVE_UI_ORIGEM = "CHAVE_UI_ORIGEM";
    public static final String ACTIVITY_PRIMEIRO_LOGIN = "ACTIVITY_PRIMEIRO_LOGIN";
    public static final String ACTIVITY_LOGIN = "ACTIVITY_LOGIN";
    public static final String ACTIVITY_MAIN = "ACTIVITY_MAIN";

}
