package br.com.geodrone.ui.base;

import java.io.File;

import br.com.geodrone.utils.ActivityUtils;
import br.com.geodrone.utils.Messenger;

/**
 * Created by fernandes on 06/06/2018.
 */

public abstract class BaseRelatorioActivity extends BaseActivity{

    public void onRelatorioSucesso(String message, File file) {

        hideLoading();
        showMessage(message);
        try {
            ActivityUtils.openFile(this, file);
        }catch (Exception ex){
            onError(ex);
        }
    }

    public void onRelatorioError(String message) {
        showMessage(message);
        hideLoading();
    }

    public void onRelatorioError(Messenger messenger) {
        showMessenger(messenger);
        hideLoading();
    }

}
