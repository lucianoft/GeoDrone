package br.com.geodrone.oauth;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;

import br.com.geodrone.oauth.dto.AccessToken;
import br.com.geodrone.ui.main.MainActivity;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by fernandes on 12/04/2018.
 */

public class OAuthInterceptor implements Interceptor {
    private static final String TAG = "OAuthInterceptor";
    private String accessToken,accessTokenType;

    public OAuthInterceptor(AccessToken accessToken){
        this.accessToken = accessToken.getAccessToken();
        this.accessTokenType = accessToken.getTokenType();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        //add it to the request
        Request.Builder builder = chain.request().newBuilder();
        if (!TextUtils.isEmpty(accessToken)&&!TextUtils.isEmpty(accessTokenType)) {
            Log.e(TAG,"In the interceptor adding the header authorization with : "+accessTokenType+" " + accessToken);
            builder.header("Authorization",accessTokenType+" " + accessToken);
        }else{
            Log.e(TAG,"In the interceptor there is a fuck with : "+accessTokenType+" " + accessToken);
        }
        //proceed to the call
        return chain.proceed(builder.build());
    }
}
