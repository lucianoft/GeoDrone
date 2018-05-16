package br.com.geodrone.model.constantes;

/**
 * Created by fernandes on 15/05/2018.
 */

public enum FlagOperacaoRota {
    INICIO_MONITORAMENTO("INI_MOT", "Inicio Monitoramento"),
    MEIO_MONITORAMENTO("ROT_MOT", "Rota Monitoramento"),
    FIM_MONITORAMENTO("FIM_MOT", "Fim Monitoramento"),
    INICIO_REGISTRO_CHUVA("INI_CH", "Inicio Registro Chuva"),
    MEIO_REGISTRO_CHUVA("ROT_CH", "Rota Registro Chuva"),
    FIM_REGISTRO_CHUVA("FIM_CH", "Fim Registro Chuva");

    private final String value;
    private final String bundle;

    FlagOperacaoRota(String value, String bundle) {
        this.value = value;
        this.bundle = bundle;
    }

    public static String getValueByIndice(int ord) {
        try {
            return FlagOperacaoRota.values()[ord].value; // less safe
        } catch (Exception ex) {
        }
        return null;
    }

    public static String getBundleValueByIndice(int ord) {
        try {
            return FlagOperacaoRota.values()[ord].bundle; // less safe
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

