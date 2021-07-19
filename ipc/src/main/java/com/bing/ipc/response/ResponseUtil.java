package com.bing.ipc.response;

import com.bing.ipc.IClientInterface;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ResponseUtil {
    public static void enqueue(String requestKey, String requestParams, IClientInterface client) {
        StringBuilder stringBuilder = new StringBuilder("com.ipc.response.");
        String first = requestKey.substring(0, 1);
        String second = requestKey.substring(1, requestKey.length());
        stringBuilder.append(first.toUpperCase()).append(second).append("Response");
        try {
            Class<?> clazz = Class.forName(stringBuilder.toString());
            Constructor<?> constructor = clazz.getConstructor(String.class, String.class, IClientInterface.class);
            Method method = clazz.getMethod(requestKey);
            Object o = constructor.newInstance(requestKey, requestParams, client);
            method.invoke(o);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
