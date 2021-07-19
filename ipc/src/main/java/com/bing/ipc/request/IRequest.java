package com.bing.ipc.request;

import com.bing.ipc.Callback;

public interface IRequest extends Comparable<IRequest>{
    void setParams(String params);

    String getParams();

    String getRequestKey();

    void addCallback(Callback callback);

    Callback getCallback();

    long getTime();
}
