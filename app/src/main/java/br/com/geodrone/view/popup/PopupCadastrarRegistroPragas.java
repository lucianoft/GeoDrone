package br.com.geodrone.view.popup;

import android.app.Activity;
import android.app.Dialog;
import android.location.Location;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.geodrone.R;

/**
 * Created by fernandes on 25/03/2018.
 */

public class PopupCadastrarRegistroPragas {

    private Activity activity;
    private Location location;
    public PopupCadastrarRegistroPragas(Activity activity, Location location){
        this.activity = activity;
        this.location = location;
        showDialog();
    }

    public  void showDialog(){
        final Dialog dialog = new Dialog(activity, R.style.Theme_AppCompat_Light_Dialog);
        dialog.setContentView(R.layout.activity_registro_pragas);
        dialog.show();
    }

}
