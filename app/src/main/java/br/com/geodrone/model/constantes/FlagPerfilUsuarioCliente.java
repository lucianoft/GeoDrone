package br.com.geodrone.model.constantes;

/**
 * Created by fernandes on 19/06/2018.
 */

public enum FlagPerfilUsuarioCliente {
    CLIENTE("CLI", "Cliente"),
    COLETOR("COL", "Coletor");


    private final String value;
    private final String descricao;

    private FlagPerfilUsuarioCliente(String value, String descricao){
        this.value = value;
        this.descricao = descricao;
    }

    public String getValue(){
        return this.value;
    }


    public static String getDescricao(String value) {
        String instance = null;

        if (value != null) {
            FlagPerfilUsuarioCliente[] values = FlagPerfilUsuarioCliente.values();
            for (FlagPerfilUsuarioCliente val : values) {
                if (val.getValue().equals(value)) {
                    instance = val.descricao;
                    break;
                }
            }
        }

        return instance;
    }

}

