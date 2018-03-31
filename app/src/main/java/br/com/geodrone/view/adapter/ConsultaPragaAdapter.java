package br.com.geodrone.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.model.Praga;

/**
 * Created by fernandes on 27/03/2018.
 */
public class ConsultaPragaAdapter extends RecyclerView.Adapter<ConsultaPragaAdapter.MyViewHolder> {

    private List<Praga> moviesList;

    public ConsultaPragaAdapter(List<Praga> moviesList) {
        this.moviesList = moviesList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nome, nomeCientifico;

        public MyViewHolder(View view) {
            super(view);
            nome = (TextView) view.findViewById(R.id.textViewPragaDescricao);
            //nomeCientifico = (TextView) view.findViewById(R.id.textViewPragaNomeCientificao);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_praga_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Praga praga = moviesList.get(position);
        holder.nome.setText(praga.getDescricao());
        holder.nomeCientifico.setText(praga.getDescricaoCientifica());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}