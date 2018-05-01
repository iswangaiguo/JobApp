package com.example.jobapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;

public class Companyinfo extends BaseActivity {

    public static final String COMPANYINFO ="http://10.0.2.2:8080/JobServer/Companyinfo";
    EditText companyname,companylocate,companyphone,companytype,companyintroduction;

    Button edit1,keep1;
    String userId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companyinfo);
        Intent intent = getIntent();
        this.userId = intent.getStringExtra("userId");
        init();
        edit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setedit();
            }
        });
        keep1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonRequest request = new CommonRequest();
                request.setRequestCode("11");
                request.addRequestParam("userId", userId);
                request.addRequestParam("companyName", companyname.getText().toString());
                request.addRequestParam("companyLocate", companylocate.getText().toString());
                request.addRequestParam("phone", companyphone.getText().toString());
                request.addRequestParam("companyType", companytype.getText().toString());
                request.addRequestParam("companyIntroduction", companyintroduction.getText().toString());

                sendHttpPostRequest(COMPANYINFO, request, new ResponseHandler() {
                    @Override
                    public void success(CommonResponse response) {
                        Toast.makeText(Companyinfo.this,"保存成功",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void fail(String failCode, String failMsg) {
                        Toast.makeText(Companyinfo.this,failMsg,Toast.LENGTH_SHORT).show();
                    }
                });
                setUedit();
            }
        });


    }

    public static void actionStart(Context context, String userId) {
        Intent intent = new Intent(context, Companyinfo.class);
        intent.putExtra("userId", userId);
        context.startActivity(intent);
    }

    private void init() {
        companyname = findViewById(R.id.companyname);
        companylocate = findViewById(R.id.companylocate);
        companyphone = findViewById(R.id.companyphone);
        companytype = findViewById(R.id.companytype);
        companyintroduction = findViewById(R.id.companyintroduction);
        edit1 = findViewById(R.id.edit1);
        keep1 = findViewById(R.id.keep1);
        CommonRequest request = new CommonRequest();
        request.setRequestCode("10");
        request.addRequestParam("userId",userId);
        sendHttpPostRequest(COMPANYINFO, request, new ResponseHandler() {
            @Override
            public void success(CommonResponse response) {
                Toast.makeText(Companyinfo.this,"数据加载成功",Toast.LENGTH_SHORT).show();
                Map<String, String> map = response.getPropertyMap();
                companyname.setText(map.get("companyName"));
                companylocate.setText(map.get("Location"));
                companyphone.setText(map.get("phone"));
                companytype.setText(map.get("companyType"));
                companyintroduction.setText(map.get("companyintroduce"));

            }

            @Override
            public void fail(String failCode, String failMsg) {
                Toast.makeText(Companyinfo.this,failMsg,Toast.LENGTH_SHORT).show();
            }
        });
        setUedit();
    }

    private void setUedit() {
        companyname.setEnabled(false);
        companylocate.setEnabled(false);
        companyphone.setEnabled(false);
        companytype.setEnabled(false);
        companyintroduction.setEnabled(false);

    }
    private void setedit() {
        companyname.setEnabled(true);
        companylocate.setEnabled(true);
        companyphone.setEnabled(true);
        companytype.setEnabled(true);
        companyintroduction.setEnabled(true);

    }



}
