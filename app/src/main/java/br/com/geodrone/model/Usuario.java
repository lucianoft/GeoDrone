package br.com.geodrone.model;

import java.util.Date;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;


import br.com.geodrone.model.api.AuditModel;
import br.com.geodrone.model.api.ActiveModel;
import br.com.geodrone.model.api.ClientModel;
import org.greenrobot.greendao.annotation.Generated;


@Entity(generateConstructors = false, nameInDb ="GEO_USUARIO")
public class Usuario extends GenericModel implements AuditModel, ActiveModel, ClientModel {

	@Id(autoincrement = false)
	@Property(nameInDb = "ID_USUARIO")
	private Long id;

	@Property(nameInDb = "NOME")
	private String nome;

	@Property(nameInDb = "SOBRENOME")
	private String sobrenome;

	@Property(nameInDb = "EMAIL")
	private String email;

	@Property(nameInDb = "TELEFONE")
	private String telefone;

	@Property(nameInDb = "SENHA")
	private String senha;

	@Property(nameInDb = "FLAG_PERFIL")
	private String flagPerfil;

	@Property(nameInDb = "ID_CLIENTE")
	@NotNull
	private Long idCliente;

	@Property(nameInDb = "IND_ACEITE_GEOMONITORA")
	private Integer indAceiteGeomonitora;

	@Property(nameInDb = "IND_ACEITE_GEOCLIMA")
	private Integer indAceiteGeoClima;

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

	public Usuario() {
	}

	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return this.sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return this.telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return this.senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFlagPerfil() {
		return this.flagPerfil;
	}
	public void setFlagPerfil(String flagPerfil) {
		this.flagPerfil = flagPerfil;
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

	public Integer getIndAceiteGeomonitora() {
		return indAceiteGeomonitora;
	}

	public void setIndAceiteGeomonitora(Integer indAceiteGeomonitora) {
		this.indAceiteGeomonitora = indAceiteGeomonitora;
	}

	public Integer getIndAceiteGeoClima() {
        return indAceiteGeoClima;
    }

    public void setIndAceiteGeoClima(Integer indAceiteGeoClima) {
        this.indAceiteGeoClima = indAceiteGeoClima;
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

		final Usuario other = (Usuario) obj;
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
