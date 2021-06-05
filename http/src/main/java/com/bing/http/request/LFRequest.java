package com.bing.http.request;

import com.bing.http.annotation.RequestMethod;

import java.util.Map;

public class LFRequest implements IRequest {
    protected Map<String, Object> map;
    protected IHost host;
    protected @RequestMethod int requestMethod;
    protected String path;


    @Override
    public void setMap(Map<String, Object> map) {
        this.map=map;
    }

    @Override
    public Map<String, Object> getMap() {
        return map;
    }

    @Override
    public int getRequestMethod() {
        return requestMethod;
    }

    @Override
    public IHost getIHost() {
        return host;
    }

    @Override
    public String getPath() {
        return path;
    }

}
