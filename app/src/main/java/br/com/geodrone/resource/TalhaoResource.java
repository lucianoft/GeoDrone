package br.com.geodrone.resource;

import java.util.Date;

/**
 * Created by fernandes on 07/06/2018.
 */
public class TalhaoResource {

  private Long idTalhao;
    private String codigo;
    private String descricao;
    private Long idCliente;
    private Integer indAtivo;
    private Date dtInclusao;
    private Date dtAlteracao;
    private Long idUsuarioReg;

    public Long getIdTalhao() {
        return idTalhao;
    }

    public void setIdTalhao(Long idTalhao) {
        this.idTalhao = idTalhao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIndAtivo() {
        return indAtivo;
    }

    public void setIndAtivo(Integer indAtivo) {
        this.indAtivo = indAtivo;
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

    public Long getIdUsuarioReg() {
        return idUsuarioReg;
    }

    public void setIdUsuarioReg(Long idUsuarioReg) {
        this.idUsuarioReg = idUsuarioReg;
    }
}
