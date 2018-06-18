package br.com.geodrone.resource;

import java.util.Date;

public class DefensivoQuimicoResource {

	private Long id;
	private String descricao;
	private String ingredientesAtivos;
	private Long idTipoDefensivo;
	private Integer indAtivo;
	private Date dtInclusao;
	private Date dtAlteracao;
	private Long versaoSistema;
	
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
	public String getIngredientesAtivos() {
		return ingredientesAtivos;
	}
	public void setIngredientesAtivos(String ingredientesAtivos) {
		this.ingredientesAtivos = ingredientesAtivos;
	}
	public Long getIdTipoDefensivo() {
		return idTipoDefensivo;
	}
	public void setIdTipoDefensivo(Long idTipoDefensivo) {
		this.idTipoDefensivo = idTipoDefensivo;
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
	public Long getVersaoSistema() {
		return versaoSistema;
	}
	public void setVersaoSistema(Long versaoSistema) {
		this.versaoSistema = versaoSistema;
	}

	
}
