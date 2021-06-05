package com.bing.wyqqsplash.base;

import com.bing.http.request.IHost;

public interface HostManager {
    IHost host=new IHost() {

        @Override
        public String getHost() {
            return "http://v.juhe.cn";
        }

        @Override
        public String getDefaultPath() {
            return "/joke/content/list.php";
        }
    };
}
