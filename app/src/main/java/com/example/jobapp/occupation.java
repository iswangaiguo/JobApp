package com.example.jobapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class occupation extends Fragment implements ResponseHandler{

    EditText searchocc;
    Button sure;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.occupation_fragment,container,false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sure = getActivity().findViewById(R.id.sure);
        searchocc = getActivity().findViewById(R.id.searchocc);
        replaceFragment(new PostOccupy());
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = searchocc.getText().toString();
                replaceFragment(new PostOccupy(str));
            }
        });
    }

    @Override
    public void success(CommonResponse response) {

    }

    @Override
    public void fail(String failCode, String failMsg) {

    }

    private void replaceFragment(android.support.v4.app.Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.occupation1,fragment);
        transaction.commit();
    }
}

