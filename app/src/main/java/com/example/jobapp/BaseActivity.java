package com.example.jobapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void sendHttpPostRequest(String url, CommonRequest request, ResponseHandler responseHandler) {
        new HttpPostTask(mHandler,responseHandler,request).execute(url);
    }

    protected Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == Constant.HANDLER_HTTP_SEND_FAIL) {
                Log.e("BaseActivity",msg.obj.toString());
                Toast.makeText(BaseActivity.this,"请求发送失败，请重试",Toast.LENGTH_SHORT).show();
            } else if (msg.what == Constant.HANDLER_HTTP_RECEIVE_FAIL) {
                Log.e("BaseActivity",msg.obj.toString());
                Toast.makeText(BaseActivity.this,"请求接收失败，请重试",Toast.LENGTH_SHORT).show();
            }
        }
    };
}
