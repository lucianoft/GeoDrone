package br.com.geodrone.ui.mensagem.usuarios;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.resource.UsuarioMensagemResource;
import br.com.geodrone.ui.forum.ForumActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UsuariosMensagemAdapter extends RecyclerView.Adapter<UsuariosMensagemAdapter.ChatViewHolder> {

    private MensagemUsuariosActivity activity;

    public void setActivity(MensagemUsuariosActivity activity){
        this.activity  = activity;
    }
    private List<UsuarioMensagemResource> mContent = new ArrayList<>();


    final class ChatViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.edit_text_usuarios_mensagem)
        TextView messageUser;

        ChatViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
        @OnClick
        void onClick(View view) {
            UsuarioMensagemResource usuarioMensagemResource = mContent.get(getAdapterPosition());
            activity.onClickUsuario(usuarioMensagemResource);
        }
     }



    @Override
    public UsuariosMensagemAdapter.ChatViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View root = activity.getLayoutInflater().inflate(R.layout.item_usuario_message, viewGroup, false);
        return new ChatViewHolder(root);
    }

    @Override
    public void onBindViewHolder(UsuariosMensagemAdapter.ChatViewHolder holder, final int position) {
        UsuarioMensagemResource chatMessage = mContent.get(position);
        if (chatMessage != null) {
            holder.messageUser.setText(chatMessage.getNome());

        }
    }

    public void clearData() {
        mContent.clear();
    }

    public void addData(UsuarioMensagemResource data) {
        mContent.add(data);
    }

    @Override
    public int getItemCount() {
        return mContent.size();
    }

}