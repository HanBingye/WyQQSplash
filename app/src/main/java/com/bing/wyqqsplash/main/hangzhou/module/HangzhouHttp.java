package com.bing.wyqqsplash.main.hangzhou.module;

import com.bing.http.LFHttp;

import java.util.HashMap;
import java.util.Map;

public class HangzhouHttp extends LFHttp {
    public Object getXiaoHua(String sort,String page,String pagesize){
        Map<String ,Object> map=new HashMap<>();

        map.put("sort",sort);
        map.put("page",page);
        map.put("pagesize",pagesize);
        map.put("time",""+System.currentTimeMillis()/1000);
        map.put("key","bbc57dd5e4f05991aff09eafd2e667e0");

        return super.execute(HangzhouRequest.request,map);

    }


}
