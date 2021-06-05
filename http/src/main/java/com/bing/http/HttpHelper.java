package com.bing.http;

import com.bing.http.request.IRequest;

import java.util.Map;

public class HttpHelper {
    private volatile static HttpScheduler httpScheduler;

    public static HttpScheduler getHttpScheduler() {
        if(httpScheduler==null){
            synchronized (HttpHelper.class){
                if(httpScheduler==null){
                    httpScheduler=new OkHttpScheduler();
                }
            }
        }
        return httpScheduler;
    }

    protected static Object execute(IRequest iRequest, Map<String,Object> map){
        iRequest.setMap(map);
        ICall call=getHttpScheduler().newCall(iRequest);
       Object object= getHttpScheduler().execute(call);
        return object;
    }
}
