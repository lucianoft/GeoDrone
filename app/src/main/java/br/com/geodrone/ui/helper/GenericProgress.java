package br.com.geodrone.ui.helper;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.wang.avi.AVLoadingIndicatorView;

import br.com.geodrone.R;

/*
 * Created by diegoricardocf on 27/03/2018.
 */

public class GenericProgress  extends Dialog {

    private AVLoadingIndicatorView mProgress;

    public GenericProgress(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        if(this.getWindow()!=null) {
            this.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }
        setContentView(R.layout.dialog_generic_progress);
        this.setCancelable(false);
        mProgress = findViewById(R.id.default_progress_bar);
    }

    @Override
    public void show() {
        super.show();
        mProgress.setVisibility(View.VISIBLE);
    }


    @Override
    public void hide() {
        super.dismiss();
    }
}
