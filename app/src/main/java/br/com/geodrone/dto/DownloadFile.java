package br.com.geodrone.dto;

import android.net.Uri;

import okhttp3.ResponseBody;

/**
 * Created by fernandes on 03/06/2018.
 */

public class DownloadFile {
    private ResponseBody responseBody;
    private String fileName;
    private Uri uri;

    public ResponseBody getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
