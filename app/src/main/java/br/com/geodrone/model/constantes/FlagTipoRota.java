package br.com.geodrone.model.constantes;

/**
 * Created by fernandes on 15/05/2018.
 */

public enum FlagTipoRota {
    MONITORAMENTO("MO", "Monitoramento"),
    REGISTRO_CHUVA("CH", "Registro Chuva");

    private final String value;
    private final String bundle;

    FlagTipoRota(String value, String bundle) {
        this.value = value;
        this.bundle = bundle;
    }

    public static String getValueByIndice(int ord) {
        try {
            return FlagTipoRota.values()[ord].value; // less safe
        } catch (Exception ex) {
        }
        return null;
    }

    public static String getBundleValueByIndice(int ord) {
        try {
            return FlagTipoRota.values()[ord].bundle; // less safe
        } catch (Exception ex) {
        }
        return null;
    }

    public String value() {
        return value;
    }

    public String getBundle() {
        return bundle;
    }

    @Override
    public String toString() {
        return getBundle();
    }
}

