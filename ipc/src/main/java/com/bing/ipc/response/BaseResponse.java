package com.bing.ipc.response;

import com.bing.ipc.IClientInterface;

public class BaseResponse {
   public String requestKey;
   public String requestParams;
   public IClientInterface client;

    public BaseResponse(String requestKey, String requestParams, IClientInterface client) {
        this.requestKey=requestKey;
        this.requestParams=requestParams;
        this.client=client;
    }
}
