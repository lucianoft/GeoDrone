package br.com.geodrone.model.constantes;

public enum FlagEstadoTempo {

    //céu de brigadeiro, ensolarado, parcialmente nublado, nublado, garoa, chuva, invernado, tempestade

    CEU_BRIGADEIRO("BRI", "Céu de brigadeiro"),
    ENSOLARADO("ENS", "Ensolarado"),
    PARCIALMENTE_NUBLADO("PARC", "Parcialmente nublado"),
    NUBLADO("NUB", "Nublado"),
    GAROA("GAR", "Garoa"),
    CHUVA("CHU", "Chuva"),
    INVERNADO("INV", "Invernado"),
    TEMPESTADE("TEM", "Tempestade");

    private final String value;
    private final String bundle;

    private FlagEstadoTempo(String value, String bundle) {
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
            return FlagEstadoTempo.values()[ord].value; // less safe
        }catch (Exception ex){
        }
        return null;
    }

    public static String getBundleValueByIndice(int ord) {
        try {
            return FlagEstadoTempo.values()[ord].bundle; // less safe
        }catch (Exception ex){
        }
        return null;
    }
}
