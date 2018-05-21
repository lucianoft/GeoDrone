package br.com.geodrone.resource;

import java.io.Serializable;
import java.util.List;

public class SincronizacaoWebResource {

    private UsuarioResource usuarioResource;

    private List<ClienteResource> clientes;
    private List<ClienteUsuarioResource> clienteUsuarios;

    private List<PontoColetaChuvaResource> pontoColetaChuvas;
    private List<RegistroChuvaResource> registroChuvas;
    private List<RegistroImagemResource> registroImagems;
    private List<RegistroPragaResource> registroPragas;
    private List<RegistroDoencaResource> registroDoencas;
    private List<RegistroCondicaoTempoResource> registroCondicaoTempos;

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

    public UsuarioResource getUsuarioResource() {
        return usuarioResource;
    }

    public void setUsuarioResource(UsuarioResource usuarioResource) {
        this.usuarioResource = usuarioResource;
    }

    public List<PontoColetaChuvaResource> getPontoColetaChuvas() {
        return pontoColetaChuvas;
    }

    public void setPontoColetaChuvas(List<PontoColetaChuvaResource> pontoColetaChuvas) {
        this.pontoColetaChuvas = pontoColetaChuvas;
    }

    public List<RegistroChuvaResource> getRegistroChuvas() {
        return registroChuvas;
    }

    public void setRegistroChuvas(List<RegistroChuvaResource> registroChuvas) {
        this.registroChuvas = registroChuvas;
    }

    public List<RegistroImagemResource> getRegistroImagems() {
        return registroImagems;
    }

    public void setRegistroImagems(List<RegistroImagemResource> registroImagems) {
        this.registroImagems = registroImagems;
    }

    public List<RegistroPragaResource> getRegistroPragas() {
        return registroPragas;
    }

    public void setRegistroPragas(List<RegistroPragaResource> registroPragas) {
        this.registroPragas = registroPragas;
    }

    public List<RegistroDoencaResource> getRegistroDoencas() {
        return registroDoencas;
    }

    public void setRegistroDoencas(List<RegistroDoencaResource> registroDoencas) {
        this.registroDoencas = registroDoencas;
    }

    public List<RegistroCondicaoTempoResource> getRegistroCondicaoTempos() {
        return registroCondicaoTempos;
    }

    public void setRegistroCondicaoTempos(List<RegistroCondicaoTempoResource> registroCondicaoTempos) {
        this.registroCondicaoTempos = registroCondicaoTempos;
    }
}
