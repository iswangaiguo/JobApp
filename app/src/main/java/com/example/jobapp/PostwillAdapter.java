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

public class PostwillAdapter extends ArrayAdapter<Postwill> {
    private int resourceId;
    public PostwillAdapter(@NonNull Context context, int resource, @NonNull List<Postwill> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Postwill postwill = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView title = view.findViewById(R.id.title3);
        TextView item_money = view.findViewById(R.id.item_money);
        TextView item_locat = view.findViewById(R.id.item_locat);
        TextView item_name = view.findViewById(R.id.item_name);
        TextView item_profess = view.findViewById(R.id.item_profess);
        TextView item_time = view.findViewById(R.id.item_time);
        title.setText(postwill.getWillname());
        item_money.setText(postwill.getMoney());
        item_locat.setText(postwill.getLocat());
        item_name.setText(postwill.getUserName());
        item_profess.setText(postwill.getProfessional());
        item_time.setText(postwill.getWilldata());
        return view;
    }
}
