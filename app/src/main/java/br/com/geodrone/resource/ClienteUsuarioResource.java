package br.com.geodrone.resource;

import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;
import java.util.Date;

import br.com.geodrone.model.GenericModel;
import br.com.geodrone.model.api.ActiveModel;
import br.com.geodrone.model.api.AuditModel;
import br.com.geodrone.model.api.ClientModel;

/**
 * Created by fernandes on 19/05/2018.
 */

public class ClienteUsuarioResource {

    private Long idClienteUsuario;
    private Long idCliente;
    private Long idUsuario;
    private Integer indPadrao;
    private Integer indAtivo;
    private Date dtInclusao;
    private Date dtAlteracao;

    public Long getIdClienteUsuario() {
        return idClienteUsuario;
    }
    public void setIdClienteUsuario(Long idClienteUsuario) {
        this.idClienteUsuario = idClienteUsuario;
    }

    public Long getIdCliente() {
        return this.idCliente;
    }
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

    public Integer getIndAtivo() {
        return this.indAtivo;
    }
    public void setIndAtivo(Integer indAtivo) {
        this.indAtivo = indAtivo;
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


}
