package br.com.geodrone.model;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

import java.util.Date;

import br.com.geodrone.model.api.ActiveModel;
import br.com.geodrone.model.api.AuditModel;
import br.com.geodrone.model.api.UserModel;
import org.greenrobot.greendao.annotation.Generated;

@Entity(generateConstructors = false, nameInDb = "GEO_TIPO_DEFENSIVO")
public class TipoDefensivo extends GenericModel implements AuditModel, ActiveModel, UserModel {

	@Id
	@Property(nameInDb =  "ID_TIPO_DEFENSIVO")
	//@SequenceGenerator(name = "SEQ_GEO_TIPO_DEFENSIVO", sequenceName = "SQ_GEO_TIPO_DEFENSIVO_01", allocationSize = 1)
	private Long id;

	@Property(nameInDb =  "DESCRICAO")
	@NotNull
	private String descricao;

	@Property(nameInDb =  "IND_ATIVO")
	@NotNull
	private Integer indAtivo;

	@Property(nameInDb =  "DT_INCLUSAO")
	@NotNull
	private Date dtInclusao;

	@Property(nameInDb =  "DT_ALTERACAO")
	@NotNull
	private Date dtAlteracao;

	@Property(nameInDb =  "VERSAO_SISTEMA")
	@NotNull
	private Long versaoSistema;

	@Property(nameInDb =  "ID_USUARIO_REG")
	@NotNull
	private Long idUsuarioReg;

	public TipoDefensivo() {
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


	/** ***** from UserDomain ****** */
	@Override
	public Long getIdUsuarioReg() {
		return this.idUsuarioReg;
	}
	@Override
	public void setIdUsuarioReg(Long idUsuarioReg) {
		this.idUsuarioReg = idUsuarioReg;
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

		final TipoDefensivo other = (TipoDefensivo) obj;
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
		return (this.descricao == null ? "" : this.descricao.toString() );
	}
	/** ***************************** */

}
