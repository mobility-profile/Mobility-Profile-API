package fi.ohtu.connectiontest;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class WebAppInterface {
    Context mContext;

    WebAppInterface(Context c) {
        mContext = c;
    }

    @JavascriptInterface
    public void update(String toast) {
        //System.out.println(toast);
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }
}