package com.swiggy_demo;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import javax.annotation.Nonnull;

public class OpenAppModule extends ReactContextBaseJavaModule {

    OpenAppModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Nonnull
    @Override
    public String getName() {
        return "OpenApp";
    }

    @ReactMethod @SuppressWarnings("unused")
    public void startSwiggy(String url, String x_platform_cookie) {
        Activity context = getCurrentActivity();
        if (context != null && !context.isFinishing()) {
            Intent swiggyIntent = new Intent(context, SwiggyActivity.class);

            swiggyIntent.putExtra("url", url);
            swiggyIntent.putExtra("x-platform-cookie", x_platform_cookie);

            context.startActivity(swiggyIntent);
        }
    }

    @ReactMethod @SuppressWarnings("unused")
    public void stopSwiggy() {
        try {
            Activity context;
            context = getCurrentActivity();
            if (context != null) {
                context.finish();
            }
        } catch (NullPointerException e) {
            Log.d("Exception", e.getMessage());
        }
    }
}
