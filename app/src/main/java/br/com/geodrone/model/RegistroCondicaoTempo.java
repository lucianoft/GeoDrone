package br.com.geodrone.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

import java.util.Date;

import br.com.geodrone.model.api.AuditModel;
import br.com.geodrone.model.api.ClientModel;
import br.com.geodrone.model.api.DateRegistryModel;
import br.com.geodrone.model.api.DeviceModel;
import br.com.geodrone.model.api.LocationModel;
import br.com.geodrone.model.api.UserModel;
import org.greenrobot.greendao.annotation.Generated;

@Entity(generateConstructors = false, nameInDb ="GEO_REGISTRO_COND_TEMPO")
public class RegistroCondicaoTempo extends GenericModel implements AuditModel, UserModel, ClientModel, DateRegistryModel, LocationModel, DeviceModel {

	@Id(autoincrement = true)
	@Property(nameInDb = "ID_REGISTRO_COND_TEMPO_DISP")
	private Long id;

	@Property(nameInDb = "FLAG_DIRECAO")
	private String flagDirecao;

	@Property(nameInDb = "FLAG_CONDICAO_TEMPO")
	private String flagCondicaoTempo;

	@Property(nameInDb = "OBSERVACAO")
	private String observacao;

	@Property(nameInDb = "ID_REGISTRO_COND_TEMPO")
	private Long idRegistroCondTempo;

	@Property(nameInDb = "ID_CLIENTE")
	private Long idCliente;

	@Property(nameInDb = "LATITUDE")
	private Double latitude;

	@Property(nameInDb = "LONGITUDE")
	private Double longitude;

	@Property(nameInDb = "DT_REGISTRO")
	private Date dtRegistro;

	@Property(nameInDb = "ID_DISPOSITIVO")
	private Long idDispositivo;

    @Property(nameInDb = "VERSAO_SISTEMA")
    @NotNull
    private Long versaoSistema;

    @Property(nameInDb = "DT_INCLUSAO")
	private Date dtInclusao;

	@Property(nameInDb = "DT_ALTERACAO")
	private Date dtAlteracao;

	@Property(nameInDb = "ID_USUARIO_REG")
	private Long idUsuarioReg;

	public RegistroCondicaoTempo() {
	}

	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getFlagDirecao() {
		return this.flagDirecao;
	}
	public void setFlagDirecao(String flagDirecao) {
		this.flagDirecao = flagDirecao;
	}

	public String getFlagCondicaoTempo() {
		return this.flagCondicaoTempo;
	}
	public void setFlagCondicaoTempo(String flagCondicaoTempo) {
		this.flagCondicaoTempo = flagCondicaoTempo;
	}

	public String getObservacao() {
		return this.observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
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

		final RegistroCondicaoTempo other = (RegistroCondicaoTempo) obj;
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
	public Long getIdRegistroCondTempo() {
		return this.idRegistroCondTempo;
	}
	public void setIdRegistroCondTempo(Long idRegistroCondTempo) {
		this.idRegistroCondTempo = idRegistroCondTempo;
	}

}
