package br.com.geodrone.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;
import java.util.Date;

import br.com.geodrone.model.api.AuditApi;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by fernandes on 30/03/2018.
 */
@Entity(generateConstructors = false, generateGettersSetters = true,  nameInDb = "TB_REGISTRO_PRAGA")
public class RegistroPraga implements AuditApi, Serializable {

    private static final long serialVersionUID = 1L;

    @Id(autoincrement =  true)
    @Property(nameInDb = "ID_REGISTRO_PRAGA")
    private Long idRegistroChuva;

    @Property(nameInDb = "ID_PRAGA_REF")
    private Long idPragaRef;

    @NotNull
    @Property(nameInDb = "ID_CLIENTE_REF")
    private Long idClienteRef;

    @NotNull
    @Property(nameInDb = "LATITUDE")
    private Double latitude;

    @NotNull
    @Property(nameInDb = "LONGITUDE")
    private Double longitude;

    @Property(nameInDb = "OBSERVACAO")
    private String observacao;

    @NotNull
    @Property(nameInDb = "QTDE")
    private Integer qtde;

    @NotNull()
    @Property(nameInDb = "DT_INCLUSAO")
    private Date dtInclusao;

    @NotNull()
    @Property(nameInDb = "DT_ALTERACAO")
    private Date dtAlteracao;

    @NotNull()
    @Property(nameInDb = "ID_USUARIO")
    private Long idUsuario;

    @Property(nameInDb = "ID_REGISTRO_PRAGA_REF")
    private Long idRegistroChuvaRef;

    @Property(nameInDb = "ID_DISPOSITIVO")
    private Long idDispositivo;

    public RegistroPraga() {
    }


    public Long getIdRegistroChuva() {
        return idRegistroChuva;
    }

    public void setIdRegistroChuva(Long idRegistroChuva) {
        this.idRegistroChuva = idRegistroChuva;
    }

    public Long getIdPragaRef() {
        return idPragaRef;
    }

    public void setIdPragaRef(Long idPragaRef) {
        this.idPragaRef = idPragaRef;
    }

    public Long getIdClienteRef() {
        return idClienteRef;
    }

    public void setIdClienteRef(Long idClienteRef) {
        this.idClienteRef = idClienteRef;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }


    public Integer getQtde() {
        return qtde;
    }

    public void setQtde(Integer qtde) {
        this.qtde = qtde;
    }

    @Override
    public Date getDtInclusao() {
        return dtInclusao;
    }

    @Override
    public void setDtInclusao(Date dtInclusao) {
        this.dtInclusao = dtInclusao;
    }

    @Override
    public Date getDtAlteracao() {
        return dtAlteracao;
    }

    @Override
    public void setDtAlteracao(Date dtAlteracao) {
        this.dtAlteracao = dtAlteracao;
    }

    @Override
    public Long getIdUsuario() {
        return idUsuario;
    }

    @Override
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdRegistroChuvaRef() {
        return idRegistroChuvaRef;
    }

    public void setIdRegistroChuvaRef(Long idRegistroChuvaRef) {
        this.idRegistroChuvaRef = idRegistroChuvaRef;
    }

    public Long getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(Long idDispositivo) {
        this.idDispositivo = idDispositivo;
    }
}
