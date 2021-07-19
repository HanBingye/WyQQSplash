package com.bing.ipc.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import com.bing.ipc.IClientInterface;
import com.bing.ipc.IServiceInterface;
import com.bing.ipc.response.ResponseUtil;

public class IpcService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new IServiceInterface.Stub() {
            private IClientInterface client;

            @Override
            public void enqueue(String requestKey, String requestParams) throws RemoteException {
                ResponseUtil.enqueue(requestKey,requestParams,client);
            }

            @Override
            public String execute(String requestKey, String requestParams) throws RemoteException {
                String result="";
                switch (requestKey){
                    case "hangzhou_detail":
                       result="来自远方祝福2";
                      break;
                    default:
                        break;
                }
                return result;
            }

            @Override
            public void registerCallback(IClientInterface client) throws RemoteException {
                this.client=client;

            }
        };
    }
}
