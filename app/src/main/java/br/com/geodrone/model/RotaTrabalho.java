package br.com.geodrone.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by fernandes on 25/03/2018.
 */
@Entity(generateConstructors = false, createInDb = true, nameInDb = "TB_ROTA_TRABALHO")
public class RotaTrabalho {

    @Id(autoincrement =  true)
    @Property(nameInDb = "ID_ROTA_TRABALHO")
    private Long idRotaTrabalho;

    @NotNull
    @Property(nameInDb = "ID_CLIENTE")
    private Long idCliente;

    @NotNull
    @Property(nameInDb = "LATITUDE")
    private Double latitude;

    @NotNull
    @Property(nameInDb = "LONGITUDE")
    private Double longitude;

    @NotNull()
    @Property(nameInDb = "DT_INCLUSAO")
    private Date dtInclusao;

    @NotNull()
    @Property(nameInDb = "DT_ALTERACAO")
    private Date dtAlteracao;

    @Property(nameInDb = "ID_USUARIO_REF")
    private Long idUsuarioRef;

    @Property(nameInDb = "ID_DISPOSITIVO")
    private Long idDispositivo;

    @Property(nameInDb = "ID_ROTA_TRABALHO_REF")
    private Long idRotaTrabalhoRef;

    public RotaTrabalho() {
    }

    public Long getIdRotaTrabalho() {
        return idRotaTrabalho;
    }

    public void setIdRotaTrabalho(Long idRotaTrabalho) {
        this.idRotaTrabalho = idRotaTrabalho;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
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

    public Long getIdUsuarioRef() {
        return idUsuarioRef;
    }

    public void setIdUsuarioRef(Long idUsuarioRef) {
        this.idUsuarioRef = idUsuarioRef;
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
