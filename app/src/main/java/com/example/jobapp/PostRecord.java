package com.example.jobapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PostRecord extends BaseActivity{

    public static final String POSTRECORD = "http://10.0.2.2:8080/JobServer/PostRecord";
    private List<Person>  personallist = new ArrayList<>();
    String userId;
    String name,school,professional,grade,time,phone,domicile,email,workexperience;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postrecord);
        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");
        CommonRequest request = new CommonRequest();
        request.addRequestParam("userId",userId);
        Log.d("PostRecord", userId);
        sendHttpPostRequest(POSTRECORD, request, new ResponseHandler() {
            @Override
            public void success(CommonResponse response) {
                List<HashMap<String, String>> list = response.getDataList();

                for(int i = 0; i < list.size(); i++) {
                    name = list.get(i).get("name");
                    school = list.get(i).get("school");
                    domicile = list.get(i).get("domicile");
                    phone = list.get(i).get("phone");
                    workexperience = list.get(i).get("workexperience");
                    Person person = new Person(name,domicile,school,phone,workexperience);
                    personallist.add(person);
                }
                PersonAdapter adapter = new PersonAdapter(PostRecord.this,R.layout.postrecord_item,personallist);
                ListView listView = findViewById(R.id.list_view1);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        PersonalDetail.actionStart(PostRecord.this,workexperience);
                    }
                });
                listView.setAdapter(adapter);
            }

            @Override
            public void fail(String failCode, String failMsg) {
                Toast.makeText(PostRecord.this,"查询失败",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void actionStar(Context context,String userId) {
        Intent intent = new Intent(context,PostRecord.class);
        intent.putExtra("userId",userId);
        context.startActivity(intent);

    }
}
