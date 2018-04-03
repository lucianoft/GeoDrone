package br.com.geodrone.model.constantes;

/**
 * Created by fernandes on 02/04/2018.
 */

public enum FlagDirecao {
    //Norte, Sul, Leste, Oeste
    NORTE("N", "Norte"),
    SUL  ("S", "Sul"),
    LESTE("L", "Leste"),
    OESTE("O", "Oeste");

    private final String value;
    private final String bundle;

    private FlagDirecao(String value, String bundle) {
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
            return FlagDirecao.values()[ord].bundle; // less safe
        }catch (Exception ex){
        }
        return null;
    }
}
