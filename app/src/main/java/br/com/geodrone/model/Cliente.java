package br.com.geodrone.model;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

import br.com.geodrone.model.constantes.FlagStatusCliente;
import br.com.geodrone.model.converter.FlagPerfilUsuarioConverter;
import br.com.geodrone.model.converter.FlagStatusClienteConverter;

/**
 * Created by fernandes on 13/03/2018.
 */
@Entity(generateConstructors = false, nameInDb = "TB_CLIENTE")
public class Cliente {

    @Id(autoincrement =  false)
    @Property(nameInDb = "ID_CLIENTE")
    private Long id;

    @Property(nameInDb = "IND_PESSOA_FISICA" )
    @NotNull
    private Integer indPessoaFisica;

    @Property(nameInDb = "NOME_RAZAO_SOCIAL" )
    @NotNull
    private String nomeRazaoSocial;

    @Property(nameInDb = "CPF" )
    private Long cfp;

    @Property(nameInDb = "CNPJ" )
    private Long cnpj;

    @Property(nameInDb = "SOBRENOME")
    private String nomeFantasia;

    @Property(nameInDb = "SEGMENTO")
    private String segmento;

    @Property(nameInDb = "EMAIL")
    @NotNull
    private String email;

    @Property(nameInDb = "TELEFONE")
    private String telefone;

    @Property(nameInDb = "CELULAR")
    private String celular;

    @Property(nameInDb = "FLAG_STATUS")
    @Convert(converter = FlagStatusClienteConverter.class, columnType = String.class)
    @NotNull
    private FlagStatusCliente flagStatus;

    public Cliente() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIndPessoaFisica() {
        return indPessoaFisica;
    }

    public void setIndPessoaFisica(Integer indPessoaFisica) {
        this.indPessoaFisica = indPessoaFisica;
    }

    public String getNomeRazaoSocial() {
        return nomeRazaoSocial;
    }

    public void setNomeRazaoSocial(String nomeRazaoSocial) {
        this.nomeRazaoSocial = nomeRazaoSocial;
    }

    public Long getCfp() {
        return cfp;
    }

    public void setCfp(Long cfp) {
        this.cfp = cfp;
    }

    public Long getCnpj() {
        return cnpj;
    }

    public void setCnpj(Long cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getSegmento() {
        return segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
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

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public FlagStatusCliente getFlagStatus() {
        return flagStatus;
    }

    public void setFlagStatus(FlagStatusCliente flagStatus) {
        this.flagStatus = flagStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cliente cliente = (Cliente) o;

        return id != null ? id.equals(cliente.id) : cliente.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
