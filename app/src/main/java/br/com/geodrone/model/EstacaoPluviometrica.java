package br.com.geodrone.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.ToMany;

import java.io.Serializable;
import java.util.Date;


/**
 * Created by fernandes on 16/03/2018.
 */
@Entity(generateConstructors = false, nameInDb = "TB_ESTACAO_PLUVIOMETRICA")
public class EstacaoPluviometrica implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id(autoincrement =  true)
    @Property(nameInDb = "ID_ESTACAO")
    private Long id;

    @NotNull
    @Property(nameInDb = "ID_CLIENTE")
    private Long idCliente;

    @NotNull
    @Property(nameInDb = "DESCRICAO")
    private String descricao;

    @NotNull
    @Property(nameInDb = "LATITUDE")
    private Double latitude;

    @NotNull
    @Property(nameInDb = "LONGITUDE")
    private Double longitude;

    @NotNull
    @Property(nameInDb = "DT_INSTALACAO")
    private Date dtInstalacao;

    @NotNull()
    @Property(nameInDb = "DT_INCLUSAO")
    private Date dtInclusao;

    @NotNull()
    @Property(nameInDb = "DT_ALTERACAO")
    private Date dtAlteracao;

    @Property(nameInDb = "ID_USUARIO")
    private Long idUsuario;

    @Property(nameInDb = "ID_ESTACAO_WEB")
    private Long idEstacaoWeb;

    public EstacaoPluviometrica(Long id,
                                Long idCliente,
                                String descricao,
                                Double latitude,
                                Double longitude,
                                Date dtInstalacao,
                                Date dtInclusao,
                                Date dtAlteracao,
                                Long idUsuario,
                                Long idEstacaoWeb) {
        this.id = id;
        this.idCliente = idCliente;
        this.descricao = descricao;
        this.latitude = latitude;
        this.longitude = longitude;
        this.dtInstalacao = dtInstalacao;
        this.dtInclusao = dtInclusao;
        this.dtAlteracao = dtAlteracao;
        this.idUsuario = idUsuario;
        this.idEstacaoWeb = idEstacaoWeb;
    }

    public EstacaoPluviometrica() {
        this(null, null, null, null, null, null, null, null, null, null);
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
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

    public Long getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }


}
