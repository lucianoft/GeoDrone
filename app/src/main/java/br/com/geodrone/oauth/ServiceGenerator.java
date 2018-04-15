package br.com.geodrone.oauth;

import android.text.TextUtils;

import java.io.IOException;

import br.com.geodrone.oauth.dto.AccessToken;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static ServiceGenerator uniqueInstance;

    private OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    private static Retrofit.Builder builder = null;
    private static Retrofit retrofit = null;

    public static ServiceGenerator getInstance(String API_BASE_URL) {

        uniqueInstance = new ServiceGenerator();

        builder = new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create());
        retrofit = builder.build();
        return uniqueInstance;
    }

    private ServiceGenerator(){

    }

    public <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null, null);
    }

    public <S> S createService(Class<S> serviceClass,
                                      String username, String password) {

        final String authToken = Credentials.basic(username, password);
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                final Request request = chain.request().newBuilder()
                        .addHeader("Authorization", authToken)
                        .build();

                return chain.proceed(request);
            }
        };
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(interceptor);
        OkHttpClient client = httpClient.build();

        builder.client(httpClient.build());
        retrofit = builder.build();

        return retrofit.create(serviceClass);
        /*if (!TextUtils.isEmpty(username)
                && !TextUtils.isEmpty(password)) {
            String authToken = Credentials.basic(username, password);

            return createService(serviceClass, authToken);
        }

        return createService(serviceClass, null);*/
    }

    public <S> S createService(
            Class<S> serviceClass, final String authToken) {
        if (!TextUtils.isEmpty(authToken)) {
            AuthenticationInterceptor interceptor =
                    new AuthenticationInterceptor(authToken);

            if (!httpClient.interceptors().contains(interceptor)) {
                httpClient.addInterceptor(interceptor);

                builder.client(httpClient.build());
                retrofit = builder.build();
            }
        }

        return retrofit.create(serviceClass);
    }

    public <S> S createServiceWithAuth(Class<S> serviceClass, final AccessToken accessToken) {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                final Request request = chain.request().newBuilder()
                        .addHeader("Authorization",accessToken.getTokenType()+" " + accessToken.getAccessToken())
                        .build();

                return chain.proceed(request);
            }
        };
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(interceptor);
        OkHttpClient client = httpClient.build();
        builder.client(httpClient.build());
        retrofit = builder.build();

        return retrofit.create(serviceClass);
    }



    /*public static <S> S basicService(Class<S> serviceClass) {
        return builder.client(
                new OkHttpClient.Builder().addInterceptor(chain -> chain.proceed(requestBuild(chain.request(), OauthToken.basic()).build())).build()).build().create(serviceClass);
    }*/

    // oauth access token을 이용한 service를 만든다.
    public static <S> S createServiceS(Class<S> serviceClass, final AccessToken accessToken) {
        final String bearer = accessToken.getTokenType()+" " + accessToken.getAccessToken();

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                final Request request = chain.request().newBuilder()
                        .addHeader("Authorization",bearer)
                        .build();

                return chain.proceed(request);
            }
        };
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(interceptor);
        OkHttpClient client = httpClient.build();

        return retrofit.create(serviceClass);

        /*return builder.client(
                // why x set : o add ??????
                new OkHttpClient.Builder().addInterceptor(chain -> {
                    Request original = chain.request();
                    Response response = chain.proceed(requestBuild(original, accessToken.bearer()).build());
                    *//*if (response.code() == 401) { // 토큰이 만료되었다.
                        // 이렇게 안하면 함수형처럼 response를 설정할 수 없어서 로우하게 만듬.
                        Request newRequest = requestBuild(
                                new Request.Builder()
                                        .url(String.format("%s/oauth/token?grant_type=refresh_token&refresh_token=%s", API_BASE_URL, oauthToken.getRefreshToken()))
                                        .method("POST", RequestBody.create(MediaType.parse("application/json"), new byte[0]))
                                        .build(), oauthToken.bearer()).build(); // create simple requestBuilder

                        Response newResponse = chain.proceed(newRequest); // 동기, 순차적 작동.
                        // 성공했을 경우에만 토큰을 새로 저장한다.
                        if (newResponse.code() == 200) {
                            oauthToken = new GsonBuilder().create().fromJson(newResponse.body().string(), OauthToken.class);
                            response = chain.proceed(requestBuild(original, oauthToken.bearer()).build());
                        }

                        // else {} // 아니면 그냥 흘려보낸다.
                    }*//*

                    return response;
                }).build()
        ).build()
                .create(serviceClass);*/
    }


    private static Request.Builder requestBuild(Request request, String auth) {
        return request.newBuilder()
                .header("Accept", "application/json")
                .header("Authorization", auth)
                .method(request.method(), request.body());
    }
}