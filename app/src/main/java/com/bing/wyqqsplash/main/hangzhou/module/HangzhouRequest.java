package com.bing.wyqqsplash.main.hangzhou.module;

import com.bing.http.annotation.RequestMethod;
import com.bing.http.request.IRequest;
import com.bing.wyqqsplash.base.JHRequest;

public interface HangzhouRequest {
    IRequest request= JHRequest.sendHttp("/joke/content/list.php", RequestMethod.GET);
}
