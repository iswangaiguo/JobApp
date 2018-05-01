package com.example.jobapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PostResume extends BaseActivity{

    public static final String ADDPOSTRESUME= "http://10.0.2.2:8080/JobServer/Addpostresume";
    Work work;
    String title,location,type,resumeId,userId,num,description,postuserId;
    TextView title1,local1 ,record, postnum1, descrip;
    Button postresume;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posrresume);
        init();
        postresume = findViewById(R.id.post1);
        postresume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonRequest request = new CommonRequest();
                request.addRequestParam("resumeId",resumeId);
                request.addRequestParam("userId",postuserId);
                sendHttpPostRequest(ADDPOSTRESUME, request, new ResponseHandler() {
                    @Override
                    public void success(CommonResponse response) {
                        Toast.makeText(PostResume.this,"投递成功",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void fail(String failCode, String failMsg) {
                        Toast.makeText(PostResume.this,"投递失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    private void init() {
        Intent intent = getIntent();
        this.title = intent.getStringExtra("title");
        this.type = intent.getStringExtra("type");
        this.location = intent.getStringExtra("location");
        this.num = intent.getStringExtra("num");
        this.resumeId = intent.getStringExtra("reusmeId");
        this.userId = intent.getStringExtra("userId");
        this.description = intent.getStringExtra("description");
        this.postuserId = intent.getStringExtra("postuserId");
        title1 = findViewById(R.id.title1);
        local1 = findViewById(R.id.local1);
        record = findViewById(R.id.record);
        postnum1 = findViewById(R.id.postnum1);
        descrip = findViewById(R.id.descrip);
        title1.setText(title);
        local1.setText(location);
        record.setText(type);
        postnum1.setText(num);
        descrip.setText(description);

    }


    public static void actionStart(Context context, Work work,String postuserId){
        Intent intent = new Intent(context,PostResume.class);
        intent.putExtra("title",work.getTitle());
        intent.putExtra("location",work.getItem_location());
        intent.putExtra("type",work.getItem_type());
        intent.putExtra("reusmeId",work.getResumeId());
        intent.putExtra("userId",work.getUserId());
        intent.putExtra("num",work.getItem_postnum());
        intent.putExtra("description",work.getJobdescrip());
        intent.putExtra("postuserId",postuserId);
        context.startActivity(intent);



    }
}
