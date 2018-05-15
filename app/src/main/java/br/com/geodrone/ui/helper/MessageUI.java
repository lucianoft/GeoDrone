package br.com.geodrone.ui.helper;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import br.com.geodrone.R;

/**
 * Created by fernandes on 01/05/2018.
 */

public class MessageUI {

    public static void showMessage(Context ctx, String mensagem){

        Toast.makeText(ctx,mensagem,Toast.LENGTH_LONG).show();
      }

    public static void showMessage(Context ctx, Exception ex){
        Toast.makeText(ctx,ex.getMessage() ,Toast.LENGTH_LONG).show();
    }
}
