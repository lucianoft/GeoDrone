package br.com.geodrone.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

import java.util.Date;

import br.com.geodrone.model.api.AuditApi;
import br.com.geodrone.model.api.ClienteApi;
import br.com.geodrone.model.api.DeviceModel;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by fernandes on 25/03/2018.
 */
@Entity(generateConstructors = false, createInDb = true, nameInDb = "TB_ROTA_TRABALHO")
public class RotaTrabalho implements AuditApi, ClienteApi, DeviceModel {

    @Id(autoincrement =  true)
    @Property(nameInDb = "ID_ROTA_TRABALHO")
    private Long idRotaTrabalho;

    @NotNull
    @Property(nameInDb = "ID_CLIENTE_REF")
    private Long idClienteRef;

    @NotNull
    @Property(nameInDb = "LATITUDE")
    private Double latitude;

    @NotNull
    @Property(nameInDb = "LONGITUDE")
    private Double longitude;

    @Property(nameInDb = "ID_ROTA_TRABALHO_REF")
    private Long idRotaTrabalhoRef;

    @Property(nameInDb = "ID_DISPOSITIVO")
    private Long idDispositivo;

    @NotNull()
    @Property(nameInDb = "DT_INCLUSAO")
    private Date dtInclusao;

    @NotNull()
    @Property(nameInDb = "DT_ALTERACAO")
    private Date dtAlteracao;

    @Property(nameInDb = "ID_USUARIO")
    private Long idUsuario;

    public RotaTrabalho() {
    }

    public Long getIdRotaTrabalho() {
        return idRotaTrabalho;
    }

    public void setIdRotaTrabalho(Long idRotaTrabalho) {
        this.idRotaTrabalho = idRotaTrabalho;
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

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
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

    @Override
    public Long getIdUsuario() {
        return this.idUsuario;
    }

    @Override
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;

    }

    public Long getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(Long idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public Long getIdRotaTrabalhoRef() {
        return idRotaTrabalhoRef;
    }

    public void setIdRotaTrabalhoRef(Long idRotaTrabalhoRef) {
        this.idRotaTrabalhoRef = idRotaTrabalhoRef;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RotaTrabalho that = (RotaTrabalho) o;

        return idRotaTrabalho != null ? idRotaTrabalho.equals(that.idRotaTrabalho) : that.idRotaTrabalho == null;
    }

    @Override
    public int hashCode() {
        return idRotaTrabalho != null ? idRotaTrabalho.hashCode() : 0;
    }
}
