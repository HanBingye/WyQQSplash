// IServiceInterface.aidl
package com.bing.ipc;
import com.bing.ipc.IClientInterface;

// Declare any non-default types here with import statements

interface IServiceInterface {
        void enqueue(String requestKey, String requestParams);

        String execute(String requestKey, String requestParams);

        void registerCallback(IClientInterface client);
}