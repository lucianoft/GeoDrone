package br.com.geodrone.model;

import java.util.Date;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;


import br.com.geodrone.model.api.AuditModel;
import br.com.geodrone.model.api.ClientModel;
import br.com.geodrone.model.api.DateRegistryModel;
import br.com.geodrone.model.api.DeviceModel;
import br.com.geodrone.model.api.LocationModel;
import br.com.geodrone.model.api.UserModel;


@Entity(generateConstructors = false, nameInDb ="GEO_REGISTRO_DOENCA")
public class RegistroDoenca extends GenericModel implements AuditModel, ClientModel, DeviceModel, UserModel, LocationModel, DateRegistryModel {

	@Id(autoincrement = true)
	@Property(nameInDb = "ID_REGISTRO_DOENCA_DISP")
	private Long id;

	@Property(nameInDb = "OBSERVACAO")
	private String observacao;

	@Property(nameInDb = "ID_DOENCA")
	private Long idDoenca;

	@Property(nameInDb = "ID_REGISTRO_DOENCA")
	private Long idRegistroDoenca;

	@Property(nameInDb = "DT_REGISTRO")
	@NotNull
	private Date dtRegistro;

	@Property(nameInDb = "ID_CLIENTE")
	@NotNull
	private Long idCliente;

	@Property(nameInDb = "LATITUDE")
	@NotNull
	private Double latitude;

	@Property(nameInDb = "LONGITUDE")
	@NotNull
	private Double longitude;

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

	public RegistroDoenca() {
	}

	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getObservacao() {
		return this.observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Long getIdDoenca() {
		return this.idDoenca;
	}
	public void setIdDoenca(Long idDoenca) {
		this.idDoenca = idDoenca;
	}


	public Long getIdRegistroDoenca() {
		return this.idRegistroDoenca;
	}
	public void setIdRegistroDoenca(Long idRegistroDoenca) {
		this.idRegistroDoenca = idRegistroDoenca;
	}

    /** ***** from DateRegistryModel ****** */
    @Override
    public Date getDtRegistro() {
        return dtRegistro;
    }

    @Override
    public void setDtRegistro(Date dtRegistro) {
        this.dtRegistro = dtRegistro;
    }
    /** ***** from DateRegistryModel ****** */


    /** ***** from DomainCliente ****** */
	@Override
	public Long getIdCliente() {
		return this.idCliente;
	}
	@Override
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	/** **** from LocationDomain ***** */
	@Override
	public Double getLongitude() {
		return this.longitude;
	}
	@Override
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	@Override
	public Double getLatitude() {
		return this.latitude;
	}
	@Override
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	/** ***************************** */


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

		final RegistroDoenca other = (RegistroDoenca) obj;
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
