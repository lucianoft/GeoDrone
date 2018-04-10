package br.com.geodrone.model;

import java.util.Date;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;


import br.com.geodrone.model.api.AuditModel;
import br.com.geodrone.model.api.ActiveModel;


@Entity(generateConstructors = false, nameInDb ="GEO_PRAGA")
public class Praga extends GenericModel implements AuditModel, ActiveModel {

	@Id(autoincrement = false)
	@Property(nameInDb = "ID_PRAGA")
	private Long id;

	@Property(nameInDb = "DESCRICAO")
	private String descricao;

	@Property(nameInDb = "DESCRICAO_CIENTIFICA")
	private String descricaoCientifica;

	@Property(nameInDb = "ID_TIPO_CULTIVO")
	private Long idTipoCultivo;

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

	public Praga() {
	}

	public Praga(Long id, String descricao, String descricaoCientifica, Long idTipoCultivo, Integer indAtivo) {
		this.id = id;
		this.descricao = descricao;
		this.descricaoCientifica = descricaoCientifica;
		this.idTipoCultivo = idTipoCultivo;
		this.indAtivo = indAtivo;
	}

	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricaoCientifica() {
		return this.descricaoCientifica;
	}
	public void setDescricaoCientifica(String descricaoCientifica) {
		this.descricaoCientifica = descricaoCientifica;
	}

	public Long getIdTipoCultivo() {
		return this.idTipoCultivo;
	}
	public void setIdTipoCultivo(Long idTipoCultivo) {
		this.idTipoCultivo = idTipoCultivo;
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

	/** ******* from Object ********* */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}

		final Praga other = (Praga) obj;
		if (this.id == null || other.id == null) {
			return false;
		}
		if (this.id.equals(other.id)) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return (this.id == null ? "" : this.id.toString() );
	}
	/** ***************************** */

}
