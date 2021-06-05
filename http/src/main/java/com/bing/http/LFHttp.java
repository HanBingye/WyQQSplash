package com.bing.http;

import com.bing.http.request.IRequest;

import java.util.Map;

public class LFHttp {
    protected Object execute(IRequest iRequest, Map<String,Object> map){
        return HttpHelper.execute(iRequest,map);
    }
}
