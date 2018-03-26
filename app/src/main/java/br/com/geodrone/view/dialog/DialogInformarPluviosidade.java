package br.com.geodrone.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;

import br.com.geodrone.R;

/**
 * Created by fernandes on 25/03/2018.
 */

public class DialogInformarPluviosidade {

    public DialogInformarPluviosidade(final Activity activity, String msg){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_informar_pluviosidade);
        dialog.show();
    }

}

