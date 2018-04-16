package br.com.geodrone.service;

import android.content.Context;

import br.com.geodrone.SessionGeooDrone;
import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.Configuracao;
import br.com.geodrone.model.Dispositivo;
import br.com.geodrone.model.Doenca;
import br.com.geodrone.model.Praga;
import br.com.geodrone.model.TipoCultivo;
import br.com.geodrone.model.Usuario;
import br.com.geodrone.oauth.APIClient;
import br.com.geodrone.oauth.ServiceGenerator;
import br.com.geodrone.oauth.dto.AccessToken;
import br.com.geodrone.resource.ClienteResource;
import br.com.geodrone.resource.DoencaResource;
import br.com.geodrone.resource.InstallerResource;
import br.com.geodrone.resource.PragaResource;
import br.com.geodrone.resource.SincronizacaoRetResource;
import br.com.geodrone.resource.TipoCultivoResource;
import br.com.geodrone.resource.UsuarioResource;
import br.com.geodrone.service.util.GenericService;
import br.com.geodrone.utils.Constantes;
import br.com.geodrone.utils.PreferencesUtils;

/**
 * Created by fernandes on 14/04/2018.
 */

public class SincronizacaoService extends GenericService {

    DispositivoService dispositivoService = null;
    ConfiguracaoService configuracaoService = null;
    TipoCultivoService tipoCultivoService = null;
    DoencaService doencaService = null;
    PragaService pragaService = null;
    UsuarioService usuarioService = null;
    ClienteService clienteService = null;

    private Context ctx = null;
    public SincronizacaoService(Context ctx){
        this.ctx = ctx;
        dispositivoService = new DispositivoService(ctx);
        configuracaoService = new ConfiguracaoService(ctx);
        tipoCultivoService = new TipoCultivoService(ctx);
        doencaService = new DoencaService(ctx);
        pragaService = new PragaService(ctx);
        usuarioService = new UsuarioService(ctx);
        clienteService = new ClienteService(ctx);
    }

    public void instalarAplicativo (String url, InstallerResource installerResource)  {

        AccessToken accessToken = PreferencesUtils.getAccessToken(this.ctx);

        APIClient client = ServiceGenerator.getInstance(url).createServiceWithAuth(APIClient.class, accessToken);

        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setId(installerResource.getIdDispositivo());
        dispositivo.setDescricao(installerResource.getDescDispositivo());
        dispositivo.setIdCliente(accessToken.getIdCliente());
        dispositivo = dispositivoService.insert(dispositivo);

        SessionGeooDrone.setAttribute(SessionGeooDrone.CHAVE_DISPOSITIVO, dispositivo);

        Configuracao configuracao = new Configuracao();
        configuracao.setIdCliente(accessToken.getIdCliente());
        configuracao.setIdDispositivo(dispositivo.getId());
        configuracao.setUrl(url);
        configuracao = configuracaoService.insert(configuracao);

        SessionGeooDrone.setAttribute(SessionGeooDrone.CHAVE_URL_BASE, url);

    }

    public void sincronizar(SincronizacaoRetResource sincronizacaoRetResource){
        if (sincronizacaoRetResource.getClienteResource() != null){
            salvarCliente(sincronizacaoRetResource.getClienteResource());
        }

        if (sincronizacaoRetResource.getUsuarioResource() != null){
            salvarUsuario(sincronizacaoRetResource.getUsuarioResource());
        }

        if (sincronizacaoRetResource.getTipoCultivos() != null && !sincronizacaoRetResource.getTipoCultivos().isEmpty()){
            for (TipoCultivoResource tipoCultivoResource : sincronizacaoRetResource.getTipoCultivos()) {
                salvarTipoCultivo(tipoCultivoResource);
            }
        }

        if (sincronizacaoRetResource.getPragas() != null && !sincronizacaoRetResource.getPragas().isEmpty()){
            for (PragaResource pragaResource : sincronizacaoRetResource.getPragas()) {
                salvarPraga(pragaResource);
            }
        }

        if (sincronizacaoRetResource.getDoencas() != null && !sincronizacaoRetResource.getDoencas().isEmpty()){
            for (DoencaResource doencaResource : sincronizacaoRetResource.getDoencas()) {
                salvarDoenca(doencaResource);
            }
        }

    }

    private void salvarTipoCultivo(TipoCultivoResource tipoCultivoResource) {
        boolean bInsert = false;
        TipoCultivo tipoCultivo = tipoCultivoService.findById(tipoCultivoResource.getId());
        if (tipoCultivo == null) {
            bInsert = true;
            tipoCultivo = new TipoCultivo();
        }
        tipoCultivo.setId(tipoCultivoResource.getId());
        tipoCultivo.setDescricao(tipoCultivoResource.getDescricao());
        tipoCultivo.setIndAtivo(tipoCultivoResource.getIndAtivo());
        if (bInsert){
            tipoCultivoService.insert(tipoCultivo);
        }else{
            tipoCultivoService.update(tipoCultivo);
        }

    }


    private void salvarCliente(ClienteResource clienteResource) {
        boolean bInsert = false;

        Cliente cliente = clienteService.findById(clienteResource.getId());
        if (cliente == null) {
            bInsert = true;
            cliente = new Cliente();
        }

        cliente.setId(clienteResource.getId());
        cliente.setNomeRazaoSocial(clienteResource.getNomeRazaoSocial());
        cliente.setSobrenome(clienteResource.getSobrenome());
        cliente.setIndPessoaFisica(clienteResource.getIndPessoaFisica());
        cliente.setCpf(clienteResource.getCpf());
        cliente.setCnpj(clienteResource.getCnpj());
        cliente.setEmail(clienteResource.getEmail());
        cliente.setTelefone(clienteResource.getTelefone());
        cliente.setCelular(clienteResource.getCelular());
        cliente.setSegmento(clienteResource.getSegmento());
        cliente.setLogradouro(clienteResource.getLogradouro());
        cliente.setBairro(clienteResource.getBairro());
        cliente.setCidade(clienteResource.getCidade());
        cliente.setNumero(clienteResource.getNumero());
        cliente.setComplemento(clienteResource.getComplemento());
        cliente.setUf(clienteResource.getUf());
        cliente.setCep(clienteResource.getCep());
        cliente.setQtdeEstacoes(clienteResource.getQtdeEstacoes());
        cliente.setFlagStatus(clienteResource.getFlagStatus());
        if (bInsert){
            cliente = clienteService.insert(cliente);
        }else{
            cliente = clienteService.update(cliente);
        }

        SessionGeooDrone.setAttribute(SessionGeooDrone.CHAVE_CLIENTE, cliente);
    }

    private void salvarUsuario(UsuarioResource usuarioResource) {
        boolean bInsert = false;
        Usuario usuario = usuarioService.findById(usuarioResource.getId());
        if (usuario == null) {
            bInsert = true;
            usuario = new Usuario();
        }

        String senha = PreferencesUtils.getString(ctx, PreferencesUtils.CHAVE_SENHA_USUARIO);

        usuario.setId(usuarioResource.getId());
        usuario.setNome(usuarioResource.getNome());
        usuario.setSobrenome(usuarioResource.getSobrenome());
        usuario.setEmail(usuarioResource.getEmail());
        usuario.setTelefone(usuarioResource.getTelefone());
        usuarioResource.setSenha(senha);
        usuario.setFlagPerfil(usuarioResource.getFlagPerfil());
        usuario.setIdCliente(usuarioResource.getIdCliente());
        usuario.setIndAtivo(usuarioResource.getIndAtivo());

        if (bInsert){
            usuario = usuarioService.insert(usuario);
        }else{
            usuario = usuarioService.update(usuario);
        }

        SessionGeooDrone.setAttribute(SessionGeooDrone.CHAVE_USUARIO, usuario);
    }



    private void salvarPraga(PragaResource pragaResource) {
        boolean bInsert = false;
        Praga praga = pragaService.findById(pragaResource.getId());
        if (praga == null) {
            bInsert = true;
            praga = new Praga();
        }
        praga.setId(pragaResource.getId());
        praga.setDescricao(pragaResource.getDescricao());
        praga.setDescricaoCientifica(pragaResource.getDescricaoCientifica());
        praga.setIdTipoCultivo(pragaResource.getIdTipoCultivo());
        praga.setIndAtivo(pragaResource.getIndAtivo());
        if (bInsert){
            pragaService.insert(praga);
        }else{
            pragaService.update(praga);
        }
    }


    private void salvarDoenca(DoencaResource doencaResource) {
        boolean bInsert = false;
        Doenca doenca = doencaService.findById(doencaResource.getId());
        if (doenca == null) {
            bInsert = true;
            doenca = new Doenca();
        }
        doenca.setId(doencaResource.getId());
        doenca.setDescricao(doencaResource.getDescricao());
        doenca.setDescricaoCientifica(doencaResource.getDescricaoCientifica());
        doenca.setIdTipoCultivo(doencaResource.getIdTipoCultivo());
        doenca.setIndAtivo(doencaResource.getIndAtivo());
        if (bInsert){
            doencaService.insert(doenca);
        }else{
            doencaService.update(doenca);
        }
    }
}
