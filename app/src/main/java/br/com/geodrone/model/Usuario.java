package br.com.geodrone.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

@Entity(generateConstructors = false, createInDb = true, nameInDb = "TB_USUARIO")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id(autoincrement =  false)
    @Property(nameInDb = "ID_USUARIO_REF")
    private Long idUsuarioRef;

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

    public Usuario() {
    }

    public Long getIdUsuarioRef() {
        return idUsuarioRef;
    }

    public void setIdUsuarioRef(Long idUsuarioRef) {
        this.idUsuarioRef = idUsuarioRef;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        return idUsuarioRef != null ? idUsuarioRef.equals(usuario.idUsuarioRef) : usuario.idUsuarioRef == null;
    }

    @Override
    public int hashCode() {
        return idUsuarioRef != null ? idUsuarioRef.hashCode() : 0;
    }
}
