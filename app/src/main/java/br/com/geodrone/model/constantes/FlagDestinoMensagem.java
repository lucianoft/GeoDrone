package br.com.geodrone.model.constantes;

/**
 * Created by fernandes on 27/05/2018.
 */

public enum FlagDestinoMensagem {
    //Norte, Sul, Leste, Oeste
    ADMINISTRADOR("ADM", "Administrador"),
    USUARIO("USU", "Usuario");

    private final String value;
    private final String bundle;

    FlagDestinoMensagem(String value, String bundle) {
        this.value = value;
        this.bundle = bundle;
    }

    public String value() {
        return value;
    }

    public String getBundle(){
        return bundle;
    }

    @Override
    public String toString(){
        return getBundle();
    }

    public static String getValueByIndice(int ord) {
        try {
            return FlagDestinoMensagem.values()[ord].value; // less safe
        }catch (Exception ex){
        }
        return null;
    }

    public static String getBundleValueByIndice(int ord) {
        try {
            return FlagDestinoMensagem.values()[ord].bundle; // less safe
        }catch (Exception ex){
        }
        return null;
    }
}