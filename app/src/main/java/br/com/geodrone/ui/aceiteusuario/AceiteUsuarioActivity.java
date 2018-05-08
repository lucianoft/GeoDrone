package br.com.geodrone.ui.aceiteusuario;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;

import br.com.geodrone.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AceiteUsuarioActivity extends AppCompatActivity {

    @BindView(R.id.webView_aceite_usuario)
    WebView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aceite_usuario);
        ButterKnife.bind(this);

        view.getSettings().setJavaScriptEnabled(true);
        view.loadUrl("file:///android_asset/aceite_usuario.html");
        view.setBackgroundColor(Color.TRANSPARENT);
    }

    private class WebViewClient extends android.webkit.WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url)
        {
            return super.shouldOverrideUrlLoading(view, url);
        }
    }
}
