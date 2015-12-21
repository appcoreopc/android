package com.example.jeremy.myapplication;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jeremy.myapplication.FriendModule.Friend;
import com.example.jeremy.myapplication.FriendModule.FriendsAdapter;
import com.example.jeremy.myapplication.MessageModule.MessageAdapter;
import com.example.jeremy.myapplication.MessageModule.MessageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeremy on 20/12/2015.
 */
public class MessageFragment extends Fragment {

    private RecyclerView recyclerView;

    public MessageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_message, container, false);
        // Inflate the layout for this fragment

        recyclerView = (RecyclerView) layout.findViewById(R.id.messagelist);
        MessageAdapter adapter = new MessageAdapter(getActivity(), getFakeFriendInfo());
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


    private List<MessageInfo> getFakeFriendInfo()
    {
        List<MessageInfo> list = new ArrayList<MessageInfo>();
        MessageInfo f1 = new MessageInfo("Sunset", "A beautiful castle around the lake transpire tranquility", "http://www.goodlightscraps.com/content/nature/nature-images-67.jpg");
        MessageInfo f2 = new MessageInfo("Waterfall", "The beautiful sunset in the grand canyon.", "http://www.goodlightscraps.com/content/nature/nature-images-64.jpg");
        list.add(f1); list.add(f2);
        return list;
    }

}
