package br.com.geodrone.resource;

import java.io.Serializable;

public class InstallerResource implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idDispositivo;
	private String descDispositivo;

	public Long getIdDispositivo() {
		return idDispositivo;
	}

	public void setIdDispositivo(Long idDispositivo) {
		this.idDispositivo = idDispositivo;
	}

	public String getDescDispositivo() {
		return descDispositivo;
	}

	public void setDescDispositivo(String descDispositivo) {
		this.descDispositivo = descDispositivo;
	}

}
