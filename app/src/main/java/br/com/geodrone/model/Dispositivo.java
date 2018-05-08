package br.com.geodrone.model;

import java.util.Date;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;


import br.com.geodrone.model.api.AuditModel;
import br.com.geodrone.model.api.DeviceModel;


@Entity(generateConstructors = false, nameInDb ="GEO_DISPOSITIVO")
public class Dispositivo extends GenericModel implements AuditModel {

	@Id(autoincrement = false)
	@Property(nameInDb = "ID_DISPOSITIVO")
	private Long id;

	@Property(nameInDb = "DESCRICAO")
	private String descricao;

	@Property(nameInDb = "DT_SINCRONIZACAO_ANDROID")
	private Date dtSincronizacaoAndroid;

	@Property(nameInDb = "DT_SINCRONIZACAO_ERP")
	private Date dtSincronizacaoErp;

	@Property(nameInDb = "ID_CLIENTE")
	@NotNull
	private Long idCliente;

	@Property(nameInDb = "DT_INCLUSAO")
	@NotNull
	private Date dtInclusao;

	@Property(nameInDb = "DT_ALTERACAO")
	@NotNull
	private Date dtAlteracao;

	@Property(nameInDb = "VERSAO_SISTEMA")
	@NotNull
	private Long versaoSistema;

	public Dispositivo() {
	}

	public Long getId() {
		return this.id;
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

	public Date getDtSincronizacaoAndroid() {
		return dtSincronizacaoAndroid;
	}

	public void setDtSincronizacaoAndroid(Date dtSincronizacaoAndroid) {
		this.dtSincronizacaoAndroid = dtSincronizacaoAndroid;
	}

	public Date getDtSincronizacaoErp() {
		return dtSincronizacaoErp;
	}

	public void setDtSincronizacaoErp(Date dtSincronizacaoErp) {
		this.dtSincronizacaoErp = dtSincronizacaoErp;
	}

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

		final Dispositivo other = (Dispositivo) obj;
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
	public Long getIdCliente() {
		return this.idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

}
