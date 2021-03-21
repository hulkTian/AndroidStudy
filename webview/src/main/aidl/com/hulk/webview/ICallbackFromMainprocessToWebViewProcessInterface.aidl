// ICallbackFromMainprocessToWebViewProcessInterface.aidl
package com.hulk.webview;

// Declare any non-default types here with import statements
interface ICallbackFromMainprocessToWebViewProcessInterface {
    void onResult(String callbackName, String response);
}