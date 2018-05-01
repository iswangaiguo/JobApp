package com.example.jobapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReleasePosition extends BaseActivity {

    public static final String POSTURL = "http://10.0.2.2:8080/JobServer/PostResume";
    String userId;

    EditText postname, worklocation, education, postnum, workintroduction;
    Button postresume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_position);
        Intent intent = getIntent();
        this.userId = intent.getStringExtra("userId");
        init();
        postresume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonRequest request = new CommonRequest();
                request.addRequestParam("userId",userId);
                request.addRequestParam("postname",postname.getText().toString());
                request.addRequestParam("worklocation",worklocation.getText().toString());
                request.addRequestParam("education",education.getText().toString());
                request.addRequestParam("postnum",postnum.getText().toString());
                request.addRequestParam("workintroduction",workintroduction.getText().toString());

                sendHttpPostRequest(POSTURL, request, new ResponseHandler() {
                    @Override
                    public void success(CommonResponse response) {
                        Toast.makeText(ReleasePosition.this,"发布成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void fail(String failCode, String failMsg) {
                        Toast.makeText(ReleasePosition.this,"发布失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



    }

    private void init() {
        postname = findViewById(R.id.postname);
        worklocation = findViewById(R.id.worklocation);
        education = findViewById(R.id.education);
        postnum = findViewById(R.id.postnum);
        workintroduction = findViewById(R.id.workintroduction);
        postresume = findViewById(R.id.postrusume);

    }

    public static void actionStart(Context context, String userId) {
        Intent intent = new Intent(context, ReleasePosition.class);
        intent.putExtra("userId", userId);
        context.startActivity(intent);
    }
}
