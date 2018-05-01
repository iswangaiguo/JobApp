package com.example.jobapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Myinformation extends Fragment {

    MainActivity activity;
    String userId;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myinformation_fragment,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button myresume = getActivity().findViewById(R.id.myresume);
        activity = (MainActivity) getActivity();
        myresume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userId = activity.gerUserId();
                Log.d("Myinformation", userId);
                MyResume.actionStart(activity,userId);
            }
        });
        Button myrecord = getActivity().findViewById(R.id.myrecord);
        myrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userId = activity.gerUserId();
                Myrecord.actionStart(activity,userId);
            }
        });
        Button postwill = getActivity().findViewById(R.id.postwill);
        postwill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userId = activity.gerUserId();
                Mypostwill.actionStar(activity,userId);
            }
        });


    }
}
