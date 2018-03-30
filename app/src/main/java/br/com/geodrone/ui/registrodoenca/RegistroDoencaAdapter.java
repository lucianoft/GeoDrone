package br.com.geodrone.ui.registrodoenca;

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
import br.com.geodrone.model.Doenca;

/**
 * Created by akshay on 1/2/15.
 */
public class RegistroDoencaAdapter extends ArrayAdapter<Doenca> {

    Context context;
    int resource, textViewResourceId;
    List<Doenca> items, tempItems, suggestions;

    public TextView nome, nomeCientifico;

    public RegistroDoencaAdapter(Context context, int resource, int textViewResourceId, List<Doenca> items) {
        super(context, resource, textViewResourceId, items);
        this.context = context;
        this.resource = resource;
        this.textViewResourceId = textViewResourceId;
        this.items = items;
        tempItems = new ArrayList<Doenca>(items); // this makes the difference.
        suggestions = new ArrayList<Doenca>();

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_doenca_layout, parent, false);
        }
        nome = (TextView) view.findViewById(R.id.textViewPraDoencaDescricao);
        //nomeCientifico = (TextView) view.findViewById(R.id.textViewDoencaNomeCientificao);
        Doenca doenca = items.get(position);
        if (doenca != null) {
            nome.setText(doenca.getDescricao() + " - (" + doenca.getDescricaoCientifica() + ")");
            //nomeCientifico.setText(doenca.getDescricaoCientifica());
        }
        return view;
    }

    @Override
    public void addAll(@NonNull Collection<? extends Doenca> collection) {
        this.items = items;
        tempItems = new ArrayList<Doenca>(items); // this makes the difference.
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
            String str = ((Doenca) resultValue).getDescricao() + " - (" + ((Doenca) resultValue).getDescricaoCientifica() + ")";
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (Doenca people : tempItems) {
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
            List<Doenca> filterList = (ArrayList<Doenca>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (Doenca people : filterList) {
                    add(people);
                    notifyDataSetChanged();
                }
            }
        }
    };


}
