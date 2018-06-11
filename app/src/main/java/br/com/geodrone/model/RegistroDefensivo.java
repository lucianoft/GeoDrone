package br.com.geodrone.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

import java.math.BigDecimal;
import java.util.Date;

import br.com.geodrone.model.api.AuditModel;
import br.com.geodrone.model.api.ClientModel;
import br.com.geodrone.model.api.DateRegistryModel;
import br.com.geodrone.model.api.DeviceModel;
import br.com.geodrone.model.api.UserModel;
import org.greenrobot.greendao.annotation.Generated;

@Entity(generateConstructors = false, nameInDb ="GEO_REGISTRO_DEFENSIVO")
public class RegistroDefensivo extends GenericModel implements AuditModel, UserModel, ClientModel, DeviceModel, DateRegistryModel {

	@Id(autoincrement = true)
	@Property(nameInDb = "ID_REGISTRO_DEFENSIVO_DISP")
	private Long id;

	@Property(nameInDb = "ID_TIPO_DEFENSIVO")
	private Long idTipoDefensivo;

	@Property(nameInDb = "DT_REGISTRO")
	@NotNull
	private Date dtRegistro;

	@Property(nameInDb = "DOSE")
	@NotNull
	private Double dose;

	@Property(nameInDb = "ID_REGISTRO_DEFENSIVO")
	private Long idRegistroDefensivo;

	@Property(nameInDb = "ID_CLIENTE")
	@NotNull
	private Long idCliente;

	
	@Property(nameInDb = "ID_DISPOSITIVO")
	@NotNull
	private Long idDispositivo;

	@Property(nameInDb = "DT_INCLUSAO")
	@NotNull
	private Date dtInclusao;

	@Property(nameInDb = "DT_ALTERACAO")
	@NotNull
	private Date dtAlteracao;

	@Property(nameInDb = "VERSAO_SISTEMA")
	@NotNull
	private Long versaoSistema;

	@Property(nameInDb = "ID_USUARIO_REG")
	@NotNull
	private Long idUsuarioReg;

	public RegistroDefensivo() {
	}

	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getidTipoDefensivo() {
		return idTipoDefensivo;
	}

	public void setidTipoDefensivo(Long idTipoDefensivo) {
		this.idTipoDefensivo = idTipoDefensivo;
	}

	public Long getIdRegistroDefensivo() {
		return idRegistroDefensivo;
	}

	public void setIdRegistroDefensivo(Long idRegistroDefensivo) {
		this.idRegistroDefensivo = idRegistroDefensivo;
	}

	public Double getDose() {
		return this.dose;
	}
	public void setDose(Double dose) {
		this.dose = dose;
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

	/** ***** from UserDomain ****** */
	@Override
	public Long getIdDispositivo() {
		return this.idDispositivo;
	}
	@Override
	public void setIdDispositivo(Long idDispositivo) {
		this.idDispositivo = idDispositivo;
	}
	/** ***************************** */

	/** **** from DateRegistryModel ***** */
	@Override
	public Date getDtRegistro() {
		return this.dtRegistro;
	}
	@Override
	public void setDtRegistro(Date dtRegistro) {
		this.dtRegistro = dtRegistro;
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

		final RegistroDefensivo other = (RegistroDefensivo) obj;
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

	public Long getIdTipoDefensivo() {
		return this.idTipoDefensivo;
	}

	public void setIdTipoDefensivo(Long idTipoDefensivo) {
		this.idTipoDefensivo = idTipoDefensivo;
	}

}
