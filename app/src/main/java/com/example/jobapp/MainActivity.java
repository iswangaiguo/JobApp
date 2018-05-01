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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button occupation = findViewById(R.id.occupation);

        Button my = findViewById(R.id.myinformation);
        occupation.setOnClickListener(this);

        my.setOnClickListener(this);
        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");
        replaceFragment(new occupation());

    }

    public String gerUserId() {
        Log.d("MainActivity", this.userId);
        return this.userId;

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.occupation:
                replaceFragment(new occupation());
                break;
            case R.id.myinformation:
                replaceFragment(new Myinformation());
                break;
        }
    }

    public static void actionStart(Context context, String userId) {
        Intent intent = new Intent(context,MainActivity.class);
        intent.putExtra("userId",userId);
        context.startActivity(intent);

    }

    private void replaceFragment(android.support.v4.app.Fragment fragment){
       FragmentManager fragmentManager = getSupportFragmentManager();
       FragmentTransaction transaction = fragmentManager.beginTransaction();
       transaction.replace(R.id.empty,fragment);
       transaction.commit();
    }
}
