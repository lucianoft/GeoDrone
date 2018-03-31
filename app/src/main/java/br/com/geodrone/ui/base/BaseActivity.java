package br.com.geodrone.ui.base;

import android.app.ProgressDialog;
import android.os.Build;

import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import br.com.geodrone.R;

public abstract class BaseActivity extends AppCompatActivity {


    private ProgressDialog mProgressDialog;

    public void setmProgressDialog(ProgressDialog mProgressDialog) {
        this.mProgressDialog = mProgressDialog;
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
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    public void onError(String message){
        Toast.makeText(this, R.string.msg_login_invalido, Toast.LENGTH_SHORT).show();
    }

    public void showMessage(String message){
        Toast.makeText(this, R.string.msg_login_invalido, Toast.LENGTH_SHORT).show();
    }
}
