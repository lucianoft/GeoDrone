package br.com.geodrone.ui.registrodefensivo;

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
import br.com.geodrone.model.DefensivoQuimico;

/**
 * Created by akshay on 1/2/15.
 */
public class RegistroDefensivoAdapter extends ArrayAdapter<DefensivoQuimico> {

    Context context;
    int resource, textViewResourceId;
    List<DefensivoQuimico> items, tempItems, suggestions;

    public TextView nome, nomeCientifico;

    public RegistroDefensivoAdapter(Context context, int resource, int textViewResourceId, List<DefensivoQuimico> items) {
        super(context, resource, textViewResourceId, items);
        this.context = context;
        this.resource = resource;
        this.textViewResourceId = textViewResourceId;
        this.items = items;
        tempItems = new ArrayList<DefensivoQuimico>(items); // this makes the difference.
        suggestions = new ArrayList<DefensivoQuimico>();

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_defensivo_quimico_layout, parent, false);
        }
        nome = (TextView) view.findViewById(R.id.textViewDefensivoDescricao);
        //nomeCientifico = (TextView) view.findViewById(R.id.textViewDefensivoQuimicoNomeCientificao);
        DefensivoQuimico doenca = items.get(position);
        if (doenca != null) {
            nome.setText(doenca.getDescricao() + " - (" + doenca.getIngredientesAtivos() + ")");
            //nomeCientifico.setText(doenca.getDescricaoCientifica());
        }
        return view;
    }

    @Override
    public void addAll(@NonNull Collection<? extends DefensivoQuimico> collection) {
        this.items = items;
        tempItems = new ArrayList<DefensivoQuimico>(items); // this makes the difference.
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
            String str = ((DefensivoQuimico) resultValue).getDescricao() + " - (" + ((DefensivoQuimico) resultValue).getIngredientesAtivos() + ")";
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (DefensivoQuimico people : tempItems) {
                    if (people.getDescricao().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        suggestions.add(people);
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
            List<DefensivoQuimico> filterList = (ArrayList<DefensivoQuimico>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (DefensivoQuimico people : filterList) {
                    add(people);
                    notifyDataSetChanged();
                }
            }
        }
    };


}
