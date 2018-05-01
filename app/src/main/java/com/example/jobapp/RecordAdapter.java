package com.example.jobapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecordAdapter extends ArrayAdapter<Record> {

    private int resourceId;

    public RecordAdapter(@NonNull Context context, int resource, @NonNull List<Record> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Record record = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView title1 = view.findViewById(R.id.title1);
        TextView item_location1 = view.findViewById(R.id.item_location1);
        TextView item_postnum1 = view.findViewById(R.id.item_postnum1);
        TextView item_company1 = view.findViewById(R.id.item_company1);
        TextView item_type1 = view.findViewById(R.id.item_type1);
        title1.setText(record.getTitle());
        item_company1.setText(record.getCompanyName());
        item_location1.setText(record.getLocation());
        item_postnum1.setText(record.getNumber());
        item_type1.setText(record.getType());
        return view;

    }
}
