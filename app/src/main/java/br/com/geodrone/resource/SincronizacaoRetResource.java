package br.com.geodrone.resource;

import java.io.Serializable;
import java.util.List;

public class SincronizacaoRetResource implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idUsuario;
	private Long idCliente;
	private Long idDispositivo;

	private ClienteResource clienteResource;
	private UsuarioResource usuarioResource;
	private List<TipoCultivoResource> tipoCultivos;
	private List<DoencaResource> doencas;
	private List<PragaResource> pragas;
	private List<PontoColetaChuvaResource> pontoColetaChuvas;

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Long getIdDispositivo() {
		return idDispositivo;
	}

	public void setIdDispositivo(Long idDispositivo) {
		this.idDispositivo = idDispositivo;
	}

	public ClienteResource getClienteResource() {
		return clienteResource;
	}

	public void setClienteResource(ClienteResource clienteResource) {
		this.clienteResource = clienteResource;
	}

	public UsuarioResource getUsuarioResource() {
		return usuarioResource;
	}

	public void setUsuarioResource(UsuarioResource usuarioResource) {
		this.usuarioResource = usuarioResource;
	}

	public List<TipoCultivoResource> getTipoCultivos() {
		return tipoCultivos;
	}

	public void setTipoCultivos(List<TipoCultivoResource> tipoCultivos) {
		this.tipoCultivos = tipoCultivos;
	}

	public List<DoencaResource> getDoencas() {
		return doencas;
	}

	public void setDoencas(List<DoencaResource> doencas) {
		this.doencas = doencas;
	}

	public List<PragaResource> getPragas() {
		return pragas;
	}

	public void setPragas(List<PragaResource> pragas) {
		this.pragas = pragas;
	}

    public List<PontoColetaChuvaResource> getPontoColetaChuvas() {
        return pontoColetaChuvas;
    }

    public void setPontoColetaChuvas(List<PontoColetaChuvaResource> pontoColetaChuvas) {
        this.pontoColetaChuvas = pontoColetaChuvas;
    }
}
