package br.com.geodrone.model.api;


import java.util.Date;

public interface AuditModel {

	public Date getDtInclusao();
	public void setDtInclusao(Date dtInclusao);

	public Date getDtAlteracao();
	public void setDtAlteracao(Date dtAlteracao);

	public Long getVersaoSistema();
	public void setVersaoSistema(Long versaoSistema);

}