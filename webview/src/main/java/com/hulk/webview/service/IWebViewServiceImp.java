package com.hulk.webview.service;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

import com.google.auto.service.AutoService;
import com.hulk.common.base.BaseApplication;
import com.hulk.common.constants.Constants;
import com.hulk.common.service.IWebViewService;
import com.hulk.webview.WebViewActivity;
import com.hulk.webview.WebViewFragment;
import com.hulk.webview.login.LoginActivity;

import org.jetbrains.annotations.NotNull;

@AutoService({IWebViewService.class})
public class IWebViewServiceImp implements IWebViewService {

    @Override
    public void startWebViewActivity(@NotNull Context context, @NotNull String url,
                                     boolean isShowTitle, boolean canNativeRefresh) {
        context.startActivity(
                new Intent(context, WebViewActivity.class)
                        .putExtra(Constants.URL, url)
                        .putExtra(Constants.IS_SHOW_TITLE, isShowTitle)
                        .putExtra(Constants.CAN_NATIVE_REFRESH, canNativeRefresh)
        );
    }

    @NotNull
    @Override
    public Fragment getWebFragment(@NotNull String url, boolean canNativeRefresh) {
        return WebViewFragment.Companion.newInstance(url, canNativeRefresh);
    }

    @Override
    public void startDemoHtml(@NotNull Context context) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(Constants.TITLE, "本地Demo测试页");
        intent.putExtra(Constants.URL, Constants.ANDROID_ASSET_URI + "demo.html");
        context.startActivity(intent);
    }

    @Override
    public boolean isLogined() {
        return false;
    }

    @Override
    public void login() {
        Intent intent = new Intent(BaseApplication.application, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BaseApplication.application.startActivity(intent);
    }

}
