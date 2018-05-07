package br.com.geodrone.utils;

import java.lang.annotation.Annotation;

import br.com.geodrone.oauth.ServiceGenerator;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

/**
 * Created by fernandes on 05/05/2018.
 */
public class ErrorUtils {
    public static Messenger parseError(Response<?> response, String url) {
        Converter<ResponseBody, Messenger> converter =
                ServiceGenerator.getInstance(url).getRetrofit()
                        .responseBodyConverter(Messenger.class, new Annotation[0]);

        Messenger error;

        try {
            if (response.code() == 404){
                error = new Messenger();
                error.addError("Erro de conexão com serviço");
            }else {
                error = converter.convert(response.errorBody());
            }
        } catch (Exception e) {
            return new Messenger();
        }

        return error;
    }
}