package br.com.geodrone.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;
import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by fernandes on 25/03/2018.
 */

@Entity(generateConstructors = false, createInDb = true, nameInDb = "TB_CONFIGURACAO")
public class Configuracao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id(autoincrement =  true)
    @Property(nameInDb = "ID_CONFIGURACAO")
    private Long idConfiguracao;

    @NotNull
    @Property(nameInDb = "ID_DISPOSITIVO")
    private Long idDispositivo;

    @NotNull
    @Property(nameInDb = "URL")
    private String url;

    @Property(nameInDb = "DT_SINCRONIZACAO")
    private Date dtSincronizacao;

    public Configuracao() {
    }

    public Long getIdConfiguracao() {
        return idConfiguracao;
    }

    public void setIdConfiguracao(Long idConfiguracao) {
        this.idConfiguracao = idConfiguracao;
    }

    public Long getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(Long idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDtSincronizacao() {
        return dtSincronizacao;
    }

    public void setDtSincronizacao(Date dtSincronizacao) {
        this.dtSincronizacao = dtSincronizacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Configuracao that = (Configuracao) o;

        return idConfiguracao != null ? idConfiguracao.equals(that.idConfiguracao) : that.idConfiguracao == null;
    }

    @Override
    public int hashCode() {
        return idConfiguracao != null ? idConfiguracao.hashCode() : 0;
    }
}
