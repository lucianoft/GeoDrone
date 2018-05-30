package br.com.geodrone.resource;

import java.util.Date;

public class UsuarioMensagemResource {
    private Long id;
    private String nome;
    private String ultimaMensagem;
    private Date dtUltimaMensagem;

    public UsuarioMensagemResource() {

    }

    public UsuarioMensagemResource(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUltimaMensagem() {
        return ultimaMensagem;
    }

    public void setUltimaMensagem(String ultimaMensagem) {
        this.ultimaMensagem = ultimaMensagem;
    }

    public Date getDtUltimaMensagem() {
        return dtUltimaMensagem;
    }

    public void setDtUltimaMensagem(Date dtUltimaMensagem) {
        this.dtUltimaMensagem = dtUltimaMensagem;
    }
}
