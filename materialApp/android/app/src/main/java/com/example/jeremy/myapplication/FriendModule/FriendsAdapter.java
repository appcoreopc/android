package com.example.jeremy.myapplication.FriendModule;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jeremy.myapplication.R;

import java.util.List;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.MyViewHolder> {

    private Context context;
    private List<Friend> friends;
    private LayoutInflater inflater;

    public FriendsAdapter(Context ctx, List<Friend> friendsData) {
        context = ctx;
        friends = friendsData;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.friend_row, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        Friend data = friends.get(i);
        myViewHolder.name.setText(data.getName());
        myViewHolder.email.setText(data.getEmail());

        //ImageView localImageView = new ImageView(context);
        //new DownloadImageTask(localImageView).execute(data.getImage());
        //myViewHolder.image = localImageView;
        //myViewHolder.image.setImageResource(R.drawable.ic_profile);

        new DownloadImageTask(myViewHolder.image).execute(data.getImage());
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView email;
        ImageView image;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.friendname);
            email = (TextView) view.findViewById(R.id.friendemail);
            image = (ImageView) view.findViewById(R.id.friendimage);
        }
    }
}
