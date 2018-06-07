package br.com.geodrone.ui.talhao;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.model.Talhao;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TalhaoAdapter extends RecyclerView.Adapter<TalhaoAdapter.ChatViewHolder> {

    private ConsultaTalhaoActivity activity;

    public void setActivity(ConsultaTalhaoActivity activity){
        this.activity  = activity;
    }
    private List<Talhao> mContent = new ArrayList<>();


    final class ChatViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewTalhaoDescricao)
        TextView textViewDescricao;

        ChatViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
        @OnClick
        void onClick(View view) {
            Talhao usuarioMensagemResource = mContent.get(getAdapterPosition());
            activity.onClickTalhao(usuarioMensagemResource);
        }
     }



    @Override
    public TalhaoAdapter.ChatViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View root = activity.getLayoutInflater().inflate(R.layout.list_talhao, viewGroup, false);
        return new ChatViewHolder(root);
    }

    @Override
    public void onBindViewHolder(TalhaoAdapter.ChatViewHolder holder, final int position) {
        Talhao talhao = mContent.get(position);
        if (talhao != null) {
            holder.textViewDescricao.setText(talhao.getDescricao());

        }
    }

    public void clearData() {
        mContent.clear();
    }

    public void addData(Talhao data) {
        mContent.add(data);
    }

    @Override
    public int getItemCount() {
        return mContent.size();
    }

}