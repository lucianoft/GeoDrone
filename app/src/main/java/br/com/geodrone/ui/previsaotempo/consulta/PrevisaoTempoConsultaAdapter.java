package br.com.geodrone.ui.previsaotempo.consulta;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.resource.ClienteResource;
import br.com.geodrone.resource.PrevisaoTempoArqResource;
import br.com.geodrone.utils.DateUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PrevisaoTempoConsultaAdapter extends RecyclerView.Adapter<PrevisaoTempoConsultaAdapter.PrevisaoTempoConsultaViewHolder> {

    private DateUtils dateUtils = new DateUtils();
    private PrevisaoTempoConsultaActivity activity;
    public PrevisaoTempoConsultaAdapter(PrevisaoTempoConsultaActivity activity){
        this.activity = activity;
    }
    private List<PrevisaoTempoArqResource> mContent = new ArrayList<>();


    final class PrevisaoTempoConsultaViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewDtPrevisao)
        TextView textViewDtPrevisao;

        @BindView(R.id.textViewDescricao)
        TextView textViewDescricao;

        PrevisaoTempoConsultaViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
        @OnClick
        void onClick(View view) {
            PrevisaoTempoArqResource previsaoTempoArqResource = mContent.get(getAdapterPosition());
            activity.onClickPrevisaoTempoArqResource(previsaoTempoArqResource);
        }
    }


    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public PrevisaoTempoConsultaViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View root = activity.getLayoutInflater().inflate(R.layout.list_previsao_tempo_consulta, viewGroup, false);

        return new PrevisaoTempoConsultaViewHolder(root);
    }

    @Override
    public void onBindViewHolder(PrevisaoTempoConsultaViewHolder holder, int position) {
        PrevisaoTempoArqResource previsaoTempoArqResource = mContent.get(position);
        if (previsaoTempoArqResource != null) {
            holder.textViewDtPrevisao.setText(dateUtils.format(previsaoTempoArqResource.getDtPrevisao(), "dd/MM/yyyy"));
    }
    }

    public void clearData() {
        mContent.clear();
    }

    public void addData(PrevisaoTempoArqResource data) {
        mContent.add(data);
    }

    @Override
    public int getItemCount() {
        return mContent.size();
    }

}