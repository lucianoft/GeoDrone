package br.com.geodrone.model.constantes;

/**
 * Created by fernandes on 20/03/2018.
 */

public enum FlagStatusCliente  {
    ATIVO("AT"),
    AGUARDANDO_APROVACAO("AG"),
    INATIVO("IN");

    private final String value;

    private FlagStatusCliente(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

}
