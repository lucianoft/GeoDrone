package br.com.geodrone.service;

import android.content.Context;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.geodrone.SessionGeooDrone;
import br.com.geodrone.model.Cliente;
import br.com.geodrone.model.Configuracao;
import br.com.geodrone.model.Dispositivo;
import br.com.geodrone.model.PontoColetaChuva;
import br.com.geodrone.model.RegistroChuva;
import br.com.geodrone.model.RegistroCondicaoTempo;
import br.com.geodrone.model.RegistroDefensivo;
import br.com.geodrone.model.RegistroDoenca;
import br.com.geodrone.model.RegistroImagem;
import br.com.geodrone.model.RegistroPraga;
import br.com.geodrone.model.Usuario;
import br.com.geodrone.repository.PontoColetaChuvaRepository;
import br.com.geodrone.repository.RegistroChuvaRepository;
import br.com.geodrone.repository.RegistroCondicaoTempoRepository;
import br.com.geodrone.repository.RegistroDefensivoRepository;
import br.com.geodrone.repository.RegistroDoencaRepository;
import br.com.geodrone.repository.RegistroImagemRepository;
import br.com.geodrone.repository.RegistroPragaRepository;
import br.com.geodrone.repository.TalhaoRepository;
import br.com.geodrone.resource.ClienteResource;
import br.com.geodrone.resource.PontoColetaChuvaResource;
import br.com.geodrone.resource.RegistroChuvaResource;
import br.com.geodrone.resource.RegistroCondicaoTempoResource;
import br.com.geodrone.resource.RegistroDefensivoResource;
import br.com.geodrone.resource.RegistroDoencaResource;
import br.com.geodrone.resource.RegistroImagemResource;
import br.com.geodrone.resource.RegistroPragaResource;
import br.com.geodrone.resource.SincronizacaoWebResource;
import br.com.geodrone.resource.UsuarioResource;
import br.com.geodrone.service.util.GenericService;
import br.com.geodrone.utils.DateUtils;
import br.com.geodrone.utils.FileUtils;

/**
 * Created by fernandes on 11/05/2018.
 */

public class SincronizacaoToWebService extends GenericService {
    DispositivoService dispositivoService = null;
    ConfiguracaoService configuracaoService = null;
    TipoCultivoService tipoCultivoService = null;
    DoencaService doencaService = null;
    PragaService pragaService = null;
    UsuarioService usuarioService = null;
    ClienteService clienteService = null;
    PontoColetaChuvaService pontoColetaChuvaService = null;
    PontoColetaChuvaRepository pontoColetaChuvaRepository = null;
    RegistroChuvaService registroChuvaService = null;
    RegistroChuvaRepository registroChuvaRepository = null;
    RegistroImagemService registroImagemService = null;
    RegistroImagemRepository registroImagemRepository = null;
    RegistroPragaService registroPragaService = null;
    RegistroPragaRepository registroPragaRepository = null;
    RegistroDoencaService registroDoencaService = null;
    RegistroDoencaRepository registroDoencaRepository = null;
    RegistroCondicaoTempoService registroCondicaoTempoService = null;
    RegistroCondicaoTempoRepository registroCondicaoTempoRepository = null;
    TalhaoService talhaoService = null;
    TalhaoRepository talhaoRepository = null;
    RegistroDefensivoRepository registroDefensivoRepository;

    private Context ctx = null;

    public SincronizacaoToWebService(Context ctx) {
        this.ctx = ctx;
        dispositivoService = new DispositivoService(ctx);
        configuracaoService = new ConfiguracaoService(ctx);
        tipoCultivoService = new TipoCultivoService(ctx);
        doencaService = new DoencaService(ctx);
        pragaService = new PragaService(ctx);
        usuarioService = new UsuarioService(ctx);
        clienteService = new ClienteService(ctx);
        pontoColetaChuvaService = new PontoColetaChuvaService(ctx);
        pontoColetaChuvaRepository = new PontoColetaChuvaRepository(ctx);
        registroChuvaService = new RegistroChuvaService(ctx);
        registroChuvaRepository = new RegistroChuvaRepository(ctx);
        registroImagemService = new RegistroImagemService(ctx);
        registroImagemRepository = new RegistroImagemRepository(ctx);
        registroPragaService = new RegistroPragaService(ctx);
        registroPragaRepository = new RegistroPragaRepository(ctx);
        registroDoencaService = new RegistroDoencaService(ctx);
        registroDoencaRepository = new RegistroDoencaRepository(ctx);
        registroCondicaoTempoService = new RegistroCondicaoTempoService(ctx);
        registroCondicaoTempoRepository = new RegistroCondicaoTempoRepository(ctx);
        registroDefensivoRepository = new RegistroDefensivoRepository(ctx);
    }

    public SincronizacaoWebResource sincronizarWeb() {

        Configuracao configuracao = configuracaoService.getOneConfiguracao();
        Dispositivo dispositivo = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_DISPOSITIVO);
        Cliente cliente = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_CLIENTE);
        Usuario usuario = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_USUARIO);

        SincronizacaoWebResource sincronizacaoWebResource = new SincronizacaoWebResource();
        sincronizacaoWebResource.setUsuarioResource(getUsuario());
        //sincronizacaoWebResource.setClienteResource(getClienteResource());
        sincronizacaoWebResource.setPontoColetaChuvas(pontoColetaChuvas());
        sincronizacaoWebResource.setRegistroChuvas(getRegistroChuvaResources());
        sincronizacaoWebResource.setRegistroImagems(getRegistroImagemResources());
        sincronizacaoWebResource.setRegistroPragas(getRegistroPragaResources());
        sincronizacaoWebResource.setRegistroDoencas(getRegistroDoencaResources());
        sincronizacaoWebResource.setRegistroCondicaoTempos(getRegistroCondicaoTempoResources());
        sincronizacaoWebResource.setRegistroDefensivos(getRegistroDefensivoResources());
        return sincronizacaoWebResource;

    }

    private UsuarioResource getUsuario() {
        UsuarioResource usuarioResource = new UsuarioResource();
        Usuario usuario = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_USUARIO);

        usuarioResource.setId(usuario.getId());
        usuarioResource.setNome(usuario.getNome());
        usuarioResource.setSobrenome(usuario.getSobrenome());
        usuarioResource.setEmail(usuario.getEmail());
        usuarioResource.setTelefone(usuario.getTelefone());
        usuarioResource.setSenha(usuario.getSenha());
        usuarioResource.setFlagPerfil(usuario.getFlagPerfil());
        usuarioResource.setIdCliente(usuario.getIdCliente());
        usuarioResource.setIndAtivo(usuario.getIndAtivo());
        usuarioResource.setIndAceiteGeoClima(usuario.getIndAceiteGeoClima());
        usuarioResource.setIndAceiteGeomonitora(usuario.getIndAceiteGeomonitora());

        return usuarioResource;
    }

    private ClienteResource getClienteResource() {
        ClienteResource clienteResource = new ClienteResource();
        Cliente cliente = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_CLIENTE);

        clienteResource.setId(cliente.getId());
        clienteResource.setNomeRazaoSocial(cliente.getNomeRazaoSocial());
        clienteResource.setSobrenome(cliente.getSobrenome());
        clienteResource.setIndPessoaFisica(cliente.getIndPessoaFisica());
        clienteResource.setCpf(cliente.getCpf());
        clienteResource.setCnpj(cliente.getCnpj());
        clienteResource.setEmail(cliente.getEmail());
        clienteResource.setTelefone(cliente.getTelefone());
        clienteResource.setCelular(cliente.getCelular());
        clienteResource.setSegmento(cliente.getSegmento());
        clienteResource.setLogradouro(cliente.getLogradouro());
        clienteResource.setBairro(cliente.getBairro());
        clienteResource.setCidade(cliente.getCidade());
        clienteResource.setNumero(cliente.getNumero());
        clienteResource.setComplemento(cliente.getComplemento());
        clienteResource.setUf(cliente.getUf());
        clienteResource.setCep(cliente.getCep());
        clienteResource.setQtdeEstacoes(cliente.getQtdeEstacoes());
        clienteResource.setFlagStatus(cliente.getFlagStatus());
        return clienteResource;
    }

    private List<PontoColetaChuvaResource> pontoColetaChuvas() {
        Cliente cliente = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_CLIENTE);
        Dispositivo dispositivo = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_DISPOSITIVO);

        List<PontoColetaChuvaResource> pontoColetaChuvaResources = new ArrayList<>();

        Date now = new DateUtils().now();
        Date dtSincronizacaoErp = dispositivo.getDtSincronizacaoErp() != null ? dispositivo.getDtSincronizacaoErp() : now;

        List<PontoColetaChuva> pontoColetaChuvas = pontoColetaChuvaRepository.findAllByClienteToSincronizar(cliente.getId(), dtSincronizacaoErp);
        for (PontoColetaChuva pontoColetaChuva : pontoColetaChuvas) {

            PontoColetaChuvaResource pontoColetaChuvaResource = new PontoColetaChuvaResource();
            pontoColetaChuvaResource.setIdPontoColetaChuvaDisp(pontoColetaChuva.getId());
            pontoColetaChuvaResource.setIdPontoColetaChuva(pontoColetaChuva.getIdPontoColetaChuva());
            pontoColetaChuvaResource.setIdDispositivo(pontoColetaChuva.getIdDispositivo());
            pontoColetaChuvaResource.setDescricao(pontoColetaChuva.getDescricao());
            pontoColetaChuvaResource.setDtInstalacao(pontoColetaChuva.getDtInstalacao());
            pontoColetaChuvaResource.setIdCliente(pontoColetaChuva.getIdCliente());
            pontoColetaChuvaResource.setLatitude(pontoColetaChuva.getLatitude());
            pontoColetaChuvaResource.setLongitude(pontoColetaChuva.getLongitude());
            pontoColetaChuvaResource.setIndAtivo(pontoColetaChuva.getIndAtivo());
            pontoColetaChuvaResource.setDtInclusao(pontoColetaChuva.getDtInclusao());
            pontoColetaChuvaResource.setDtAlteracao(pontoColetaChuva.getDtAlteracao());

            pontoColetaChuvaResources.add(pontoColetaChuvaResource);
        }
        return pontoColetaChuvaResources;
    }

    private List<RegistroChuvaResource> getRegistroChuvaResources() {
        Cliente cliente = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_CLIENTE);
        Dispositivo dispositivo = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_DISPOSITIVO);

        Date date = new DateUtils().now();
        Date dtSincronizacaoErp = dispositivo.getDtSincronizacaoErp() != null ? dispositivo.getDtSincronizacaoErp() : date;

        List<RegistroChuvaResource> registroChuvaResources = new ArrayList<>();

        List<RegistroChuva> registroChuvas = registroChuvaRepository.findAllByClienteToSincronizar(cliente.getId(), dtSincronizacaoErp);
        for (RegistroChuva registroChuva: registroChuvas) {

            PontoColetaChuva pontoColetaChuva = pontoColetaChuvaRepository.findById(registroChuva.getIdPontoColetaChuvaDisp());
                    
            RegistroChuvaResource registroChuvaResource = new RegistroChuvaResource();

            registroChuvaResource.setIdRegistroChuva(registroChuva.getIdRegistroChuva());
            registroChuvaResource.setIdRegistroChuvaDisp(registroChuva.getId());
            registroChuvaResource.setVolume(registroChuva.getVolume());
            registroChuvaResource.setIdPontoColetaChuva(pontoColetaChuva.getIdPontoColetaChuva());
            registroChuvaResource.setIdPontoColetaChuvaDisp(pontoColetaChuva.getId());
            registroChuvaResource.setIdCliente(registroChuva.getIdCliente());
            registroChuvaResource.setLatitude(registroChuva.getLatitude());
            registroChuvaResource.setLongitude(registroChuva.getLongitude());
            registroChuvaResource.setDtRegistro(registroChuva.getDtRegistro());
            registroChuvaResource.setIdDispositivo(registroChuva.getIdDispositivo());
            registroChuvaResource.setDtInclusao(registroChuva.getDtInclusao());
            registroChuvaResource.setDtAlteracao(registroChuva.getDtAlteracao());
            registroChuvaResource.setIdUsuarioReg(registroChuva.getIdUsuarioReg());

            registroChuvaResources.add(registroChuvaResource);
        }
        return registroChuvaResources;
    }

    private List<RegistroImagemResource> getRegistroImagemResources() {
        Cliente cliente = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_CLIENTE);
        Dispositivo dispositivo = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_DISPOSITIVO);

        Date date = new DateUtils().now();
        Date dtSincronizacaoErp = dispositivo.getDtSincronizacaoErp() != null ? dispositivo.getDtSincronizacaoErp() : date;

        List<RegistroImagemResource> registroImagemResources = new ArrayList<>();

        List<RegistroImagem> registroImagems = registroImagemRepository.findAllByClienteToSincronizar(cliente.getId(), dtSincronizacaoErp);
        for (RegistroImagem registroImagem: registroImagems) {

            try {
                File file = new File(registroImagem.getPath());
                byte[] imagem = FileUtils.convertFileToByteArray(file);

                RegistroImagemResource registroImagemResource = new RegistroImagemResource();

                registroImagemResource.setIdRegistroImagem(registroImagem.getIdRegistroImagem());
                registroImagemResource.setIdRegistroImagemDisp(registroImagem.getId());
                registroImagemResource.setImagem(imagem);
                registroImagemResource.setObservacao(registroImagem.getObservacao());
                registroImagemResource.setIdCliente(registroImagem.getIdCliente());
                registroImagemResource.setLatitude(registroImagem.getLatitude());
                registroImagemResource.setLongitude(registroImagem.getLongitude());
                registroImagemResource.setDtRegistro(registroImagem.getDtRegistro());
                registroImagemResource.setIdDispositivo(registroImagem.getIdDispositivo());
                registroImagemResource.setDtInclusao(registroImagem.getDtInclusao());
                registroImagemResource.setDtAlteracao(registroImagem.getDtAlteracao());
                registroImagemResource.setIdUsuarioReg(registroImagem.getIdUsuarioReg());

                registroImagemResources.add(registroImagemResource);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return registroImagemResources;
    }

    private List<RegistroPragaResource> getRegistroPragaResources() {
        Cliente cliente = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_CLIENTE);
        Dispositivo dispositivo = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_DISPOSITIVO);

        Date date = new DateUtils().now();
        Date dtSincronizacaoErp = dispositivo.getDtSincronizacaoErp() != null ? dispositivo.getDtSincronizacaoErp() : date;

        List<RegistroPragaResource> registroPragaResources = new ArrayList<>();

        List<RegistroPraga> registroPragas = registroPragaRepository.findAllByClienteToSincronizar(cliente.getId(), dtSincronizacaoErp);
        if (registroPragas != null) {
            for (RegistroPraga registroPraga : registroPragas) {

                RegistroPragaResource registroPragaResource = new RegistroPragaResource();

                registroPragaResource.setIdRegistroPraga(registroPraga.getIdRegistroPraga());
                registroPragaResource.setIdRegistroPragaDisp(registroPraga.getId());
                registroPragaResource.setIdPraga(registroPraga.getIdPraga());
                registroPragaResource.setIdTalhao(registroPraga.getIdTalhao());
                registroPragaResource.setObservacao(registroPraga.getObservacao());
                registroPragaResource.setQtde(registroPraga.getQtde());
                registroPragaResource.setIdCliente(registroPraga.getIdCliente());
                registroPragaResource.setLatitude(registroPraga.getLatitude());
                registroPragaResource.setLongitude(registroPraga.getLongitude());
                registroPragaResource.setDtRegistro(registroPraga.getDtRegistro());
                registroPragaResource.setIdDispositivo(registroPraga.getIdDispositivo());
                registroPragaResource.setIdUsuarioReg(registroPraga.getIdUsuarioReg());

                registroPragaResources.add(registroPragaResource);
            }
        }
        return registroPragaResources;
    }

    private List<RegistroDoencaResource> getRegistroDoencaResources() {
        Cliente cliente = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_CLIENTE);
        Dispositivo dispositivo = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_DISPOSITIVO);

        Date date = new DateUtils().now();
        Date dtSincronizacaoErp = dispositivo.getDtSincronizacaoErp() != null ? dispositivo.getDtSincronizacaoErp() : date;

        List<RegistroDoencaResource> registroDoencaResources = new ArrayList<>();

        List<RegistroDoenca> registroDoencas = registroDoencaRepository.findAllByClienteToSincronizar(cliente.getId(), dtSincronizacaoErp);
        if (registroDoencas != null) {
            for (RegistroDoenca registroDoenca : registroDoencas) {

                RegistroDoencaResource registroDoencaResource = new RegistroDoencaResource();

                registroDoencaResource.setIdRegistroDoenca(registroDoenca.getIdRegistroDoenca());
                registroDoencaResource.setIdRegistroDoencaDisp(registroDoenca.getId());
                registroDoencaResource.setIdTalhao(registroDoenca.getIdTalhao());
                registroDoencaResource.setIdDoenca(registroDoenca.getIdDoenca());
                registroDoencaResource.setObservacao(registroDoenca.getObservacao());
                registroDoencaResource.setQtde(registroDoenca.getQtde());
                registroDoencaResource.setIdCliente(registroDoenca.getIdCliente());
                registroDoencaResource.setLatitude(registroDoenca.getLatitude());
                registroDoencaResource.setLongitude(registroDoenca.getLongitude());
                registroDoencaResource.setDtRegistro(registroDoenca.getDtRegistro());
                registroDoencaResource.setIdDispositivo(registroDoenca.getIdDispositivo());
                registroDoencaResource.setIdUsuarioReg(registroDoenca.getIdUsuarioReg());

                registroDoencaResources.add(registroDoencaResource);
            }
        }
        return registroDoencaResources;
    }

    private List<RegistroCondicaoTempoResource> getRegistroCondicaoTempoResources() {
        Cliente cliente = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_CLIENTE);
        Dispositivo dispositivo = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_DISPOSITIVO);

        Date date = new DateUtils().now();
        Date dtSincronizacaoErp = dispositivo.getDtSincronizacaoErp() != null ? dispositivo.getDtSincronizacaoErp() : date;

        List<RegistroCondicaoTempoResource> registroCondicaoTempoResources = new ArrayList<>();

        List<RegistroCondicaoTempo> registroCondicaoTempos = registroCondicaoTempoRepository.findAllByClienteToSincronizar(cliente.getId(), dtSincronizacaoErp);

        if (registroCondicaoTempos != null) {
            for (RegistroCondicaoTempo registroCondicaoTempo : registroCondicaoTempos) {

                RegistroCondicaoTempoResource registroCondicaoTempoResource = new RegistroCondicaoTempoResource();

                registroCondicaoTempoResource.setIdRegistroCondicaoTempo(registroCondicaoTempo.getIdRegistroCondTempo());
                registroCondicaoTempoResource.setIdRegistroCondicaoTempoDisp(registroCondicaoTempo.getId());
                registroCondicaoTempoResource.setFlagCondicaoTempo(registroCondicaoTempo.getFlagCondicaoTempo());
                registroCondicaoTempoResource.setFlagDirecao(registroCondicaoTempo.getFlagDirecao());
                registroCondicaoTempoResource.setObservacao(registroCondicaoTempo.getObservacao());
                registroCondicaoTempoResource.setIdCliente(registroCondicaoTempo.getIdCliente());
                registroCondicaoTempoResource.setLatitude(registroCondicaoTempo.getLatitude());
                registroCondicaoTempoResource.setLongitude(registroCondicaoTempo.getLongitude());
                registroCondicaoTempoResource.setDtRegistro(registroCondicaoTempo.getDtRegistro());
                registroCondicaoTempoResource.setIdDispositivo(registroCondicaoTempo.getIdDispositivo());
                registroCondicaoTempoResource.setIdUsuarioReg(registroCondicaoTempo.getIdUsuarioReg());

                registroCondicaoTempoResources.add(registroCondicaoTempoResource);
            }
        }
        return registroCondicaoTempoResources;
    }


    private List<RegistroDefensivoResource> getRegistroDefensivoResources() {
        Cliente cliente = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_CLIENTE);
        Dispositivo dispositivo = SessionGeooDrone.getAttribute(SessionGeooDrone.CHAVE_DISPOSITIVO);

        Date date = new DateUtils().now();
        Date dtSincronizacaoErp = dispositivo.getDtSincronizacaoErp() != null ? dispositivo.getDtSincronizacaoErp() : date;

        List<RegistroDefensivoResource> registroDefensivoResources = new ArrayList<>();

        List<RegistroDefensivo> registroDefensivos = registroDefensivoRepository.findAllByClienteToSincronizar(cliente.getId(), dtSincronizacaoErp);
        if (registroDefensivos != null) {
            for (RegistroDefensivo registroDefensivo : registroDefensivos) {

                RegistroDefensivoResource registroDefensivoResource = new RegistroDefensivoResource();

                registroDefensivoResource.setIdRegistroDefensivo(registroDefensivo.getIdRegistroDefensivo());
                registroDefensivoResource.setIdRegistroDefensivoDisp(registroDefensivo.getId());
                registroDefensivoResource.setIdDefensivoQuimico(registroDefensivo.getIdDefensivoQuimico());
                registroDefensivoResource.setDose(registroDefensivo.getDose());
                registroDefensivoResource.setIdCliente(registroDefensivo.getIdCliente());
                //registroDefensivoResource.setLatitude(registroDefensivo.getLatitude());
                //registroDefensivoResource.setLongitude(registroDefensivo.getLongitude());
                registroDefensivoResource.setDtRegistro(registroDefensivo.getDtRegistro());
                registroDefensivoResource.setIdDispositivo(registroDefensivo.getIdDispositivo());
                registroDefensivoResource.setIdUsuarioReg(registroDefensivo.getIdUsuarioReg());

                registroDefensivoResources.add(registroDefensivoResource);
            }
        }
        return registroDefensivoResources;
    }

}