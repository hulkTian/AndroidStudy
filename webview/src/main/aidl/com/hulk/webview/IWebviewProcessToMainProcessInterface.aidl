// IWebviewProcessToMainProcessInterface.aidl
package com.hulk.webview;

import com.hulk.webview.ICallbackFromMainprocessToWebViewProcessInterface;
// Declare any non-default types here with import statements

interface IWebviewProcessToMainProcessInterface {
    void handleWebCommand(String commandName, String jsonParams, in ICallbackFromMainprocessToWebViewProcessInterface callback);
}