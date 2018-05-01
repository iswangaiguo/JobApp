package com.example.jobapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class LoginActivity extends BaseActivity {

    RadioButton rb;
    EditText user;
    EditText pass;
    String userId;
    public static String URL_LANDING = "http://10.0.2.2:8080/JobServer/Landing";
    public static String URL_REGISTER = "http://10.0.2.2:8080/JobServer/Register";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = findViewById(R.id.et_username);
        pass = findViewById(R.id.et_password);
        RadioGroup type = findViewById(R.id.radioGroupId);
        type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int radioButtonId = radioGroup.getCheckedRadioButtonId();
                rb = findViewById(radioButtonId);
            }
        });
        Button landing = findViewById(R.id.login);
        landing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                landing(user.getText().toString(), pass.getText().toString(),rb.getText().toString());
            }
        });

        Button register = findViewById(R.id.btn_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register(user.getText().toString(), pass.getText().toString(),rb.getText().toString());
            }
        });

    }

    private void register(String userId, String pass, String type) {
        Log.d("LoginActivity",user + pass + type);

        CommonRequest request = new CommonRequest();
        request.addRequestParam("userId", userId);
        request.addRequestParam("password", pass);
        request.addRequestParam("type", type);
        sendHttpPostRequest(URL_REGISTER, request, new ResponseHandler() {
            @Override
            public void success(CommonResponse response) {
                Toast.makeText(LoginActivity.this,"注册成功",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void fail(String failCode, String failMsg) {
                Toast.makeText(LoginActivity.this,failMsg,Toast.LENGTH_SHORT).show();
            }
        });



    }


    private void landing(final String userId, String pass, String type){
        Log.d("LoginActivity",user + pass + type);
        this.userId = userId;
        CommonRequest request = new CommonRequest();
        request.addRequestParam("userId", userId);
        request.addRequestParam("password", pass);
        request.addRequestParam("type", type);
        sendHttpPostRequest(URL_LANDING, request, new ResponseHandler() {
            @Override
            public void success(CommonResponse response) {
                Toast.makeText(LoginActivity.this,"登陆成功",Toast.LENGTH_SHORT).show();
                if(rb.getText().toString().equals("个人")) {
                    MainActivity.actionStart(LoginActivity.this, userId);
                } else {
                    Main2Activity.actionStart(LoginActivity.this,userId);
                }

            }

            @Override
            public void fail(String failCode, String failMsg) {
                Toast.makeText(LoginActivity.this,failMsg,Toast.LENGTH_SHORT).show();
            }
        });
    }






}
