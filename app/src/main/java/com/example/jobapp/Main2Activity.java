package com.example.jobapp;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{

    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button jobhunter = findViewById(R.id.jobhunter);
        Button myinformation = findViewById(R.id.myinformation1);
        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");
        replaceFragment(new JobHunter());
        jobhunter.setOnClickListener(this);
        myinformation.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.jobhunter:
                replaceFragment(new JobHunter());
                break;
            case R.id.myinformation1:
                replaceFragment(new Myinformation1());
        }


    }

    public String gerUserId() {
        Log.d("Main2Activity", this.userId);
        return this.userId;

    }

    public static void actionStart(Context context, String userId) {
        Intent intent = new Intent(context,Main2Activity.class);
        intent.putExtra("userId",userId);
        context.startActivity(intent);

    }

    private void replaceFragment(android.support.v4.app.Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.empty1,fragment);
        transaction.commit();
    }
}
