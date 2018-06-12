package br.com.geodrone.model.constantes;

public enum FlagStatusCliente {
    ATIVO("A", "Ativo"),
    NOVO("N", "Novo"),
    CANCELADO("C", "Cancelado");

    private final String value;
    private final String descricao;

    private FlagStatusCliente(String value, String descricao){
        this.value = value;
        this.descricao = descricao;
    }

    public String getValue(){
        return this.value;
    }

    public static String getDescricao(String value) {
        String instance = null;

        if (value != null) {
            FlagStatusCliente[] values = FlagStatusCliente.values();
            for (FlagStatusCliente val : values) {
                if (val.getValue().equals(value)) {
                    instance = val.descricao;
                    break;
                }
            }
        }

        return instance;
    }

    public static FlagStatusCliente getInstance(String descricao) {
        FlagStatusCliente instance = null;

        if (descricao != null) {
            FlagStatusCliente[] values = FlagStatusCliente.values();
            for (FlagStatusCliente value : values) {
                if (value.getValue().equals(descricao)) {
                    instance = value;
                    break;
                }
            }
        }

        return instance;
    }


}