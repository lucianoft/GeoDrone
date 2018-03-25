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

    @Id(autoincrement =  false)
    @Property(nameInDb = "ID_ROTA_TRABALHO")
    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
