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
 * Created by fernandes on 01/04/2018.
 */
@Entity(generateConstructors = false, nameInDb = "TB_PONTO_COLETA_IMAGEM")
public class RegistroImagem implements AuditApi, ClienteApi, DispositivoApi, Serializable {

    private static final long serialVersionUID = 1L;

    @Id(autoincrement =  true)
    @Property(nameInDb = "ID_REGISTRO_IMAGEM")
    private Long idRegistroImagem;

    @NotNull
    @Property(nameInDb = "ID_CLIENTE_REF")
    private Long idClienteRef;

    @NotNull
    @Property(nameInDb = "LATITUDE")
    private Double latitude;

    @NotNull
    @Property(nameInDb = "LONGITUDE")
    private Double longitude;

    @Property(nameInDb = "FLAG_DIRECAO")
    private String flagDirecao;

    @Property(nameInDb = "OBSERVACAO")
    private String observacao;

    @NotNull
    @Property(nameInDb = "IMAGEM")
    private byte[] imagem;

    @Property(nameInDb = "ID_IMAGEM_REF")
    private Long idImagemRef;

    @NotNull()
    @Property(nameInDb = "DT_INCLUSAO")
    private Date dtInclusao;

    @NotNull()
    @Property(nameInDb = "DT_ALTERACAO")
    private Date dtAlteracao;

    @NotNull()
    @Property(nameInDb = "ID_USUARIO")
    private Long idUsuario;

    @Property(nameInDb = "ID_REGISTRO_IMAGEM_REF")
    private Long idRegistroImagemRef;

    @Property(nameInDb = "ID_DISPOSITIVO")
    private Long idDispositivo;

    public RegistroImagem() {
    }

    public Long getIdRegistroImagem() {
        return idRegistroImagem;
    }

    public void setIdRegistroImagem(Long idRegistroImagem) {
        this.idRegistroImagem = idRegistroImagem;
    }

    @Override
    public Long getIdClienteRef() {
        return idClienteRef;
    }

    @Override
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

    public String getFlagDirecao() {
        return flagDirecao;
    }

    public void setFlagDirecao(String flagDirecao) {
        this.flagDirecao = flagDirecao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public Long getIdImagemRef() {
        return idImagemRef;
    }

    public void setIdImagemRef(Long idImagemRef) {
        this.idImagemRef = idImagemRef;
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

    public Long getIdRegistroImagemRef() {
        return idRegistroImagemRef;
    }

    public void setIdRegistroImagemRef(Long idRegistroImagemRef) {
        this.idRegistroImagemRef = idRegistroImagemRef;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RegistroImagem that = (RegistroImagem) o;

        return idRegistroImagem != null ? idRegistroImagem.equals(that.idRegistroImagem) : that.idRegistroImagem == null;
    }

    @Override
    public int hashCode() {
        return idRegistroImagem != null ? idRegistroImagem.hashCode() : 0;
    }
}
