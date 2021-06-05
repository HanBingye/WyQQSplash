package com.bing.wyqqsplash.base;

import com.bing.http.annotation.RequestMethod;
import com.bing.http.request.IRequest;
import com.bing.http.request.LFRequest;

public class JHRequest extends LFRequest {
    public static IRequest sendHttp(String path, @RequestMethod int requestMethod) {
        JHRequest jhRequest=new JHRequest();
        jhRequest.host= HostManager.host;
        jhRequest.path=path;
        jhRequest.requestMethod=requestMethod;
        return jhRequest;
    }
}
