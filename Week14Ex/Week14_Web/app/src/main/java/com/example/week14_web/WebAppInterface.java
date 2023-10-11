package com.example.week14_web;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class WebAppInterface {
    Context mContext;

    WebAppInterface(Context c) {
        mContext = c;
    }

    @JavascriptInterface
    public void showToast(String arg) {
        Toast.makeText(mContext, arg, Toast.LENGTH_SHORT).show();
    }
}
