package br.com.geodrone.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;
import java.util.Date;

import br.com.geodrone.model.api.AuditApi;
import br.com.geodrone.model.api.ClienteApi;
import br.com.geodrone.model.api.DispositivoApi;

import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by fernandes on 19/03/2018.
 */
@Entity(generateConstructors = false, generateGettersSetters = true,  nameInDb = "TB_REGISTRO_CHUVA")
public class RegistroChuva implements AuditApi, ClienteApi, DispositivoApi, Serializable {

    private static final long serialVersionUID = 1L;

    @Id(autoincrement =  true)
    @Property(nameInDb = "ID_REGISTRO_CHUVA")
    private Long idRegistroChuva;

    @NotNull
    @Property(nameInDb = "ID_PONTO_COLETA_CHUVA")
    private Long idPontoColetaChuva;

    @NotNull
    @Property(nameInDb = "ID_CLIENTE_REF")
    private Long idClienteRef;

    @Property(nameInDb = "OBSERVACAO")
    private String observacao;

    @NotNull
    @Property(nameInDb = "VOLUME")
    private Long volume;

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

    @Property(nameInDb = "ID_REGISTRO_CHUVA_REF")
    private Long idRegistroChuvaRef;

    public RegistroChuva() {
    }

    public Long getIdRegistroChuva() {
        return idRegistroChuva;
    }

    public void setIdRegistroChuva(Long idRegistroChuva) {
        this.idRegistroChuva = idRegistroChuva;
    }

    public Long getIdPontoColetaChuva() {
        return idPontoColetaChuva;
    }

    public void setIdPontoColetaChuva(Long idPontoColetaChuva) {
        this.idPontoColetaChuva = idPontoColetaChuva;
    }

    @Override
    public Long getIdClienteRef() {
        return idClienteRef;
    }

    @Override
    public void setIdClienteRef(Long idClienteRef) {
        this.idClienteRef = idClienteRef;
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

    @Override
    public Long getIdDispositivo() {
        return idDispositivo;
    }

    @Override
    public void setIdDispositivo(Long idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public Long getIdRegistroChuvaRef() {
        return idRegistroChuvaRef;
    }

    public void setIdRegistroChuvaRef(Long idRegistroChuvaRef) {
        this.idRegistroChuvaRef = idRegistroChuvaRef;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegistroChuva that = (RegistroChuva) o;

        return idRegistroChuva != null ? idRegistroChuva.equals(that.idRegistroChuva) : that.idRegistroChuva == null;
    }

    @Override
    public int hashCode() {
        return idRegistroChuva != null ? idRegistroChuva.hashCode() : 0;
    }
}
