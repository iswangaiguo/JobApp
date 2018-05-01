package com.example.jobapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Mypostwill extends BaseActivity {

    public static final String MYPOSTWILL = "http://10.0.2.2:8080/JobServer/Mypostwill";
    String userId;
    EditText resumewill,moneywill,locatwill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userId = getIntent().getStringExtra("userId");
        setContentView(R.layout.activity_mypostwill);
        resumewill = findViewById(R.id.resumewill);
        moneywill = findViewById(R.id.moneywill);
        locatwill = findViewById(R.id.locatwill);
        Button post = findViewById(R.id.postwill);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonRequest request = new CommonRequest();
                request.addRequestParam("userId",userId);
                request.addRequestParam("resumewill",resumewill.getText().toString());
                request.addRequestParam("moneywill",moneywill.getText().toString());
                request.addRequestParam("locatwill",locatwill.getText().toString());
                sendHttpPostRequest(MYPOSTWILL, request, new ResponseHandler() {
                    @Override
                    public void success(CommonResponse response) {
                        Toast.makeText(Mypostwill.this,"发布成功",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void fail(String failCode, String failMsg) {
                        Toast.makeText(Mypostwill.this,"发布失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    public static void actionStar(Context context, String userId) {
        Intent intent = new Intent(context,Mypostwill.class);
        intent.putExtra("userId",userId);
        context.startActivity(intent);

    }
}
