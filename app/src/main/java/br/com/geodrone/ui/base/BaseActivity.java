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
import br.com.geodrone.ui.helper.GenericProgress;
import br.com.geodrone.utils.Message;
import br.com.geodrone.utils.Messenger;

public abstract class BaseActivity extends AppCompatActivity implements BaseError{


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

    public abstract  void showLoading();

    public abstract void hideLoading();

    @Override
    public void onError(String message){
        hideLoading();
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Exception ex){
        hideLoading();
        Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessenger(Messenger messenger){
        if (messenger != null && messenger.getMessages() != null)
            for (Message message : messenger.getMessages()) {
                Toast.makeText(this, message.getMsg(), Toast.LENGTH_SHORT).show();
            }
    }
}
