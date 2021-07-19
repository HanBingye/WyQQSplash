package com.bing.ipc.result;

public interface IResult {
    boolean isSuccess();

    int getCode();

    String getData();
}
