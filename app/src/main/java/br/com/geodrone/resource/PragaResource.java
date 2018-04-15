package br.com.geodrone.resource;

import java.io.Serializable;
import java.util.Date;

public class PragaResource implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String descricao;
	private String descricaoCientifica;
	private Long idTipoCultivo;
	private Integer indAtivo;
	private Date dtInclusao;
	private Date dtAlteracao;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getDescricaoCientifica() {
		return descricaoCientifica;
	}
	public void setDescricaoCientifica(String descricaoCientifica) {
		this.descricaoCientifica = descricaoCientifica;
	}
	public Long getIdTipoCultivo() {
		return idTipoCultivo;
	}
	public void setIdTipoCultivo(Long idTipoCultivo) {
		this.idTipoCultivo = idTipoCultivo;
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

}
