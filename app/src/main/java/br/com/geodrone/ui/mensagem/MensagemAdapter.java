package br.com.geodrone.ui.mensagem;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.model.Usuario;
import br.com.geodrone.model.constantes.FlagDestinoMensagem;
import br.com.geodrone.resource.MensagemResource;
import br.com.geodrone.ui.forum.ChatMessage;
import br.com.geodrone.ui.forum.ForumActivity;

public class MensagemAdapter extends RecyclerView.Adapter<MensagemAdapter.MensagemViewHolder> {

    private Long idUsuario;
    private Activity activity;
    public MensagemAdapter(MensagemActivity activity,Long idUsuario){
        this.activity = activity;
        this.idUsuario  = idUsuario;
    }
    private List<MensagemResource> mContent = new ArrayList<>();

    private static final int USUARIO_LOGADO = 0;
    private static final int OUTRO_USUARIO = 1;

    static final class MensagemViewHolder extends RecyclerView.ViewHolder {

        TextView messageText;
        TextView messageUser;
        TextView messageTime;

        MensagemViewHolder(View view) {
            super(view);

            messageText = (TextView) view.findViewById(R.id.message_text);
            messageUser = (TextView) view.findViewById(R.id.message_user);
            messageTime = (TextView) view.findViewById(R.id.message_time);

        }
    }


    @Override
    public int getItemViewType(int position) {
        if(mContent.get(position).getFlagDestino().equals(FlagDestinoMensagem.USUARIO.value())){
            return USUARIO_LOGADO;
        } else {
            return OUTRO_USUARIO;
        }
    }

    @Override
    public MensagemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View root;
        if (viewType == USUARIO_LOGADO)
            root = activity.getLayoutInflater().inflate(R.layout.item_message_out, viewGroup, false);
        else
            root = activity.getLayoutInflater().inflate(R.layout.item_message_in, viewGroup, false);

        return new MensagemViewHolder(root);
    }

    @Override
    public void onBindViewHolder(MensagemViewHolder holder, int position) {
        MensagemResource chatMessage = mContent.get(position);
        if (chatMessage != null) {
            holder.messageText.setText(chatMessage.getMensagem());
            holder.messageUser.setText(null);

            holder.messageTime.setText(converteTimestamp(chatMessage.getDtRegistro().getTime()));
        }
    }

    public void clearData() {
        mContent.clear();
    }

    public void addData(MensagemResource data) {
        mContent.add(data);
    }

    @Override
    public int getItemCount() {
        return mContent.size();
    }

    private CharSequence converteTimestamp(Long mileSegundos){
        return DateUtils.getRelativeTimeSpanString(mileSegundos, System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
    }
}