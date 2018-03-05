package br.com.geodrone.dto;

import java.io.Serializable;

public class PragaDto implements Serializable {

    private Long id;
    private String descricao;

    public PragaDto(){
        this(null, null);
    }

    public PragaDto(Long id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
