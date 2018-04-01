package br.com.geodrone.ui.base;

import android.widget.Toast;

import br.com.geodrone.utils.Messenger;

/**
 * Created by fernandes on 01/04/2018.
 */

public interface BaseError {

    public void onError(String message);

    public void onError(Exception ex);

    public void showMessage(String message);

    public void showMessenger(Messenger messenger);
}
