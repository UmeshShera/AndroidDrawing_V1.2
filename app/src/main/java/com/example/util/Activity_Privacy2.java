package com.example.util;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import howtodraw.drawing.lessons.art.R;

public class Activity_Privacy2 extends AppCompatActivity {

    private WebView webView;
    LinearLayout llbanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy2);

        llbanner = findViewById(R.id.llbanner);
        i_Ads2.PthinkFacebookBannerSmall(Activity_Privacy2.this, llbanner);

        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/privacypolicy.htm");
    }

    public void onBackClick(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        i_Ads2.ShowAdsBackPressedFinish(Activity_Privacy2.this);
    }
}
