package com.example.jobapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Willdetail extends AppCompatActivity {

    TextView name1,phone2,money2,school2,experience;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_willdetail);
        name1 = findViewById(R.id.name1);
        phone2 = findViewById(R.id.phone2);
        money2 = findViewById(R.id.money2);
        school2 = findViewById(R.id.school2);
        experience = findViewById(R.id.experience);
        Intent intent = getIntent();
        name1.setText(intent.getStringExtra("name"));
        phone2.setText(intent.getStringExtra("phone"));
        money2.setText(intent.getStringExtra("money"));
        school2.setText(intent.getStringExtra("school"));
        experience.setText(intent.getStringExtra("experience"));

    }



    public static void actionStart(Context context, String name, String phone,String school,String money,String experience) {
        Intent intent = new Intent(context,Willdetail.class);
        intent.putExtra("name",name);
        intent.putExtra("phone",phone);
        intent.putExtra("school",school);
        intent.putExtra("money",money);
        intent.putExtra("experience",experience);
        context.startActivity(intent);

    }
}
