// IClientInterface.aidl
package com.bing.ipc;

// Declare any non-default types here with import statements

interface IClientInterface {
   void callback(String requestKey, String result);
}