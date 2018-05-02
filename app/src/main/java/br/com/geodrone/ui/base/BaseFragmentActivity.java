package br.com.geodrone.ui.base;

import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import br.com.geodrone.ui.helper.MessageUI;
import br.com.geodrone.utils.Message;
import br.com.geodrone.utils.Messenger;

/**
 * Created by luciano on 31/03/2018.
 */

public class BaseFragmentActivity extends FragmentActivity implements BaseError{

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

    @Override
    public void onError(String message){
        MessageUI.showMessage(this, message);
    }

    @Override
    public void onError(Exception ex){
        Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(String message){
        MessageUI.showMessage(this, message);
    }

    @Override
    public void showMessenger(Messenger messenger){
        if (messenger != null && messenger.getMessages() != null)
            for (Message message : messenger.getMessages()) {
                MessageUI.showMessage(this, message.getMsg());
            }
    }
}
