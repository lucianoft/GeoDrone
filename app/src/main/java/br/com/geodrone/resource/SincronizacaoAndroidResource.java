package br.com.geodrone.resource;

import java.io.Serializable;
import java.util.List;

public class SincronizacaoAndroidResource {

	private Long idUsuario;
	private Long idCliente;
	private Long idDispositivo;

    private UsuarioResource usuario;
    private List<ClienteResource> clientes;
	private List<ClienteUsuarioResource> clienteUsuarios;
	private List<TipoCultivoResource> tipoCultivos;
	private List<DoencaResource> doencas;
	private List<PragaResource> pragas;
	private List<PontoColetaChuvaResource> pontoColetaChuvas;
	private List<TipoDefensivoResource> tipoDefensivos;
	private List<EstagioInfestacaoResource> estagioInfestacaos;

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


    public UsuarioResource getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioResource usuario) {
        this.usuario = usuario;
    }

    public List<ClienteResource> getClientes() {
        return clientes;
    }

    public void setClientes(List<ClienteResource> clientes) {
        this.clientes = clientes;
    }

    public List<ClienteUsuarioResource> getClienteUsuarios() {
        return clienteUsuarios;
    }

    public void setClienteUsuarios(List<ClienteUsuarioResource> clienteUsuarios) {
        this.clienteUsuarios = clienteUsuarios;
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

	public List<TipoDefensivoResource> getTipoDefensivos() {
		return tipoDefensivos;
	}

	public void setTipoDefensivos(List<TipoDefensivoResource> tipoDefensivos) {
		this.tipoDefensivos = tipoDefensivos;
	}

	public List<EstagioInfestacaoResource> getEstagioInfestacaos() {
		return estagioInfestacaos;
	}

	public void setEstagioInfestacaos(List<EstagioInfestacaoResource> estagioInfestacaos) {
		this.estagioInfestacaos = estagioInfestacaos;
	}
}
