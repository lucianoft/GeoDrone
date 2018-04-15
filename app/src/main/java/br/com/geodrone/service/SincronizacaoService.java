package br.com.geodrone.service;

import android.content.Context;

import br.com.geodrone.model.Configuracao;
import br.com.geodrone.model.Dispositivo;
import br.com.geodrone.oauth.APIClient;
import br.com.geodrone.oauth.ServiceGenerator;
import br.com.geodrone.oauth.dto.AccessToken;
import br.com.geodrone.resource.InstallerResource;
import br.com.geodrone.resource.SincronizacaoRetResource;
import br.com.geodrone.service.util.GenericService;
import br.com.geodrone.utils.Constantes;
import br.com.geodrone.utils.PreferencesUtils;

/**
 * Created by fernandes on 14/04/2018.
 */

public class SincronizacaoService extends GenericService {

    DispositivoService dispositivoService = null;
    ConfiguracaoService configuracaoService = null;

    private Context ctx = null;
    public SincronizacaoService(Context ctx){
        this.ctx = ctx;
        dispositivoService = new DispositivoService(ctx);
        configuracaoService = new ConfiguracaoService(ctx);
    }

    public void instalarAplicativo (String url, InstallerResource installerResource)  {

        AccessToken accessToken = PreferencesUtils.getAccessToken(this.ctx);

        APIClient client = ServiceGenerator.getInstance(url).createServiceWithAuth(APIClient.class, accessToken);

        Dispositivo dispositivo = new Dispositivo();
        dispositivo.setId(installerResource.getIdDispositivo());
        dispositivo.setDescricao(installerResource.getDescDispositivo());
        dispositivo.setIdCliente(accessToken.getIdCliente());
        dispositivo = dispositivoService.insert(dispositivo);

        Configuracao configuracao = new Configuracao();
        configuracao.setIdCliente(accessToken.getIdCliente());
        configuracao.setIdDispositivo(dispositivo.getId());
        configuracao.setUrl(url);
        configuracaoService.insert(configuracao);
    }

    public void sincronizar(SincronizacaoRetResource sincronizacaoRetResource){

    }
}
