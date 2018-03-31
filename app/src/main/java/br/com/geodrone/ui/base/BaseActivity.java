package br.com.geodrone.ui.base;

import android.app.ProgressDialog;
import android.os.Build;

import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import br.com.geodrone.R;

public abstract class BaseActivity extends AppCompatActivity {


    private ProgressBar progressBar;

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    public void showLoading() {
        hideLoading();
        if (this.progressBar != null) {
            this.progressBar.setVisibility(View.VISIBLE);
        }
    }

    public void hideLoading() {
        if (this.progressBar != null) {
            this.progressBar.setVisibility(View.INVISIBLE);
        }
    }

    public void onError(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void onError(Exception ex){
        Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
    }

    public void showMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
