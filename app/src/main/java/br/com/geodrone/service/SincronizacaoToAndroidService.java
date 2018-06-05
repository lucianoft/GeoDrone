package br.com.geodrone.service;

import android.content.Context;

import br.com.geodrone.SessionGeooDrone;
import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.ClienteUsuario;
import br.com.geodrone.model.Configuracao;
import br.com.geodrone.model.Dispositivo;
import br.com.geodrone.model.Doenca;
import br.com.geodrone.model.EstagioInfestacao;
import br.com.geodrone.model.PontoColetaChuva;
import br.com.geodrone.model.Praga;
import br.com.geodrone.model.TipoCultivo;
import br.com.geodrone.model.TipoDefensivo;
import br.com.geodrone.model.Usuario;
import br.com.geodrone.oauth.APIClient;
import br.com.geodrone.oauth.ServiceGenerator;
import br.com.geodrone.oauth.dto.AccessToken;
import br.com.geodrone.repository.ClienteUsuarioRepository;
import br.com.geodrone.resource.ClienteResource;
import br.com.geodrone.resource.ClienteUsuarioResource;
import br.com.geodrone.resource.DoencaResource;
import br.com.geodrone.resource.EstagioInfestacaoResource;
import br.com.geodrone.resource.InstallerResource;
import br.com.geodrone.resource.PontoColetaChuvaResource;
import br.com.geodrone.resource.PragaResource;
import br.com.geodrone.resource.SincronizacaoAndroidResource;
import br.com.geodrone.resource.TipoCultivoResource;
import br.com.geodrone.resource.TipoDefensivoResource;
import br.com.geodrone.resource.UsuarioResource;
import br.com.geodrone.service.util.GenericService;
import br.com.geodrone.utils.DateUtils;
import br.com.geodrone.utils.NumberUtils;
import br.com.geodrone.utils.PreferencesUtils;

/**
 * Created by fernandes on 14/04/2018.
 */

public class SincronizacaoToAndroidService extends GenericService {

    DispositivoService dispositivoService = null;
    ConfiguracaoService configuracaoService = null;
    TipoCultivoService tipoCultivoService = null;
    DoencaService doencaService = null;
    PragaService pragaService = null;
    UsuarioService usuarioService = null;
    ClienteService clienteService = null;
    PontoColetaChuvaService pontoColetaChuvaService = null;
    ClienteUsuarioService clienteUsuarioService = null;
    ClienteUsuarioRepository clienteUsuarioRepository = null;
    TipoDefensivoService tipoDefensivoService = null;
    EstagioInfestacaoService estagioInfestacaoService = null;

    private Context ctx = null;
    public SincronizacaoToAndroidService(Context ctx){
        this.ctx = ctx;
        dispositivoService = new DispositivoService(ctx);
        configuracaoService = new ConfiguracaoService(ctx);
        tipoCultivoService = new TipoCultivoService(ctx);
        doencaService = new DoencaService(ctx);
        pragaService = new PragaService(ctx);
        usuarioService = new UsuarioService(ctx);
        clienteService = new ClienteService(ctx);
        pontoColetaChuvaService = new PontoColetaChuvaService(ctx);
        clienteUsuarioService = new ClienteUsuarioService(ctx);
        clienteUsuarioRepository = new ClienteUsuarioRepository(ctx);
        tipoDefensivoService = new TipoDefensivoService(ctx);
        estagioInfestacaoService = new EstagioInfestacaoService(ctx);
    }

    public void instalarAplicativo (String url, InstallerResource installerResource)  {

        AccessToken accessToken = PreferencesUtils.getAccessToken(this.ctx);

        APIClient client = ServiceGenerator.getInstance(url).createServiceWithAuth(APIClient.class, accessToken);

        DateUtils dateUtils = new DateUtils();
        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setId(installerResource.getIdDispositivo());
        dispositivo.setDescricao(installerResource.getDescDispositivo());
        dispositivo = dispositivoService.insert(dispositivo);

        SessionGeooDrone.setAttribute(SessionGeooDrone.CHAVE_DISPOSITIVO, dispositivo);

        Configuracao configuracao = configuracaoService.getOneConfiguracao();
        if (configuracao == null) {
            configuracao = new Configuracao();
            configuracao.setUrl(url);
            configuracao = configuracaoService.insert(configuracao);
        }
        SessionGeooDrone.setAttribute(SessionGeooDrone.CHAVE_URL_BASE, url);

    }

    public void sincronizar(SincronizacaoAndroidResource sincronizacaoAndroidResource){
        if (sincronizacaoAndroidResource.getClientes() != null){
            for (ClienteResource clienteResource : sincronizacaoAndroidResource.getClientes()) {
                salvarCliente(clienteResource);
            }
        }

        if (sincronizacaoAndroidResource.getClienteUsuarios() != null){
            for (ClienteUsuarioResource clienteUsuarioResource : sincronizacaoAndroidResource.getClienteUsuarios()) {
                salvarClienteUsuario(clienteUsuarioResource);
            }
        }

        if (sincronizacaoAndroidResource.getUsuario() != null){
            salvarUsuario(sincronizacaoAndroidResource.getUsuario());
        }

        if (sincronizacaoAndroidResource.getTipoCultivos() != null && !sincronizacaoAndroidResource.getTipoCultivos().isEmpty()){
            for (TipoCultivoResource tipoCultivoResource : sincronizacaoAndroidResource.getTipoCultivos()) {
                salvarTipoCultivo(tipoCultivoResource);
            }
        }

        if (sincronizacaoAndroidResource.getTipoDefensivos() != null && !sincronizacaoAndroidResource.getTipoDefensivos().isEmpty()){
            for (TipoDefensivoResource tipoDefensivoResource : sincronizacaoAndroidResource.getTipoDefensivos()) {
                salvarTipoDefensivo(tipoDefensivoResource);
            }
        }
        
        if (sincronizacaoAndroidResource.getPragas() != null && !sincronizacaoAndroidResource.getPragas().isEmpty()){
            for (PragaResource pragaResource : sincronizacaoAndroidResource.getPragas()) {
                salvarPraga(pragaResource);
            }
        }

        if (sincronizacaoAndroidResource.getDoencas() != null && !sincronizacaoAndroidResource.getDoencas().isEmpty()){
            for (DoencaResource doencaResource : sincronizacaoAndroidResource.getDoencas()) {
                salvarDoenca(doencaResource);
            }
        }

        if (sincronizacaoAndroidResource.getDoencas() != null && !sincronizacaoAndroidResource.getDoencas().isEmpty()){
            for (DoencaResource doencaResource : sincronizacaoAndroidResource.getDoencas()) {
                salvarDoenca(doencaResource);
            }
        }

        if (sincronizacaoAndroidResource.getPontoColetaChuvas() != null && !sincronizacaoAndroidResource.getPontoColetaChuvas().isEmpty()){
            for (PontoColetaChuvaResource pontoColetaChuvaResource : sincronizacaoAndroidResource.getPontoColetaChuvas()) {
                salvarPontoColetaChuva(pontoColetaChuvaResource);
            }
        }

        if (sincronizacaoAndroidResource.getEstagioInfestacaos() != null && !sincronizacaoAndroidResource.getEstagioInfestacaos().isEmpty()){
            for (EstagioInfestacaoResource estagioInfestacaoResource : sincronizacaoAndroidResource.getEstagioInfestacaos()) {
                salvarEstagioInfestacao(estagioInfestacaoResource);
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

    private void salvarTipoDefensivo(TipoDefensivoResource tipoDefensivoResource) {
        boolean bInsert = false;
        TipoDefensivo tipoDefensivo = tipoDefensivoService.findById(tipoDefensivoResource.getId());
        if (tipoDefensivo == null) {
            bInsert = true;
            tipoDefensivo = new TipoDefensivo();
        }
        tipoDefensivo.setId(tipoDefensivoResource.getId());
        tipoDefensivo.setDescricao(tipoDefensivoResource.getDescricao());
        tipoDefensivo.setIndAtivo(tipoDefensivoResource.getIndAtivo());
        if (bInsert){
            tipoDefensivoService.insert(tipoDefensivo);
        }else{
            tipoDefensivoService.update(tipoDefensivo);
        }

    }

    private void salvarEstagioInfestacao(EstagioInfestacaoResource estagioInfestacaoResource) {
        boolean bInsert = false;
        EstagioInfestacao estagioInfestacao = estagioInfestacaoService.findById(estagioInfestacaoResource.getId());
        if (estagioInfestacao == null) {
            bInsert = true;
            estagioInfestacao = new EstagioInfestacao();
        }
        estagioInfestacao.setId(estagioInfestacaoResource.getId());
        estagioInfestacao.setCodigo(estagioInfestacaoResource.getCodigo());
        estagioInfestacao.setDescricao(estagioInfestacaoResource.getDescricao());
        estagioInfestacao.setIndAtivo(estagioInfestacaoResource.getIndAtivo());
        if (bInsert){
            estagioInfestacaoService.insert(estagioInfestacao);
        }else{
            estagioInfestacaoService.update(estagioInfestacao);
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

    private void salvarClienteUsuario(ClienteUsuarioResource clienteUsuarioResource) {
        boolean bInsert = false;

        ClienteUsuario clienteUsuario = clienteUsuarioService.findById(clienteUsuarioResource.getIdClienteUsuario());
        if (clienteUsuario == null) {
            bInsert = true;
            clienteUsuario = new ClienteUsuario();
        }

        clienteUsuario.setId(clienteUsuarioResource.getIdClienteUsuario());
        clienteUsuario.setIdCliente(clienteUsuarioResource.getIdCliente());
        clienteUsuario.setIdUsuario(clienteUsuarioResource.getIdUsuario());
        clienteUsuario.setIndAtivo(clienteUsuarioResource.getIndAtivo());
        clienteUsuario.setIndPadrao(clienteUsuarioResource.getIndPadrao());
        
        if (bInsert){
            clienteUsuario = clienteUsuarioService.insert(clienteUsuario);
        }else{
            clienteUsuario = clienteUsuarioService.update(clienteUsuario);
        }

    }

    private void salvarUsuario(UsuarioResource usuarioResource) {
        boolean bInsert = false;
        Usuario usuario = usuarioService.findById(usuarioResource.getId());
        if (usuario == null) {
            bInsert = true;
            usuario = new Usuario();
        }

        //String senha = PreferencesUtils.getString(ctx, PreferencesUtils.CHAVE_SENHA_USUARIO);

        usuario.setId(usuarioResource.getId());
        usuario.setNome(usuarioResource.getNome());
        usuario.setSobrenome(usuarioResource.getSobrenome());
        usuario.setEmail(usuarioResource.getEmail());
        usuario.setTelefone(usuarioResource.getTelefone());
        usuarioResource.setSenha(usuarioResource.getSenha());
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

    private void salvarPontoColetaChuva(PontoColetaChuvaResource pontoColetaChuvaResource) {
        boolean bInsert = false;
        NumberUtils numberUtils = new NumberUtils();

        Dispositivo dispositivo = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_DISPOSITIVO);
        boolean isMesmoDispositivo = pontoColetaChuvaResource.getIdDispositivo() != null && numberUtils.isEqualsLong(pontoColetaChuvaResource.getIdDispositivo(), dispositivo.getId());

        PontoColetaChuva pontoColetaChuva = null;
        if (isMesmoDispositivo) {
            pontoColetaChuva = pontoColetaChuvaService.findById(pontoColetaChuvaResource.getIdPontoColetaChuvaDisp());
        }else{
            pontoColetaChuva = pontoColetaChuvaService.findByIdPontoColetaChuvaWeb(pontoColetaChuvaResource.getIdPontoColetaChuva());
        }
        if (pontoColetaChuva == null) {
            bInsert = true;
            pontoColetaChuva = new PontoColetaChuva();
        }

        pontoColetaChuva.setIdPontoColetaChuva(pontoColetaChuvaResource.getIdPontoColetaChuva());
        pontoColetaChuva.setDescricao(pontoColetaChuvaResource.getDescricao());
        pontoColetaChuva.setDtInstalacao(pontoColetaChuvaResource.getDtInstalacao());
        pontoColetaChuva.setIdCliente(pontoColetaChuvaResource.getIdCliente());
        pontoColetaChuva.setLatitude(pontoColetaChuvaResource.getLatitude());
        pontoColetaChuva.setLongitude(pontoColetaChuvaResource.getLongitude());
        pontoColetaChuva.setIndAtivo(pontoColetaChuvaResource.getIndAtivo());

        if (bInsert){
            pontoColetaChuvaService.insert(pontoColetaChuva);
        }else{
            pontoColetaChuvaService.update(pontoColetaChuva);
        }
    }
}
