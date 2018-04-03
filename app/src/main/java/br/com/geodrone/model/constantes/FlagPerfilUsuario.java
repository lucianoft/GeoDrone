package br.com.geodrone.model.constantes;

/**
 * Created by fernandes on 17/03/2018.
 */

public enum FlagPerfilUsuario {
    ADM("ADM");

    private final String value;

    private FlagPerfilUsuario(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

}
