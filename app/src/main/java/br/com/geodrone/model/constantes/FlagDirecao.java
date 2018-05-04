package br.com.geodrone.model.constantes;

/**
 * Created by fernandes on 02/04/2018.
 */

public enum FlagDirecao {
    //Norte, Sul, Leste, Oeste
    NORTE("N", "Norte"),
    NORDESTE("NE", "Nordeste"),
    LESTE("L", "Leste"),
    SUDESTE("SE", "Sudeste"),
    SUDOESTE("SO", "Sudoeste"),
    OESTE("O", "Oeste"),
    NOROESTE("NO", "Noroeste");

    private final String value;
    private final String bundle;

    FlagDirecao(String value, String bundle) {
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
            return FlagDirecao.values()[ord].value; // less safe
        }catch (Exception ex){
        }
        return null;
    }

    public static String getBundleValueByIndice(int ord) {
        try {
            return FlagDirecao.values()[ord].bundle; // less safe
        }catch (Exception ex){
        }
        return null;
    }
}
