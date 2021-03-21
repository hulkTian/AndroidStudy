package com.hulk.webview.command;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.google.auto.service.AutoService;
import com.hulk.common.base.BaseApplication;
import com.hulk.webview.ICallbackFromMainprocessToWebViewProcessInterface;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

@AutoService({Command.class})
public class CommandShowToast implements Command {
    @NotNull
    @Override
    public String name() {
        return "showToast";
    }

    @Override
    public void execute(final Map<?, ?> parameters, ICallbackFromMainprocessToWebViewProcessInterface callback) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(() -> Toast.makeText(BaseApplication.application,
                String.valueOf(parameters.get("message")), Toast.LENGTH_SHORT).show());
    }
}
