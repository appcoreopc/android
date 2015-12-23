package com.example.jeremy.myapplication.FriendModule;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jeremy.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class FriendFragment extends Fragment {
    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_friend, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.friendlist);
        FriendsAdapter adapter = new FriendsAdapter(getActivity(), getFakeFriendInfo());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private List<Friend> getFakeFriendInfo()
    {
        List<Friend> list = new ArrayList<Friend>();
        Friend f1 = new Friend("jeremy", "kepung@gmail.com", "https://cdn2.iconfinder.com/data/icons/picons-essentials/57/user-512.png");
        Friend f2 = new Friend("mark", "theedge@gmail.com", "http://icons.iconseeker.com/png/fullsize/scrap/client.png");
        list.add(f1); list.add(f2);
        return list;
    }
}
