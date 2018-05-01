package com.example.jobapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;



public class JobHunter extends Fragment {

    EditText searchhunter;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.jobhunter_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button search = getActivity().findViewById(R.id.search1);
        searchhunter = getActivity().findViewById(R.id.searchhunter);
        replaceFragment(new Jobhunter1());
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              replaceFragment(new Jobhunter1(searchhunter.getText().toString()));
            }
        });
    }


    private void replaceFragment(android.support.v4.app.Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.jobhunter,fragment);
        transaction.commit();
    }

}
