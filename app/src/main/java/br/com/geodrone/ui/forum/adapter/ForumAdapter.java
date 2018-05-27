package br.com.geodrone.ui.forum.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.ui.forum.ChatMessage;
import br.com.geodrone.ui.forum.ForumActivity;

public class ForumAdapter extends RecyclerView.Adapter<ForumAdapter.ChatViewHolder> {

    private ForumActivity activity;

    public void setActivity(ForumActivity activity){
        this.activity  = activity;
    }
    private List<ChatMessage> mContent = new ArrayList<>();

    private static final int USUARIO_LOGADO = 0;
    private static final int OUTRO_USUARIO = 1;

    static final class ChatViewHolder extends RecyclerView.ViewHolder {

        TextView messageText;
        TextView messageUser;
        TextView messageTime;

        ChatViewHolder(View view) {
            super(view);

            messageText = (TextView) view.findViewById(R.id.message_text);
            messageUser = (TextView) view.findViewById(R.id.message_user);
            messageTime = (TextView) view.findViewById(R.id.message_time);

        }
    }


    @Override
    public int getItemViewType(int position) {
        if(mContent.get(position).getMessageUserId().equals(activity.getLoggedInUserName())){
            return USUARIO_LOGADO;
        } else {
            return OUTRO_USUARIO;
        }
    }

    @Override
    public ForumAdapter.ChatViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View root;
        if (viewType == USUARIO_LOGADO)
            root = activity.getLayoutInflater().inflate(R.layout.item_out_message, viewGroup, false);
        else
            root = activity.getLayoutInflater().inflate(R.layout.item_in_message, viewGroup, false);

        return new ChatViewHolder(root);
    }

    @Override
    public void onBindViewHolder(ForumAdapter.ChatViewHolder holder, int position) {
        ChatMessage chatMessage = mContent.get(position);
        if (chatMessage != null) {
            holder.messageText.setText(chatMessage.getMessageText());
            holder.messageUser.setText(chatMessage.getMessageUser());

            holder.messageTime.setText(DateFormat.format("dd-MM-yyyy (HH:mm:ss)", chatMessage.getMessageTime()));
        }
    }

    public void clearData() {
        mContent.clear();
    }

    public void addData(ChatMessage data) {
        mContent.add(data);
    }

    @Override
    public int getItemCount() {
        return mContent.size();
    }

}