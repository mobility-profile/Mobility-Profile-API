package fi.ohtu.connectiontest;

import android.content.Context;
import android.webkit.JavascriptInterface;

public class WebAppInterface {
    Context mContext;

    WebAppInterface(Context c) {
        mContext = c;
    }

    @JavascriptInterface
    public void update() {
        System.out.println("JIPII");
    }
}