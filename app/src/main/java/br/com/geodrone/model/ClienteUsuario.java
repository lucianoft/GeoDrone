package br.com.geodrone.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

import java.util.Date;

import br.com.geodrone.model.api.ActiveModel;
import br.com.geodrone.model.api.AuditModel;
import br.com.geodrone.model.api.ClientModel;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by fernandes on 19/05/2018.
 */
@Entity(generateConstructors = false, nameInDb ="GEO_CLIENTE_USUARIO")

public class ClienteUsuario extends GenericModel implements AuditModel, ClientModel, ActiveModel {

    @Id(autoincrement = false)
    @Property(nameInDb = "ID_CLIENTE_USUARIO")
    private Long id;

    @Property(nameInDb = "ID_USUARIO")
    @NotNull
    private Long idUsuario;

    @Property(nameInDb = "IND_PADRAO")
    @NotNull
    private Integer indPadrao;

    @Property(nameInDb = "ID_CLIENTE")
    @NotNull
    private Long idCliente;

    @Property(nameInDb = "IND_ATIVO")
    @NotNull
    private Integer indAtivo;

    @Property(nameInDb = "DT_INCLUSAO")
    @NotNull
    private Date dtInclusao;

    @Property(nameInDb = "DT_ALTERACAO")
    @NotNull
    private Date dtAlteracao;

    @Property(nameInDb = "VERSAO_SISTEMA")
    @NotNull
    private Long versaoSistema;

    public ClienteUsuario() {
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }


    /** ***** from DomainCliente ****** */
    @Override
    public Long getIdCliente() {
        return this.idCliente;
    }
    @Override
    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }


    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIndPadrao() {
        return indPadrao;
    }

    public void setIndPadrao(Integer indPadrao) {
        this.indPadrao = indPadrao;
    }

    /** ***** from ActiveDomain ***** */
    @Override
    public Integer getIndAtivo() {
        return this.indAtivo;
    }
    @Override
    public void setIndAtivo(Integer indAtivo) {
        this.indAtivo = indAtivo;
    }

    @Override
    public boolean isAtivo() {
        return (indAtivo != null && indAtivo.intValue() == 1);
    }
    /** ***************************** */

    /** ***** from AuditDomain ****** */
    @Override
    public Date getDtInclusao() {
        return this.dtInclusao;
    }
    @Override
    public void setDtInclusao(Date dtInclusao) {
        this.dtInclusao = dtInclusao;
    }

    @Override
    public Date getDtAlteracao() {
        return this.dtAlteracao;
    }
    @Override
    public void setDtAlteracao(Date dtAlteracao) {
        this.dtAlteracao = dtAlteracao;
    }

    @Override
    public Long getVersaoSistema() {
        return this.versaoSistema;
    }
    @Override
    public void setVersaoSistema(Long versaoSistema) {
        this.versaoSistema = versaoSistema;
    }
    /** ***************************** */


}
