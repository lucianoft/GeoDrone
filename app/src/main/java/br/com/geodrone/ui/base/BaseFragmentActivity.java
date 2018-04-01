package br.com.geodrone.ui.base;

import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Created by luciano on 31/03/2018.
 */

public class BaseFragmentActivity extends FragmentActivity{

    private ProgressBar progressBar;

    public void setProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
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
