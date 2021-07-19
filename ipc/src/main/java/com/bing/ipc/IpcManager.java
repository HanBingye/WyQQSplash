package com.bing.ipc;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;

import com.bing.ipc.request.IRequest;
import com.bing.ipc.result.IResult;
import com.bing.ipc.result.IpcResult;
import com.bing.ipc.service.IpcService;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class IpcManager {
    private static IpcManager mInstance;
    private Context context;
    //mSets mWaitSets只用于异步请求
    private Set<IRequest> mSets = new TreeSet<IRequest>();
    private Set<IRequest> mWaitSets = new TreeSet<IRequest>();
    private int connectStatus = IConnectStatus.SERVICE_UNBIND;
    private ServiceConnection serviceConnect;
    private IServiceInterface ServiceInterface;
    private IBinder.DeathRecipient deathRecipient;
    private IClientInterface.Stub ClientInterface;

    interface IConnectStatus {
        int SERVICE_UNBIND = 1;
        int SERVICE_BINGING = 2;
        int SERVICE_BIND = 3;
    }

    public IpcManager(Context context) {
        this.context = context.getApplicationContext();
    }

    public synchronized static IpcManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new IpcManager(context);
        }
        return mInstance;
    }

    //异步跨进程通信
    public void enqueue(IRequest iRequest, Callback callback) {
        if (TextUtils.isEmpty(iRequest.getRequestKey()) || mSets.contains(iRequest)) {
            callback.callback(IpcResult.getErrorResult());
            return;
        }
        iRequest.addCallback(callback);
        mSets.add(iRequest);
        if (connectStatus != IConnectStatus.SERVICE_BIND) {
            connectService();
            mWaitSets.add(iRequest);
            return;
        }
        handleType(iRequest);


    }

    //同步跨进程通信
    public IResult execute(IRequest iRequest) {
        if (TextUtils.isEmpty(iRequest.getRequestKey())) {

            return IpcResult.getErrorResult();
        }

        if (connectStatus != IConnectStatus.SERVICE_BIND) {

            connectService();
            return IpcResult.getErrorResult();

        }

        return handleType(iRequest);
    }


    //建立IPC通讯连接
    private void connectService() {


        if (serviceConnect == null) {
            serviceConnect = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName name, IBinder service) {
                    connectStatus = IConnectStatus.SERVICE_BIND;

                    ServiceInterface = IServiceInterface.Stub.asInterface(service);
                    //往服务端注入接口
                    try {
                        ServiceInterface.registerCallback(ClientInterface);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    //Binder通信的死亡通知 有重启逻辑
                    try {
                        service.linkToDeath(deathRecipient, 0);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    //连接成功之后,去把等待的数据请求发送
                    for (IRequest iRequest : mWaitSets) {
                        handleType(iRequest);
                    }
                    mWaitSets.clear();

                }

                @Override
                public void onServiceDisconnected(ComponentName name) {
                    connectStatus = IConnectStatus.SERVICE_UNBIND;
                }
            };
        }


        if (deathRecipient == null) {
            deathRecipient = new IBinder.DeathRecipient() {
                @Override
                public void binderDied() {
                    connectStatus = IConnectStatus.SERVICE_UNBIND;
                    //针对异步请求做callback处理
                    for (IRequest iRequest : mSets) {
                        iRequest.getCallback().callback(IpcResult.getErrorResult());
                    }
                    mSets.clear();
                }
            };
        }
        if (ClientInterface == null) {
            ClientInterface = new IClientInterface.Stub() {
                @Override
                public void callback(String requestKey, String result) throws RemoteException {
                    Iterator<IRequest> iterator = mSets.iterator();
                    while (iterator.hasNext()) {
                        IRequest next = iterator.next();
                        if (TextUtils.equals(next.getRequestKey(), requestKey)) {
                            next.getCallback().callback(IpcResult.getSuccessResult(result));
                            mSets.remove(next);
                            return;
                        }
                    }
                }
            };
        }
        Intent intent = new Intent(context, IpcService.class);
        context.bindService(intent, serviceConnect, Service.BIND_AUTO_CREATE);
        connectStatus = IConnectStatus.SERVICE_BINGING;

    }

    //实际跨进程通信的方法
    private IResult handleType(IRequest iRequest) {
        if (iRequest.getCallback() != null) {
            try {


                ServiceInterface.enqueue(iRequest.getRequestKey(), iRequest.getParams());
            } catch (RemoteException e) {
                iRequest.getCallback().callback(IpcResult.getErrorResult());
            }
        } else {
            try {
                String data = ServiceInterface.execute(iRequest.getRequestKey(), iRequest.getParams());
                return IpcResult.getSuccessResult(data);
            } catch (RemoteException e) {
                return IpcResult.getErrorResult();
            }
        }
        return IpcResult.getErrorResult();
    }


}
