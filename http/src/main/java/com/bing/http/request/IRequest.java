package com.bing.http.request;

import java.util.Map;

public interface IRequest {

    void setMap(Map<String, Object> map);
    Map<String, Object> getMap();
    int getRequestMethod();
    IHost getIHost();
    String getPath();
}
