package com.example.jobapp;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpPostTask extends AsyncTask<String, String, String> {
    /**
     * BaseActivity 中基础问题的处理 handler
     */
    private Handler mHandler;

    /**
     * 返回信息处理回调接口
     */
    private ResponseHandler rHandler;

    /**
     * 请求类对象
     */
    private CommonRequest request;

    public HttpPostTask(Handler mHandler, ResponseHandler rHandler, CommonRequest request) {
        this.mHandler = mHandler;
        this.rHandler = rHandler;
        this.request = request;
    }


    @Override
    protected String doInBackground(String... params) {
        StringBuilder resultBuf = new StringBuilder();
        try {
            URL url = new URL(params[0]);

            // 第一步：使用URL打开一个HttpURLConnection连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-type", "application/json;charset=UTF-8");
            // 第二步：设置HttpURLConnection连接相关属性
         //   connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");

            connection.setRequestMethod("POST"); // 设置请求方法，“POST或GET”
            connection.setConnectTimeout(8000); // 设置连接建立的超时时间
            connection.setReadTimeout(8000); // 设置网络报文收发超时时间
            connection.setDoOutput(true);
            connection.setDoInput(true);

            // 如果是POST方法，需要在第3步获取输入流之前向连接写入POST参数
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.write(request.getJsonStr().getBytes());
            out.flush();

            // 第三步：打开连接输入流读取返回报文 -> *注意*在此步骤才真正开始网络请求
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // 通过连接的输入流获取下发报文，然后就是Java的流处理
                InputStream in = connection.getInputStream();
                BufferedReader read = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = read.readLine()) != null) {
                    resultBuf.append(line);
                }
                return resultBuf.toString();
            } else {
                // 异常情况，如404/500...
                mHandler.obtainMessage(Constant.HANDLER_HTTP_RECEIVE_FAIL,
                        "[" + responseCode + "]" + connection.getResponseMessage()).sendToTarget();
            }
        } catch (IOException e) {
            // 网络请求过程中发生IO异常
            mHandler.obtainMessage(Constant.HANDLER_HTTP_SEND_FAIL,
                    e.getClass().getName() + " : " + e.getMessage()).sendToTarget();
        }
        return resultBuf.toString();

    }


    @Override
    protected void onPostExecute(String result) {
        if (rHandler != null) {
            if (!"".equals(result)) {
                CommonResponse response = new CommonResponse(result);
                // 这里response.getResCode()为多少是和服务器约定好的
                if ("0".equals(response.getResCode())) { // 正确
                    rHandler.success(response);
                } else {
                    rHandler.fail(response.getResCode(), response.getResMsg());
                    Log.d("HttpPostTask", response.getResCode()+response.getResMsg());
                }
            }
        }
    }
}

