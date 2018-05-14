package br.com.geodrone.resource;

import java.io.Serializable;
import java.util.List;

public class SincronizacaoWebResource implements Serializable {

    private static final long serialVersionUID = 1L;

    private ClienteResource clienteResource;
    private UsuarioResource usuarioResource;

    private List<PontoColetaChuvaResource> pontoColetaChuvas;
    private List<RegistroChuvaResource> registroChuvas;
    private List<RegistroImagemResource> registroImagems;
    private List<RegistroPragaResource> registroPragas;
    private List<RegistroDoencaResource> registroDoencas;

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
}
