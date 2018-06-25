package br.com.geodrone.ui.estagioinfestacao;

/**
 * Created by fernandes on 28/03/2018.
 */
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.model.EstagioInfestacao;

/**
 * Created by akshay on 1/2/15.
 */
public class EstagioInfestacaoAdapter extends ArrayAdapter<EstagioInfestacao> {

    Context context;
    int resource, textViewResourceId;
    List<EstagioInfestacao> items, tempItems, suggestions;

    public TextView nome, nomeCientifico;

    public EstagioInfestacaoAdapter(Context context, int resource, int textViewResourceId, List<EstagioInfestacao> items) {
        super(context, resource, textViewResourceId, items);
        this.context = context;
        this.resource = resource;
        this.textViewResourceId = textViewResourceId;
        this.items = items;
        tempItems = new ArrayList<EstagioInfestacao>(items); // this makes the difference.
        suggestions = new ArrayList<EstagioInfestacao>();

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_estagio_infestacao, parent, false);
        }
        nome = (TextView) view.findViewById(R.id.textViewEstacaoInfestacaoDescricao);
        EstagioInfestacao estagioInfestacao = items.get(position);
        if (estagioInfestacao != null) {
            nome.setText(estagioInfestacao.getDescricao());
        }
        return view;
    }

    @Override
    public void addAll(@NonNull Collection<? extends EstagioInfestacao> collection) {
        this.items = items;
        tempItems = new ArrayList<EstagioInfestacao>(items); // this makes the difference.
    }

    @Override
    public Filter getFilter() {

        return nameFilter;
    }

    /**
     * Custom Filter implementation for custom suggestions we provide.
     */
    Filter nameFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            String str = ((EstagioInfestacao) resultValue).getDescricao();
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (EstagioInfestacao estagioInfestacao : tempItems) {
                    if (estagioInfestacao.getDescricao().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        suggestions.add(estagioInfestacao);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            List<EstagioInfestacao> filterList = (ArrayList<EstagioInfestacao>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (EstagioInfestacao estagioInfestacao : filterList) {
                    add(estagioInfestacao);
                    notifyDataSetChanged();
                }
            }
        }
    };


}
