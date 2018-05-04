package br.com.geodrone.ui.registrocondicaotempo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import br.com.geodrone.R;

/**
 * Created by Jerry on 3/17/2018.
 */

public class RegistroCondicaoTempoRecyclerViewItemHolder extends RecyclerView.ViewHolder {

    private TextView carTitleText = null;

    private ImageView carImageView = null;

    public RegistroCondicaoTempoRecyclerViewItemHolder(View itemView) {
        super(itemView);

        if(itemView != null)
        {
            carTitleText = (TextView)itemView.findViewById(R.id.text_card_registro_condicao_tempo);

            carImageView = (ImageView)itemView.findViewById(R.id.image_card_registro_condicao_tempo);
        }
    }

    public TextView getCarTitleText() {
        return carTitleText;
    }

    public ImageView getCarImageView() {
        return carImageView;
    }
}