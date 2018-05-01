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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class PostOccupy extends Fragment implements ResponseHandler{

    public static final String POSTOCCUY = "http://10.0.2.2:8080/JobServer/PostOccupation";
    private String occupy,loc,postuserId;
    private List<Work>  workList = new ArrayList<>();
    MainActivity mainActivity;
    CommonRequest res;

    EditText searchocc;
    String str;

     public PostOccupy() {
        this.str="";
    }
     public PostOccupy(String str) {

        this.str = str;
        Log.d("str111",str);
     }
    @Nullable
    @Override


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.postoccypy_fragment,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainActivity = (MainActivity) getActivity();
        postuserId = mainActivity.gerUserId();

        searchocc = mainActivity.findViewById(R.id.searchocc);
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

        new HttpPostTask(mHandler,this,res).execute(POSTOCCUY);

    }

    @Override
    public void success(CommonResponse response) {
        Toast.makeText(getContext(),"数据加载成功",Toast.LENGTH_SHORT).show();
        List<HashMap<String, String>> list = response.getDataList();
        String postName,workLocation,record,resumeNumber,jobDescriptions,posttime,companyType,companyName,resumeId,userId;
        for(int i = 0; i < list.size(); i++) {
            postName = list.get(i).get("postName");
            workLocation = list.get(i).get("workLocation");
            record = list.get(i).get("record");
            resumeNumber = list.get(i).get("resumeNumber");
            posttime = list.get(i).get("posttime");
            companyType = list.get(i).get("companyType");
            companyName = list.get(i).get("companyName");
            resumeId = list.get(i).get("resumeId");
            userId = list.get(i).get("userId");
            jobDescriptions = list.get(i).get("jobDescriptions");
            Work work = new Work(postName,workLocation,resumeNumber,companyType,companyName,posttime,resumeId,userId,jobDescriptions);
            workList.add(work);
        }
        if(!str.equals("")){
            Iterator<Work> e = workList.iterator();
            while (e.hasNext()){
                Work work = e.next();
                if(work.getTitle().indexOf(str) == -1 ) {
                    e.remove();
                }
            }
        }


        WorkAdapter adapter = new WorkAdapter(getActivity(),R.layout.postresume_item,workList);
        ListView listView = getActivity().findViewById(R.id.list_view);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Work work = workList.get(i);
                PostResume.actionStart(getActivity(),work,postuserId);
            }
        });
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void fail(String failCode, String failMsg) {
        Toast.makeText(getContext(),"数据加载失败",Toast.LENGTH_SHORT).show();
    }
}

