package com.example.jeremy.myapplication.Notification;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.jeremy.myapplication.R;

public class SnackBarNotificationFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.snackbar_notification, container, false);
        Button bottomSnackBarBtn = (Button) layout.findViewById(R.id.bottomSnackBarBtn);

        final CoordinatorLayout snackbarLayout = (CoordinatorLayout) layout.findViewById(R.id.notificationSnackbarToplocation);
        Button topSnackBarBtn = (Button) layout.findViewById(R.id.topSnackBarBtn);

        topSnackBarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (snackbarLayout != null)
                Snackbar.make(snackbarLayout, "Top Snackbar notification", Snackbar.LENGTH_SHORT).show();
            }
        });

        bottomSnackBarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Bottom Snackbar notification", Snackbar.LENGTH_SHORT).show();
            }
        });
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
}