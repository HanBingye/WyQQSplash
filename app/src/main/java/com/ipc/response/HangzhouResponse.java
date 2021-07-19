package com.ipc.response;

import android.os.RemoteException;

import com.bing.ipc.IClientInterface;
import com.bing.ipc.response.BaseResponse;

public class HangzhouResponse extends BaseResponse {
    public HangzhouResponse(String requestKey, String requestParams, IClientInterface client) {
        super(requestKey, requestParams, client);
    }
    public void hangzhou(){
        try {
            client.callback(requestKey,"来自远方的祝福");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
