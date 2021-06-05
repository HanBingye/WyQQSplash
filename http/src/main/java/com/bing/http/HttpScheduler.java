package com.bing.http;

import com.bing.http.request.IRequest;

public abstract class HttpScheduler {
    public abstract ICall newCall(IRequest iRequest);

    public  Object execute(ICall call){
        return call.execute();
    }
}
