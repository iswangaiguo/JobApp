package com.example.jobapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Myrecord extends BaseActivity {

    public static final String MYRECORDPATH = "http://10.0.2.2:8080/JobServer/Myreord";
    String userId;
    private List<Record> myrecord = new ArrayList<>();
    String title,location,number,type,companyName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myrecord);
        userId = getIntent().getStringExtra("userId");
        CommonRequest request = new CommonRequest();
        request.addRequestParam("userId",userId);
        sendHttpPostRequest(MYRECORDPATH, request, new ResponseHandler() {
            @Override
            public void success(CommonResponse response) {
                List<HashMap<String,String>> list = response.getDataList();
                for (int i = 0; i < list.size(); i++) {
                    title = list.get(i).get("title");
                    location = list.get(i).get("worklocation");
                    type = list.get(i).get("companyType");
                    companyName = list.get(i).get("companyName");
                    number = list.get(i).get("resumeNumber");
                    Record record = new Record(title,location,number,type,companyName);
                    myrecord.add(record);
                }
                RecordAdapter recordAdapter = new RecordAdapter(Myrecord.this,R.layout.myrecord_item,myrecord);
                ListView listView = findViewById(R.id.list_view2);
                listView.setAdapter(recordAdapter);

            }

            @Override
            public void fail(String failCode, String failMsg) {
                Toast.makeText(Myrecord.this,"查询失败",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public static void actionStart(Context context, String userId) {
        Intent intent = new Intent(context,Myrecord.class);
        intent.putExtra("userId",userId);
        context.startActivity(intent);
    }
}
