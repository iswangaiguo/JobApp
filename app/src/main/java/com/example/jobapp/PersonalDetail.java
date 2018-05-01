package com.example.jobapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PersonalDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_detail);
        TextView detail = findViewById(R.id.detail);
        Intent intent = getIntent();
        String string = intent.getStringExtra("detail");
        detail.setText(string);
    }

    public static void actionStart(Context context, String detail) {
        Intent intent = new Intent(context,PersonalDetail.class);
        intent.putExtra("detail",detail);
        context.startActivity(intent);

    }
}
