package br.com.geodrone.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;
import java.util.Date;

import br.com.geodrone.model.api.AuditModel;
import br.com.geodrone.model.api.ClientModel;
import br.com.geodrone.model.api.DeviceModel;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by fernandes on 19/03/2018.
 */
@Entity(generateConstructors = false, generateGettersSetters = true,  nameInDb = "TB_REGISTRO_CHUVA")
public class RegistroChuva implements AuditModel, ClientModel, DeviceModel, Serializable {

    private static final long serialVersionUID = 1L;

    @Id(autoincrement =  true)
    @Property(nameInDb = "ID_REGISTRO_CHUVA_DISP")
    private Long id;

    @NotNull
    @Property(nameInDb = "ID_PONTO_COLETA_CHUVA")
    private Long idPontoColetaChuva;

    @NotNull
    @Property(nameInDb = "ID_CLIENTE")
    private Long idCliente;

    @Property(nameInDb = "OBSERVACAO")
    private String observacao;

    @NotNull
    @Property(nameInDb = "VOLUME")
    private Long volume;

    @Property(nameInDb = "ID_REGISTRO_CHUVA")
    private Long idRegistroChuva;

    @NotNull()
    @Property(nameInDb = "DT_INCLUSAO")
    private Date dtInclusao;

    @NotNull()
    @Property(nameInDb = "DT_ALTERACAO")
    private Date dtAlteracao;

    @Property(nameInDb = "ID_USUARIO")
    private Long idUsuario;

    @Property(nameInDb = "ID_DISPOSITIVO")
    private Long idDispositivo;

    @Property(nameInDb = "VERSAO_SISTEMA")
    @NotNull
    private Long versaoSistema;

    public RegistroChuva() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdPontoColetaChuva() {
        return idPontoColetaChuva;
    }

    public void setIdPontoColetaChuva(Long idPontoColetaChuva) {
        this.idPontoColetaChuva = idPontoColetaChuva;
    }

    @Override
    public Long getIdCliente() {
        return idCliente;
    }

    @Override
    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Long getIdRegistroChuva() {
        return idRegistroChuva;
    }

    public void setIdRegistroChuva(Long idRegistroChuva) {
        this.idRegistroChuva = idRegistroChuva;
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

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public Long getIdDispositivo() {
        return idDispositivo;
    }

    @Override
    public void setIdDispositivo(Long idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    @Override
    public Long getVersaoSistema() {
        return versaoSistema;
    }

    @Override
    public void setVersaoSistema(Long versaoSistema) {
        this.versaoSistema = versaoSistema;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegistroChuva that = (RegistroChuva) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
