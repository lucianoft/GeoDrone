package br.com.geodrone.oauth;

import java.io.File;
import java.util.List;

import br.com.geodrone.oauth.dto.AccessToken;
import br.com.geodrone.resource.AlterarSenhaUsuarioResourse;
import br.com.geodrone.resource.ClienteResource;
import br.com.geodrone.resource.InstallerResource;
import br.com.geodrone.resource.MensagemResource;
import br.com.geodrone.resource.SincronizacaoAndroidResource;
import br.com.geodrone.resource.SincronizacaoWebResource;
import br.com.geodrone.resource.TalhaoResource;
import br.com.geodrone.resource.UsuarioMensagemResource;
import br.com.geodrone.utils.Constantes;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

public interface APIClient {

    @FormUrlEncoded
    @POST("oauth/token")
    Call<AccessToken> getNewAccessToken(
            @Field("client_id") String clientId,
            @Field("grant_type") String grantType,
            @Field("username") String username,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("oauth/token")
    Call<AccessToken> getRefreshAccessToken(
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("grant_type") String grantType,
            @Field("refresh_token") String refreshToken);

    @POST(Constantes.API_URL_PREFIXO + "install/{idUsuario}/{idCliente}/{descDispositivo}")
    public Call<InstallerResource> instalarAplicativo(@Path("idUsuario") Long idUsuario,
                                                                @Path("idCliente")    Long idCliente,
                                                                @Path("descDispositivo") String descDispositivo);

    @GET(Constantes.API_URL_PREFIXO + "sincronizacao/{idUsuario}/{idCliente}/{idDispositivo}/{dtSincronizacao}")
    public Call<SincronizacaoAndroidResource> getAtualizacoes(@Path("idUsuario")  Long idUsuario,
                                                              @Path("idCliente")  Long idCliente,
                                                              @Path("idDispositivo") Long idDispositivo,
                                                              @Path("dtSincronizacao") String dtSincronizacao);


    @POST(Constantes.API_URL_PREFIXO + "cliente/cadastro-inicial")
    public Call<ClienteResource> cadastrarCliente(@Body ClienteResource clienteResource);

    @POST(Constantes.API_URL_PREFIXO + "sincronizacao/web")
    @Streaming
    public Call<SincronizacaoWebResource> sincronizarWeb(@Body SincronizacaoWebResource sincronizacaoWebResource);


    @POST(Constantes.API_URL_PREFIXO + "mensagems/cadastro")
    public Call<MensagemResource> insert(@Body MensagemResource mensagemResource);

    @GET(Constantes.API_URL_PREFIXO + "mensagems/usuario-resource/{idUsuario}")
    public Call<List<MensagemResource>> findAllMensagemsByUsuario(@Path("idUsuario") Long idUsuario);


    @GET(Constantes.API_URL_PREFIXO + "usuarios/all-mensagem-resource")
    public Call<List<UsuarioMensagemResource>> findAllUsuariosMensagem();

    @PUT(Constantes.API_URL_PREFIXO + "usuarios/{id}/alterar-senha")
    public Call<Void> alterarSenhaUsuario(@Path("id") Long idUsuario,@Body AlterarSenhaUsuarioResourse alterarSenhaUsuarioResourse);

    @POST(Constantes.API_URL_PREFIXO + "recuperar-senha")
    public Call<Void> recuperarSenha(@Query("email") String email);

    @Streaming
    @GET(Constantes.API_URL_PREFIXO + "registro-pragas/cliente/{idCliente}/relatorio")
    public Call<ResponseBody> findRelatorioRegistroPraga(@Path("idCliente") Long idCliente,
                                                         @Query("dtInicio") String dtInicio,
                                                         @Query("dtFim")    String dtFim);

    @Streaming
    @GET(Constantes.API_URL_PREFIXO + "registro-pragas/cliente/{idCliente}/relatorio-indice")
    public Call<ResponseBody> findRelatorioIndicePraga(@Path("idCliente") Long idCliente,
                                                         @Query("dtInicio") String dtInicio,
                                                         @Query("dtFim")    String dtFim);

    @Streaming
    @GET(Constantes.API_URL_PREFIXO + "registro-doencas/cliente/{idCliente}/relatorio")
    public Call<ResponseBody> findRelatorioRegistroDoenca(@Path("idCliente") Long idCliente,
                                                          @Query("dtInicio") String dtInicio,
                                                          @Query("dtFim")    String dtFim);

    @Streaming
    @GET(Constantes.API_URL_PREFIXO + "registro-chuvas/cliente/{idCliente}/relatorio")
    public Call<ResponseBody> findRelatorioRegistroChuva(@Path("idCliente") Long idCliente,
                                                         @Query("dtInicio") String dtInicio,
                                                         @Query("dtFim")    String dtFim);


    @POST(Constantes.API_URL_PREFIXO + "talhaos/cliente/{idCliente}/resource")
    public Call<TalhaoResource> insertTalhao(@Path("idCliente") Long idCliente,
                                       @Body TalhaoResource talhaoResource);

    @PUT(Constantes.API_URL_PREFIXO + "talhaos/{id}/resource")
    public Call<TalhaoResource> updateTalhao(@Path("id") Long id,
                                             @Body TalhaoResource talhaoResource);

    @GET(Constantes.API_URL_PREFIXO + "clientes/resource")
    public Call<List<ClienteResource>> findAllCliente();

}
