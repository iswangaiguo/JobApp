package com.example.jobapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Myinformation1 extends Fragment{

    Main2Activity activity;
    String userId ;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myinformation1_fragment, container, false);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button myinfo = getActivity().findViewById(R.id.myinfo);
        Button post = getActivity().findViewById(R.id.post);
        Button postrecord = getActivity().findViewById(R.id.postrecord);
        activity = (Main2Activity) getActivity();
        myinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userId = activity.gerUserId();
                Log.d("Myinformation1", userId);
                Companyinfo.actionStart(activity,userId);
            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userId = activity.gerUserId();
                String userId = activity.gerUserId();
                ReleasePosition.actionStart(activity,userId);
            }
        });
        postrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userId = activity.gerUserId();
                PostRecord.actionStar(activity,userId);
            }
        });

    }


}
