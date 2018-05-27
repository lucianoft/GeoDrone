package br.com.geodrone.ui.aceiteusuariogeomonitora;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;
import android.widget.CheckBox;

import br.com.geodrone.R;
import br.com.geodrone.ui.base.BaseActivity;
import br.com.geodrone.ui.helper.GenericProgress;
import br.com.geodrone.ui.main.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AceiteUsuarioGeomonitoraActivity extends BaseActivity implements AceiteUsuarioGeomonitoraPresenter.View {

    @BindView(R.id.webView_aceite_usuario_geomonitora)
    WebView view;

    @BindView(R.id.checkbox_aceite_usuario_geomonitora)
    CheckBox checkBox;

    private GenericProgress mProgress;

    AceiteUsuarioGeomonitoraPresenter aceiteUsuarioGeomonitoraPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aceite_usuario_geomonitora);
        ButterKnife.bind(this);

        view.getSettings().setJavaScriptEnabled(true);
        view.loadUrl("file:///android_asset/aceite_usuario_geomonitora.html");
        view.setBackgroundColor(Color.TRANSPARENT);

        aceiteUsuarioGeomonitoraPresenter = new AceiteUsuarioGeomonitoraPresenter(this);
        mProgress = new GenericProgress(this);
        showLoading();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        aceiteUsuarioGeomonitoraPresenter.takeView(this);
        hideLoading();
    }

    @Override
    public void showLoading() {
        mProgress.show();
    }

    @Override
    public void hideLoading() {
        mProgress.hide();
    }

    @Override
    @OnClick(R.id.button_aceite_usuario_geomonitora)
    public void onClickAceite() {
        try {
            showLoading();
            aceiteUsuarioGeomonitoraPresenter.aceite(checkBox.isChecked());
        }catch (Exception ex){
            hideLoading();
            onError(ex);
        }
    }

    @Override
    public void onErrorAceite(String message) {
        hideLoading();
        showMessage(message);
    }

    @Override
    public void onAceiteSucesso(String message) {
        hideLoading();
        //Intent intent = new Intent(this, Forum2Activity.class);
        //startActivity(intent);
        finish();
        super.onBackPressed();
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
