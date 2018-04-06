package br.com.geodrone.model;

import java.util.Date;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;

import br.com.geodrone.model.api.AuditModel;
import org.greenrobot.greendao.annotation.Generated;

@Entity(generateConstructors = false, nameInDb ="GEO_CLIENTE")
public class Cliente extends GenericModel implements AuditModel {

	@Id
	@NotNull
	private Long id;

	@Property(nameInDb = "NOME_RAZAO_SOCIAL")
	private String nomeRazaoSocial;

	@Property(nameInDb = "SOBRENOME")
	private String sobrenome;

	@Property(nameInDb = "IND_PESSOA_FISICA")
	private Integer indPessoaFisica;

	@Property(nameInDb = "CPF")
	private Long cpf;

	@Property(nameInDb = "CNPJ")
	private Long cnpj;

	@Property(nameInDb = "EMAIL")
	private String email;

	@Property(nameInDb = "TELEFONE")
	private String telefone;

	@Property(nameInDb = "CELULAR")
	private String celular;

	@Property(nameInDb = "FLAG_STATUS")
	private String flagStatus;

	@Property(nameInDb = "DT_INCLUSAO")
	@NotNull
	private Date dtInclusao;

	@Property(nameInDb = "DT_ALTERACAO")
	@NotNull
	private Date dtAlteracao;

	@Property(nameInDb = "VERSAO_SISTEMA")
	@NotNull
	private Long versaoSistema;

	public Cliente() {
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeRazaoSocial() {
		return this.nomeRazaoSocial;
	}
	public void setNomeRazaoSocial(String nomeRazaoSocial) {
		this.nomeRazaoSocial = nomeRazaoSocial;
	}

	public String getSobrenome() {
		return this.sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public Integer getIndPessoaFisica() {
		return this.indPessoaFisica;
	}
	public void setIndPessoaFisica(Integer indPessoaFisica) {
		this.indPessoaFisica = indPessoaFisica;
	}
	public boolean isPessoaFisica() {
		return (indPessoaFisica != null && indPessoaFisica.intValue() == 1);
	}

	public Long getCpf() {
		return this.cpf;
	}
	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public Long getCnpj() {
		return this.cnpj;
	}
	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
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

	public String getCelular() {
		return this.celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getFlagStatus() {
		return this.flagStatus;
	}
	public void setFlagStatus(String flagStatus) {
		this.flagStatus = flagStatus;
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

		final Cliente other = (Cliente) obj;
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
