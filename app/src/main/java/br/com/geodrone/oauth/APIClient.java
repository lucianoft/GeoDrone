package br.com.geodrone.oauth;

import br.com.geodrone.oauth.dto.AccessToken;
import br.com.geodrone.resource.InstallerResource;
import br.com.geodrone.resource.SincronizacaoRetResource;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

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

    @POST("/geoodrone/install/{idUsuario}/{idCliente}/{descDispositivo}")
    public Call<InstallerResource> instalarAplicativo(@Path("idUsuario") Long idUsuario,
                                                                @Path("idCliente")    Long idCliente,
                                                                @Path("descDispositivo") String descDispositivo);

    @GET("/geoodrone/sincronizacao/{idUsuario}/{idCliente}/{idDispositivo}/{dtSincronizacao}")
    public Call<SincronizacaoRetResource> getAtualizacoes(@Path("idUsuario")  Long idUsuario,
                                                    @Path("idCliente")  Long idCliente,
                                                    @Path("idDispositivo") Long idDispositivo,
                                                    @Path("dtSincronizacao") String dtSincronizacao) ;
}
