package br.com.geodrone.resource;

import java.util.Date;

public class PrevisaoTempoArqResource {

	private Long id;
    private String nomeArquivo;
    private Long idCliente;
    private Long idMicroRegiao;
    private Date dtPrevisao;

    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdMicroRegiao() {
        return idMicroRegiao;
    }

    public void setIdMicroRegiao(Long idMicroRegiao) {
        this.idMicroRegiao = idMicroRegiao;
    }

    public Date getDtPrevisao() {
        return dtPrevisao;
    }

    public void setDtPrevisao(Date dtPrevisao) {
        this.dtPrevisao = dtPrevisao;
    }
}
