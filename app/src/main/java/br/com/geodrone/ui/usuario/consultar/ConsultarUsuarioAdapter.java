package br.com.geodrone.ui.usuario.consultar;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.model.constantes.FlagPerfilUsuario;
import br.com.geodrone.model.constantes.FlagSimNao;
import br.com.geodrone.resource.UsuarioResource;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConsultarUsuarioAdapter extends RecyclerView.Adapter<ConsultarUsuarioAdapter.ConsultarUsuarioViewHolder> {

    private ConsultarUsuarioActivity activity;

    public void setActivity(ConsultarUsuarioActivity activity){
        this.activity  = activity;
    }
    private List<UsuarioResource> mContent = new ArrayList<>();


    final class ConsultarUsuarioViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewNome)
        TextView textViewNome;

        @BindView(R.id.textViewAtivo)
        TextView textViewAtivo;

        @BindView(R.id.textViewPerfil)
        TextView textViewMicroRegiao;

        ConsultarUsuarioViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
        @OnClick
        void onClick(View view) {
            UsuarioResource usuarioResource = mContent.get(getAdapterPosition());
            activity.onClickUsuario(usuarioResource);
        }
     }

    @Override
    public ConsultarUsuarioViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View root = activity.getLayoutInflater().inflate(R.layout.list_usuarios, viewGroup, false);
        return new ConsultarUsuarioViewHolder(root);
    }

    @Override
    public void onBindViewHolder(ConsultarUsuarioViewHolder holder, final int position) {
        UsuarioResource usuarioResource = mContent.get(position);
        if (usuarioResource != null) {
            holder.textViewNome.setText(usuarioResource.getNome());
            holder.textViewAtivo.setText(FlagSimNao.getDescricao(usuarioResource.getIndAtivo()).toString());
            holder.textViewMicroRegiao.setText(FlagPerfilUsuario.getDescricao(usuarioResource.getFlagPerfil()));
        }
    }

    public void clearData() {
        mContent.clear();
    }

    public void addData(UsuarioResource data) {
        mContent.add(data);
    }

    @Override
    public int getItemCount() {
        return mContent.size();
    }

}