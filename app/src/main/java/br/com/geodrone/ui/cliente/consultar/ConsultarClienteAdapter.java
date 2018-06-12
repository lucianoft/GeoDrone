package br.com.geodrone.ui.cliente.consultar;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.model.constantes.FlagStatusCliente;
import br.com.geodrone.resource.ClienteResource;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConsultarClienteAdapter extends RecyclerView.Adapter<ConsultarClienteAdapter.ConsultarClienteViewHolder> {

    private ConsultarClienteActivity activity;

    public void setActivity(ConsultarClienteActivity activity){
        this.activity  = activity;
    }
    private List<ClienteResource> mContent = new ArrayList<>();


    final class ConsultarClienteViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewNome)
        TextView textViewNome;

        @BindView(R.id.textViewStatus)
        TextView textViewStatus;

        @BindView(R.id.textViewMicroRegiao)
        TextView textViewMicroRegiao;

        ConsultarClienteViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
        @OnClick
        void onClick(View view) {
            ClienteResource clienteResource = mContent.get(getAdapterPosition());
            activity.onClickAlterarStatusCliente(clienteResource);
        }
     }

    @Override
    public ConsultarClienteViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View root = activity.getLayoutInflater().inflate(R.layout.list_clientes, viewGroup, false);
        return new ConsultarClienteViewHolder(root);
    }

    @Override
    public void onBindViewHolder(ConsultarClienteViewHolder holder, final int position) {
        ClienteResource clienteResource = mContent.get(position);
        if (clienteResource != null) {
            holder.textViewNome.setText(clienteResource.getNomeRazaoSocial());
            holder.textViewStatus.setText(FlagStatusCliente.getDescricao(clienteResource.getFlagStatus()));
            holder.textViewMicroRegiao.setText(clienteResource.getDescMicroRegiao());
        }
    }

    public void clearData() {
        mContent.clear();
    }

    public void addData(ClienteResource data) {
        mContent.add(data);
    }

    @Override
    public int getItemCount() {
        return mContent.size();
    }

}