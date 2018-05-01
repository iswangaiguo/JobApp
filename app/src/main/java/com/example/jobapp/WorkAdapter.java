package com.example.jobapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class WorkAdapter extends ArrayAdapter<Work> {

    private int resourceId;
    public WorkAdapter(@NonNull Context context, int resource, @NonNull List<Work> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Work work = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView title = view.findViewById(R.id.title);
        TextView item_location = view.findViewById(R.id.item_location);
        TextView item_postnum = view.findViewById(R.id.item_postnum);
        TextView item_type = view.findViewById(R.id.item_type);
        TextView item_company = view.findViewById(R.id.item_company);
        TextView posttime = view.findViewById(R.id.posttime);
        title.setText(work.getTitle());
        item_location.setText(work.getItem_location());
        item_postnum.setText(work.getItem_postnum());
        item_type.setText(work.getItem_type());
        item_company.setText(work.getItem_company());
        posttime.setText(work.getPosttime());
        return view;
    }
}
