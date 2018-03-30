package br.com.geodrone.ui.registropraga;

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
import br.com.geodrone.model.Praga;

/**
 * Created by akshay on 1/2/15.
 */
public class RegistroPragaAdapter extends ArrayAdapter<Praga> {

    Context context;
    int resource, textViewResourceId;
    List<Praga> items, tempItems, suggestions;

    public TextView nome, nomeCientifico;

    public RegistroPragaAdapter(Context context, int resource, int textViewResourceId, List<Praga> items) {
        super(context, resource, textViewResourceId, items);
        this.context = context;
        this.resource = resource;
        this.textViewResourceId = textViewResourceId;
        this.items = items;
        tempItems = new ArrayList<Praga>(items); // this makes the difference.
        suggestions = new ArrayList<Praga>();

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_praga_layout, parent, false);
        }
        nome = (TextView) view.findViewById(R.id.textViewPragaNomeComum);
        Praga praga = items.get(position);
        if (praga != null) {
            nome.setText(praga.getDescricao() + " - (" + praga.getDescricaoCientifica() + ")");
        }
        return view;
    }

    @Override
    public void addAll(@NonNull Collection<? extends Praga> collection) {
        this.items = items;
        tempItems = new ArrayList<Praga>(items); // this makes the difference.
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
            String str = ((Praga) resultValue).getDescricao() + " - (" + ((Praga) resultValue).getDescricaoCientifica() + ")";
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (Praga praga : tempItems) {
                    if (praga.getDescricao().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        suggestions.add(praga);
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
            List<Praga> filterList = (ArrayList<Praga>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (Praga praga : filterList) {
                    add(praga);
                    notifyDataSetChanged();
                }
            }
        }
    };


}
