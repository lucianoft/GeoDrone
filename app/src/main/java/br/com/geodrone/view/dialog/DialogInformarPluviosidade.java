package br.com.geodrone.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import br.com.geodrone.R;

/**
 * Created by fernandes on 25/03/2018.
 */

public class DialogInformarPluviosidade {

    public DialogInformarPluviosidade(final Activity activity, String msg){
        final Dialog dialog = new Dialog(activity, R.style.MyAlertDialogTheme);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_informar_pluviosidade);

        dialog.show();
/*
        DisplayMetrics metrics = new DisplayMetrics(); //get metrics of screen
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = (int) (metrics.widthPixels * 0.9); //set width to 90% of total

        dialog.getWindow().setLayout(width, RelativeLayout.LayoutParams.WRAP_CONTENT); //set layout
        *///        dialog.show();
    }

}

