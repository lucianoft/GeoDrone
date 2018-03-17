package br.com.geodrone.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by fernandes on 16/03/2018.
 */
@Entity(generateConstructors = false, nameInDb = "TB_ESTACAO")
public class Estacao {

    @Id(autoincrement =  true)
    @Property(nameInDb = "ID_ESTACAO")
    private Long id;

    @NotNull
    @Property(nameInDb = "ID_CLIENTE")
    private Long idCliente;

    @NotNull
    @Property(nameInDb = "LATITUDE")
    private Long latitude;

    @NotNull
    @Property(nameInDb = "LONGITUDE")
    private Long longitude;

    @NotNull
    @Property(nameInDb = "DT_INSTALACAO")
    private Date dtInstalacao;

    @NotNull()
    @Property(nameInDb = "DT_INCLUSAO")
    private Date dtInclusao;

    @NotNull()
    @Property(nameInDb = "DT_ALTERACAO")
    private Date dtAlteracao;

    @Property(nameInDb = "ID_ESTACAO_WEB")
    private Long idEstacaoWeb;

    public Estacao() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }

    public Date getDtInstalacao() {
        return dtInstalacao;
    }

    public void setDtInstalacao(Date dtInstalacao) {
        this.dtInstalacao = dtInstalacao;
    }

    public Date getDtInclusao() {
        return dtInclusao;
    }

    public void setDtInclusao(Date dtInclusao) {
        this.dtInclusao = dtInclusao;
    }

    public Date getDtAlteracao() {
        return dtAlteracao;
    }

    public void setDtAlteracao(Date dtAlteracao) {
        this.dtAlteracao = dtAlteracao;
    }

    public Long getIdEstacaoWeb() {
        return idEstacaoWeb;
    }

    public void setIdEstacaoWeb(Long idEstacaoWeb) {
        this.idEstacaoWeb = idEstacaoWeb;
    }


}
