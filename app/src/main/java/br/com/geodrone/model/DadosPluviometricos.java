package br.com.geodrone.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

import java.util.Date;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by fernandes on 19/03/2018.
 */
@Entity(generateConstructors = false, generateGettersSetters = true,  nameInDb = "TB_DADOS_PLUVIOMETRICOS")
public class DadosPluviometricos {

    @Id(autoincrement =  true)
    @Property(nameInDb = "ID_DADOS_PLUVIOMETRICOS")
    private Long id;

    @NotNull
    @Property(nameInDb = "ID_CLIENTE")
    private Long idCliente;

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

    @Property(nameInDb = "ID_DADOS_PLUVIOMETRICOS_WEB")
    private Long idDadosPluviometricosWeb;

    public DadosPluviometricos() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCliente() {
        return this.idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getVolume() {
        return this.volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Date getDtInclusao() {
        return this.dtInclusao;
    }

    public void setDtInclusao(Date dtInclusao) {
        this.dtInclusao = dtInclusao;
    }

    public Date getDtAlteracao() {
        return this.dtAlteracao;
    }

    public void setDtAlteracao(Date dtAlteracao) {
        this.dtAlteracao = dtAlteracao;
    }

    public Long getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdDadosPluviometricosWeb() {
        return this.idDadosPluviometricosWeb;
    }

    public void setIdDadosPluviometricosWeb(Long idDadosPluviometricosWeb) {
        this.idDadosPluviometricosWeb = idDadosPluviometricosWeb;
    }


}
