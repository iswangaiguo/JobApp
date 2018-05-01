package com.example.jobapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

public class MyResume extends BaseActivity {

    public static final String URLRESUME = "http://10.0.2.2:8080/JobServer/PersonalResume";
    EditText name,school,professional,grade,time,phone,domicile,email,workexperience;

    Button edit,keep;

    String userId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myresume);
        Intent intent = getIntent();
        this.userId = intent.getStringExtra("userId");
        init();
        Log.d("MyResume", userId);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setedit();
            }
        });
        keep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonRequest request = new CommonRequest();
                request.setRequestCode("8");
                request.addRequestParam("userId", userId);
                request.addRequestParam("name", name.getText().toString());
                request.addRequestParam("school", school.getText().toString());
                request.addRequestParam("professional", professional.getText().toString());
                request.addRequestParam("grade", grade.getText().toString());
                request.addRequestParam("time", time.getText().toString());
                request.addRequestParam("phone", phone.getText().toString());
                request.addRequestParam("domicile", domicile.getText().toString());
                request.addRequestParam("email", email.getText().toString());
                request.addRequestParam("workexperience", workexperience.getText().toString());
                sendHttpPostRequest(URLRESUME, request, new ResponseHandler() {
                    @Override
                    public void success(CommonResponse response) {
                        Toast.makeText(MyResume.this,"保存成功",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void fail(String failCode, String failMsg) {
                        Toast.makeText(MyResume.this,failMsg,Toast.LENGTH_SHORT).show();
                    }
                });
                setUedit();
            }
        });
    }

    private void setUedit() {
        name.setEnabled(false);
        school.setEnabled(false);
        professional.setEnabled(false);
        grade.setEnabled(false);
        time.setEnabled(false);
        phone.setEnabled(false);
        domicile.setEnabled(false);
        email.setEnabled(false);
        workexperience.setEnabled(false);

    }

    private void setedit() {
        name.setEnabled(true);
        school.setEnabled(true);
        professional.setEnabled(true);
        grade.setEnabled(true);
        time.setEnabled(true);
        phone.setEnabled(true);
        domicile.setEnabled(true);
        email.setEnabled(true);
        workexperience.setEnabled(true);

    }


    private void init() {
        name = findViewById(R.id.name);
        school = findViewById(R.id.school);
        professional = findViewById(R.id.professional);
        grade = findViewById(R.id.grade);
        time = findViewById(R.id.time);
        phone = findViewById(R.id.phone);
        domicile = findViewById(R.id.domicile);
        email = findViewById(R.id.email);
        workexperience = findViewById(R.id.workexperience);
        edit = findViewById(R.id.edit);
        keep = findViewById(R.id.keep);

        CommonRequest request = new CommonRequest();
        request.setRequestCode("9");
        request.addRequestParam("userId",userId);
        sendHttpPostRequest(URLRESUME, request, new ResponseHandler() {
            @Override
            public void success(CommonResponse response) {
                Toast.makeText(MyResume.this,"数据加载成功",Toast.LENGTH_SHORT).show();
                Map<String, String> map = response.getPropertyMap();
                name.setText(map.get("name"));
                school.setText(map.get("school"));
                professional.setText(map.get("professional"));
                grade.setText(map.get("grade"));
                time.setText(map.get("time"));
                phone.setText(map.get("phone"));
                domicile.setText(map.get("domicile"));
                email.setText(map.get("email"));
                workexperience.setText(map.get("workexperience"));
                setUedit();
            }

            @Override
            public void fail(String failCode, String failMsg) {
                Toast.makeText(MyResume.this,failMsg,Toast.LENGTH_SHORT).show();
            }
        });
    }
    public static void actionStart(Context context, String userId) {
        Intent intent = new Intent(context,MyResume.class);
        intent.putExtra("userId",userId);
        context.startActivity(intent);

    }
}
