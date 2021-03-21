package com.hulk.webview.command;

import android.os.RemoteException;
import android.util.Log;

import com.google.auto.service.AutoService;
import com.google.gson.Gson;
import com.hulk.common.service.IWebViewService;
import com.hulk.common.unit.ServiceLoaderUnit;
import com.hulk.webview.ICallbackFromMainprocessToWebViewProcessInterface;
import com.hulk.webview.login.LoginEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

@AutoService({Command.class})
public class CommandLogin implements Command {
    IWebViewService iWebViewService = ServiceLoaderUnit.load(IWebViewService.class);
    ICallbackFromMainprocessToWebViewProcessInterface callback;
    String callbacknameFromNativeJs;
    public CommandLogin(){
        EventBus.getDefault().register(this);
    }

    @Override
    public String name() {
        return "login";
    }

    @Override
    public void execute(final Map parameters, ICallbackFromMainprocessToWebViewProcessInterface callback) {
        Log.d("CommandLogin", new Gson().toJson(parameters));
        if (iWebViewService != null && !iWebViewService.isLogined()) {
            iWebViewService.login();
            this.callback = callback;
            this.callbacknameFromNativeJs = parameters.get("callbackname").toString();
        }
    }

    @Subscribe
    public void onMessageEvent(LoginEvent event) {
        Log.d("CommandLogin", event.userName);
        HashMap map = new HashMap();
        map.put("accountName", event.userName);
        if(this.callback != null) {
            try {
                this.callback.onResult(callbacknameFromNativeJs, new Gson().toJson(map));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
