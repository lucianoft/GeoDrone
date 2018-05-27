package br.com.geodrone.ui.forum.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import br.com.geodrone.R;
import br.com.geodrone.ui.forum.ChatMessage;
import br.com.geodrone.ui.forum.ForumGeodroneActivity;
import br.com.geodrone.ui.forum.ForumActivity;
import br.com.geodrone.ui.forum.model.ChatModel;
import br.com.geodrone.utils.Util;
import hani.momanii.supernova_emoji_library.Helper.EmojiconTextView;

/**
 * Created by Alessandro Barreto on 23/06/2016.
 */
public class ChatFirebaseAdapter extends RecyclerView.Adapter<ChatFirebaseAdapter.MyChatViewHolder> {

    private static final int RIGHT_MSG = 0;
    private static final int LEFT_MSG = 1;
    private static final int RIGHT_MSG_IMG = 2;
    private static final int LEFT_MSG_IMG = 3;

    private ClickListenerChatFirebase mClickListenerChatFirebase;

    private String nameUser;

    private ForumGeodroneActivity activity;

    public void setActivity(ForumGeodroneActivity activity){
        this.activity  = activity;
    }
    private List<ChatModel> mContent = new ArrayList<>();

    public ChatFirebaseAdapter(String nameUser, ClickListenerChatFirebase mClickListenerChatFirebase) {
        this.nameUser = nameUser;
        this.mClickListenerChatFirebase = mClickListenerChatFirebase;
    }

    public class MyChatViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvTimestamp,tvLocation,tvNameUsr;
        EmojiconTextView txtMessage;
        ImageView ivUser,ivChatPhoto;

        public MyChatViewHolder(View itemView) {
            super(itemView);
            tvNameUsr = (TextView)itemView.findViewById(R.id.tvNameUsr);
            tvTimestamp = (TextView)itemView.findViewById(R.id.timestamp);
            txtMessage = (EmojiconTextView)itemView.findViewById(R.id.txtMessage);
            tvLocation = (TextView)itemView.findViewById(R.id.tvLocation);
            ivChatPhoto = (ImageView)itemView.findViewById(R.id.img_chat);
            //ivUser = (ImageView)itemView.findViewById(R.id.ivUserChat);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            ChatModel model = mContent.get(position);
            if (model.getMapModel() != null){
                mClickListenerChatFirebase.clickImageMapChat(view,position,model.getMapModel().getLatitude(),model.getMapModel().getLongitude());
            }else{
                mClickListenerChatFirebase.clickImageChat(view,position,model.getUserModel().getName(),model.getUserModel().getPhoto_profile(),model.getFile().getUrl_file());
            }
        }

        public void setTvNameUsr(String userName){
            if (tvNameUsr == null) return;
            tvNameUsr.setText(userName);
        }
        public void setTxtMessage(String message){
            if (txtMessage == null)return;
            txtMessage.setText(message);
        }

        public void setIvUser(String urlPhotoUser){
            if (ivUser == null)return;
            Glide.with(ivUser.getContext()).load(urlPhotoUser);
            //.centerCrop().centerCrop().transform(new CircleTransform(ivUser.getContext())).override(40,40).into(ivUser);
        }

        public void setTvTimestamp(String timestamp){
            if (tvTimestamp == null)return;
            tvTimestamp.setText(converteTimestamp(timestamp));
        }

        public void setIvChatPhoto(String url){
            if (ivChatPhoto == null)return;
            Glide.with(ivChatPhoto.getContext()).load(url)
                    //.override(100, 100)
                    //.fitCenter()
                    .into(ivChatPhoto);
            ivChatPhoto.setOnClickListener(this);
        }

        public void tvIsLocation(int visible){
            if (tvLocation == null)return;
            tvLocation.setVisibility(visible);
        }

    }

    @Override
    public MyChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == RIGHT_MSG){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_right,parent,false);
            return new MyChatViewHolder(view);
        }else if (viewType == LEFT_MSG){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_left,parent,false);
            return new MyChatViewHolder(view);
        }else if (viewType == RIGHT_MSG_IMG){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_right_img,parent,false);
            return new MyChatViewHolder(view);
        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_left_img,parent,false);
            return new MyChatViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(MyChatViewHolder holder, int position) {
        ChatModel model = mContent.get(position);
        if (model != null) {
            holder.setTvNameUsr(model.getUserModel().getName());
            holder.setIvUser(model.getUserModel().getPhoto_profile());
            holder.setTxtMessage(model.getMessage());
            holder.setTvTimestamp(model.getTimeStamp());
            holder.tvIsLocation(View.GONE);
            if (model.getFile() != null) {
                holder.tvIsLocation(View.GONE);
                holder.setIvChatPhoto(model.getFile().getUrl_file());
            } else if (model.getMapModel() != null) {
                holder.setIvChatPhoto(Util.local(model.getMapModel().getLatitude(), model.getMapModel().getLongitude()));
                holder.tvIsLocation(View.VISIBLE);
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        ChatModel model = mContent.get(position);
        if (model.getMapModel() != null){
            if (model.getUserModel().getName().equals(nameUser)){
                return RIGHT_MSG_IMG;
            }else{
                return LEFT_MSG_IMG;
            }
        }else if (model.getFile() != null){
            if (model.getFile().getType().equals("img") && model.getUserModel().getName().equals(nameUser)){
                return RIGHT_MSG_IMG;
            }else{
                return LEFT_MSG_IMG;
            }
        }else if (model.getUserModel().getName().equals(nameUser)){
            return RIGHT_MSG;
        }else{
            return LEFT_MSG;
        }
    }

    private CharSequence converteTimestamp(String mileSegundos){
        return DateUtils.getRelativeTimeSpanString(Long.parseLong(mileSegundos), System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
    }

    public void clearData() {
        mContent.clear();
    }

    public void addData(ChatModel data) {
        mContent.add(data);
    }

    @Override
    public int getItemCount() {
        return mContent.size();
    }
}
