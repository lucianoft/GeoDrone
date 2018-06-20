package br.com.geodrone.model.constantes;

/**
 * Created by fernandes on 05/05/2018.
 */

public enum FlagSimNao {
    //Norte, Sul, Leste, Oeste
    SIM(1, "Sim"),
    NAO(0, "Nao");

    private final Integer value;
    private final String bundle;

    FlagSimNao(Integer value, String bundle) {
        this.value = value;
        this.bundle = bundle;
    }

    public Integer getValue() {
        return value;
    }

    public String getBundle(){
        return bundle;
    }

    @Override
    public String toString(){
        return getBundle();
    }

    public static Integer getValueByIndice(int ord) {
        try {
            return FlagSimNao.values()[ord].value; // less safe
        }catch (Exception ex){
        }
        return null;
    }

    public static String getBundleValueByIndice(int ord) {
        try {
            return FlagSimNao.values()[ord].bundle; // less safe
        }catch (Exception ex){
        }
        return null;
    }

    public static String getDescricao(Integer value) {
        String instance = null;

        if (value != null) {
            FlagSimNao[] values = FlagSimNao.values();
            for (FlagSimNao val : values) {
                if (val.getValue().equals(value)) {
                    instance = val.bundle;
                    break;
                }
            }
        }

        return instance;
    }
}