package br.com.geodrone.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;
import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by fernandes on 19/03/2018.
 */
@Entity(generateConstructors = false, generateGettersSetters = true,  nameInDb = "TB_REGISTRO_CHUVA")
public class RegistroChuva implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id(autoincrement =  true)
    @Property(nameInDb = "ID_REGISTRO_CHUVA")
    private Long idRegistroChuva;

    @NotNull
    @Property(nameInDb = "ID_CLIENTE_REF")
    private Long idClienteRef;

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

    public Long getIdClienteRef() {
        return idClienteRef;
    }

    public void setIdClienteRef(Long idClienteRef) {
        this.idClienteRef = idClienteRef;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
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

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
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
