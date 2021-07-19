package com.bing.ipc.result;

public class IpcResult implements IResult{

    private boolean success;
    private int code;
    private String data;

    public static IResult getErrorResult(){
        IpcResult result = new IpcResult();
        result.success=false;
        return result;
    }
    public static IResult getSuccessResult(String data){
        IpcResult result = new IpcResult();
        result.success=true;
        result.data=data;
        return result;
    }
    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getData() {
        return data;
    }
}
