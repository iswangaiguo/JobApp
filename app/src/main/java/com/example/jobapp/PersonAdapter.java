package com.example.jobapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class PersonAdapter extends ArrayAdapter<Person> {
    private int resourceId;

    public PersonAdapter(@NonNull Context context, int resource, @NonNull List<Person> objects){
        super(context, resource, objects);
        resourceId = resource;

    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Person person = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView username = view.findViewById(R.id.username);
        TextView school1 = view.findViewById(R.id.school1);
        TextView local2 = view.findViewById(R.id.local2);
        TextView phone1 = view.findViewById(R.id.phone1);
        username.setText(person.getName());
        school1.setText(person.getSchool());
        local2.setText(person.getLocal());
        phone1.setText(person.getPhone());
        return view;
    }
}
