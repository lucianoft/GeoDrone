package br.com.geodrone.resource;

import java.io.Serializable;

public class UsuarioResource implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private String telefone;
    private String senha;
    private String flagPerfil;
    private Long idCliente;
    private Integer indAtivo;
    private Integer indAceite;

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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFlagPerfil() {
        return flagPerfil;
    }

    public void setFlagPerfil(String flagPerfil) {
        this.flagPerfil = flagPerfil;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIndAtivo() {
        return indAtivo;
    }

    public void setIndAtivo(Integer indAtivo) {
        this.indAtivo = indAtivo;
    }

    public Integer getIndAceite() {
        return indAceite;
    }

    public void setIndAceite(Integer indAceite) {
        this.indAceite = indAceite;
    }
}
