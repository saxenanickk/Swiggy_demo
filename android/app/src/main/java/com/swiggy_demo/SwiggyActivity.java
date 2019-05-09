package com.swiggy_demo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.HashMap;

public class SwiggyActivity extends AppCompatActivity {

    @SuppressLint({"AddJavascriptInterface", "SetJavaScriptEnabled"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("x-platform-cookie", getIntent().getStringExtra("x-platform-cookie"));

        WebView myWebView = new WebView(this);
        setContentView(myWebView);

        myWebView.setWebViewClient(new CustomWebViewClient());
        myWebView.addJavascriptInterface(new WebAppInterface(this), "NativeApp");
        WebSettings webSetting = myWebView.getSettings();
        webSetting.setJavaScriptEnabled(true);

        myWebView.loadUrl(getIntent().getStringExtra("url"), hashMap);
    }

    private class CustomWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    public class WebAppInterface {
        Context mContext;

        /** Instantiate the interface and set the context */
        WebAppInterface(Context c) {
            mContext = c;
        }

        /** Show a toast from the web page */
        @JavascriptInterface
        @SuppressWarnings("unused")
        public void setCookie(String cookie) {
            Toast.makeText(mContext, "Cookie: " + cookie, Toast.LENGTH_LONG).show();
        }

        @JavascriptInterface @SuppressWarnings("unused")
        public void fireEvent(String event, String order_id) {
            Toast.makeText(mContext, event + " " + order_id, Toast.LENGTH_LONG).show();
        }
    }
}
