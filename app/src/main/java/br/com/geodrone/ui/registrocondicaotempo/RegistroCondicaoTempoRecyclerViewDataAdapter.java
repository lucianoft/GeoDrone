package br.com.geodrone.ui.registrocondicaotempo;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.geodrone.R;

/**
 * Created by Jerry on 3/17/2018.
 */

public class RegistroCondicaoTempoRecyclerViewDataAdapter extends RecyclerView.Adapter<RegistroCondicaoTempoRecyclerViewItemHolder> {

    private List<RegistroCondicaoTempoRecyclerViewItem> carItemList;

    public RegistroCondicaoTempoRecyclerViewDataAdapter(List<RegistroCondicaoTempoRecyclerViewItem> carItemList) {
        this.carItemList = carItemList;
    }

    @Override
    public RegistroCondicaoTempoRecyclerViewItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get LayoutInflater object.
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        // Inflate the RecyclerView item layout xml.
        View carItemView = layoutInflater.inflate(R.layout.activity_registro_condicao_tempo_card_view_item, parent, false);

        // Get car title text view object.
        final TextView carTitleView = (TextView)carItemView.findViewById(R.id.text_card_registro_condicao_tempo);
        // Get car image view object.
        final ImageView carImageView = (ImageView)carItemView.findViewById(R.id.image_card_registro_condicao_tempo);
        // When click the image.
        carImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get car title text.
                String carTitle = carTitleView.getText().toString();
                // Create a snackbar and show it.
                Snackbar snackbar = Snackbar.make(carImageView, "You click " + carTitle +" image", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });

        // Create and return our custom Car Recycler View Item Holder object.
        RegistroCondicaoTempoRecyclerViewItemHolder ret = new RegistroCondicaoTempoRecyclerViewItemHolder(carItemView);
        return ret;
    }

    @Override
    public void onBindViewHolder(RegistroCondicaoTempoRecyclerViewItemHolder holder, int position) {
        if(carItemList!=null) {
            // Get car item dto in list.
            RegistroCondicaoTempoRecyclerViewItem carItem = carItemList.get(position);

            if(carItem != null) {
                // Set car item title.
                holder.getCarTitleText().setText(carItem.getCarName());
                // Set car image resource id.
                holder.getCarImageView().setImageResource(carItem.getCarImageId());
            }
        }
    }

    @Override
    public int getItemCount() {
        int ret = 0;
        if(carItemList!=null)
        {
            ret = carItemList.size();
        }
        return ret;
    }
}