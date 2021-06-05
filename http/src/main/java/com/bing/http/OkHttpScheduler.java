package com.bing.http;

import com.bing.http.annotation.RequestMethod;
import com.bing.http.request.IRequest;

import java.util.Iterator;
import java.util.Map;

import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class OkHttpScheduler extends HttpScheduler {
    private OkHttpClient client;

    @Override
    public ICall newCall(IRequest iRequest) {
        Map<String, Object> map = iRequest.getMap();
        int requestMethod = iRequest.getRequestMethod();
        Request.Builder requestBuilder = new Request.Builder();
        switch (requestMethod){
            case RequestMethod.GET:
                StringBuilder stringBuilder=new StringBuilder(iRequest.getIHost().getHost());
                stringBuilder.append(iRequest.getPath());
                HttpUrl.Builder urlBuilder = HttpUrl.parse(stringBuilder.toString()).newBuilder();

                if(map!=null&&map.size()>0){
                    Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
                    while (iterator.hasNext()){
                        Map.Entry<String, Object> next = iterator.next();
                        // TODO: 2021/5/18
                        urlBuilder.addQueryParameter(next.getKey(), String.valueOf(next.getValue()));
                    }
                }
                requestBuilder.get().url(urlBuilder.build());
                break;
                case RequestMethod.POST:

                    break;
        }
        Request request = requestBuilder.build();
        Call call = getClient().newCall(request);
        OkHttpCall okHttpCall=new  OkHttpCall(iRequest,call);
        return okHttpCall;
    }



    private OkHttpClient getClient() {
        if(client==null){
            client=new OkHttpClient();
        }
        return client;
    }
}
