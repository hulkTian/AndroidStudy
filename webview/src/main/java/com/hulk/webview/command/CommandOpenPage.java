package com.hulk.webview.command;

import android.content.ComponentName;
import android.content.Intent;
import android.text.TextUtils;

import com.google.auto.service.AutoService;
import com.hulk.common.base.BaseApplication;
import com.hulk.webview.ICallbackFromMainprocessToWebViewProcessInterface;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

@AutoService({Command.class})
public class CommandOpenPage implements Command {

    @NotNull
    @Override
    public String name() {
        return "openPage";
    }

    @Override
    public void execute(Map<?,?> parameters, ICallbackFromMainprocessToWebViewProcessInterface callback) {
        String targetClass = String.valueOf(parameters.get("target_class"));
        if (!TextUtils.isEmpty(targetClass)) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName(BaseApplication.application, targetClass));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            BaseApplication.application.startActivity(intent);
        }
    }
}
