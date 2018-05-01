package com.example.jobapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Jobhunter1 extends Fragment implements ResponseHandler{

    public static final String JOBHUNTER = "http://10.0.2.2:8080/JobServer/Jobhunter";
    private List<Postwill>  postwillList = new ArrayList<>();
    CommonRequest res;

    String hunter;

    public Jobhunter1() {
        this.hunter = "";
    }

    public Jobhunter1(String hunter) {
        this.hunter = hunter;

    }


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.jobhunter1_fragment, container, false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        res = new CommonRequest();
        Handler mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if(msg.what == Constant.HANDLER_HTTP_SEND_FAIL) {
                    Log.e("BaseActivity",msg.obj.toString());
                    Toast.makeText(getContext(),"请求发送失败，请重试",Toast.LENGTH_SHORT).show();
                } else if (msg.what == Constant.HANDLER_HTTP_RECEIVE_FAIL) {
                    Log.e("BaseActivity",msg.obj.toString());
                    Toast.makeText(getContext(),"请求接收失败，请重试",Toast.LENGTH_SHORT).show();
                }
            }
        };

        new HttpPostTask(mHandler,this,res).execute(JOBHUNTER);

    }

    @Override
    public void success(CommonResponse response) {
        List<HashMap<String, String>> list = response.getDataList();
        String userId,userName,willname,locat,money,willdata,phone,school,professional,workexperience;
        for(int i = 0; i < list.size(); i++){
            userId = list.get(i).get("userId");
            userName = list.get(i).get("userName");
            willname = list.get(i).get("willname");
            locat = list.get(i).get("locat");
            money = list.get(i).get("money");
            willdata = list.get(i).get("willdata");
            phone = list.get(i).get("phone");
            school = list.get(i).get("school");
            professional = list.get(i).get("professional");
            workexperience = list.get(i).get("workexperience");
            Postwill postwill = new Postwill(userId, userName, willname,locat, money, willdata, phone,  school, professional,workexperience);
            postwillList.add(postwill);
        }

        if(!hunter.equals("")){
            Iterator<Postwill> e = postwillList.iterator();
            while (e.hasNext()){
                Postwill postwill = e.next();
                if(postwill.getWillname().indexOf(hunter) == -1 ) {
                    e.remove();
                }
            }
        }
        PostwillAdapter postwillAdapter = new PostwillAdapter(getActivity(),R.layout.postwill_item,postwillList);
        ListView listView = getActivity().findViewById(R.id.list_view3);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Postwill postwill = postwillList.get(i);
                Willdetail.actionStart(getContext(),postwill.getUserName(),postwill.getPhone(),postwill.getSchool(),postwill.getMoney(),postwill.getWorkexperience());
            }
        });
        listView.setAdapter(postwillAdapter);
        postwillAdapter.notifyDataSetChanged();


    }

    @Override
    public void fail(String failCode, String failMsg) {

    }
}
