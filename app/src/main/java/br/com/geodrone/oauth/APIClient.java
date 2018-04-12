package br.com.geodrone.oauth;

import br.com.geodrone.oauth.dto.AccessToken;
import br.com.geodrone.resource.SincronizacaoResource;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

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

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("sincronizacaos")
    Call<SincronizacaoResource> getSincronizacao(@Body() SincronizacaoResource sincronizacaoResource);


}
