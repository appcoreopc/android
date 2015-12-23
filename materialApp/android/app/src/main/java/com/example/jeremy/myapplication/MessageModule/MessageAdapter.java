package com.example.jeremy.myapplication.MessageModule;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jeremy.myapplication.R;
import com.example.jeremy.myapplication.FriendModule.DownloadImageTask;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {

    private Context context;
    private List<MessageInfo> messages;
    private LayoutInflater inflater;

    public MessageAdapter(Context ctx, List<MessageInfo> messagesData) {
        context = ctx;
        messages = messagesData;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.message_row, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        MessageInfo data = messages.get(i);
        myViewHolder.name.setText(data.getTitle());
        myViewHolder.email.setText(data.getDescription());
        new DownloadImageTask(myViewHolder.image).execute(data.getImagePath());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView email;
        ImageView image;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.messageTitle);
            email = (TextView) view.findViewById(R.id.messageInfo);
            image = (ImageView) view.findViewById(R.id.messageImage);
        }
    }
}

