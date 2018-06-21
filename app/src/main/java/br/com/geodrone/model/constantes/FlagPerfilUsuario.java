package br.com.geodrone.model.constantes;

/**
 * Created by fernandes on 17/03/2018.
 */

public enum FlagPerfilUsuario {
    MASTER("GEO", "Geodrone"),
    ADMINISTRADOR("ADM", "Administrador"),
    CLIENTE("CLI", "Cliente"),
    COLETOR("COL", "Coletor");


    private final String value;
    private final String descricao;

    private FlagPerfilUsuario(String value, String descricao){
        this.value = value;
        this.descricao = descricao;
    }

    public String getValue(){
        return this.value;
    }


    public static String getDescricao(String value) {
        String instance = null;

        if (value != null) {
            FlagPerfilUsuario[] values = FlagPerfilUsuario.values();
            for (FlagPerfilUsuario val : values) {
                if (val.getValue().equals(value)) {
                    instance = val.descricao;
                    break;
                }
            }
        }

        return instance;
    }

    public static FlagPerfilUsuario getInstance(String descricao) {
        FlagPerfilUsuario instance = null;

        if (descricao != null) {
            FlagPerfilUsuario[] values = FlagPerfilUsuario.values();
            for (FlagPerfilUsuario value : values) {
                if (value.getValue().equals(descricao)) {
                    instance = value;
                    break;
                }
            }
        }

        return instance;
    }
}
