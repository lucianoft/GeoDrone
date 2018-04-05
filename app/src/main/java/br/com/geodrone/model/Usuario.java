package br.com.geodrone.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

import br.com.geodrone.model.api.ClientModel;

@Entity(generateConstructors = false, createInDb = true, nameInDb = "TB_USUARIO")
public class Usuario implements Serializable, ClientModel {

    private static final long serialVersionUID = 1L;

    @Id(autoincrement =  false)
    @Property(nameInDb = "ID_USUARIO")
    private Long id;

    @Property(nameInDb = "NOME" )
    @NotNull
    private String nome;

    @Property(nameInDb = "SOBRENOME")
    private String sobrenome;

    @Property(nameInDb = "EMAIL")
    @NotNull
    private String email;

    @Property(nameInDb = "TELEFONE" )
    @NotNull
    private String telefone;

    @Property(nameInDb = "SENHA")
    @NotNull
    private String senha;

    @NotNull
    @Property(nameInDb = "ID_CLIENTE")
    private Long idCliente;

    public Usuario() {
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

    @Override
    public Long getIdCliente() {
        return idCliente;
    }

    @Override
    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        return id != null ? id.equals(usuario.id) : usuario.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
