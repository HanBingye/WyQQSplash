package com.bing.ipc.request;

import androidx.annotation.NonNull;

import com.bing.ipc.Callback;

public class IpcRequest implements IRequest {
    private long time;
    private String requestKey;
    private String params;
    private Callback callback;

    public IpcRequest() {
        time = System.currentTimeMillis();
    }

    public IpcRequest(@NonNull String requestKey) {
        this.requestKey = requestKey;
    }

    @Override
    public void setParams(@NonNull String params) {
        this.params = params;
    }

    @Override
    public String getParams() {
        return params;
    }

    @Override
    public String getRequestKey() {
        return requestKey;
    }

    @Override
    public void addCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public Callback getCallback() {
        return callback;
    }

    @Override
    public long getTime() {
        return time;
    }


    @Override
    public int compareTo(IRequest o) {
        return (int) (this.getTime() - o.getTime());
    }
}
