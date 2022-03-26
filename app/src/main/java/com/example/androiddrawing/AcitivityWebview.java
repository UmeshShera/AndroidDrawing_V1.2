package com.example.androiddrawing;

import static com.example.util.i_Ads2.PthinkFacebookBannerSmall;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.util.SessionManagerAppLang;
import com.example.util.i_Ads2;

import howtodraw.drawing.lessons.art.R;

import java.util.Locale;

public class AcitivityWebview extends AppCompatActivity {

    String IntentURL, IntentTitle = "";

    SessionManagerAppLang sessionManagerAppLang;
    ImageView imBack;
    TextView TVTitle;
    SwipeRefreshLayout swipeRefreshLayout;
    WebView webView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        IntentURL = getIntent().getStringExtra("URL");
        IntentTitle = getIntent().getStringExtra("Title");
        imBack = findViewById(R.id.imBack);
        TVTitle = findViewById(R.id.TVTitle);
        webView1 = findViewById(R.id.webView1);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        imBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        TVTitle.setText(IntentTitle);

        sessionManagerAppLang = new SessionManagerAppLang(AcitivityWebview.this);
        setLocale(sessionManagerAppLang.getLanguage());

        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);
                                        LoadPage(IntentURL);
                                    }
                                }
        );

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LoadPage(IntentURL);

            }
        });
        LinearLayout llbanner = findViewById(R.id.ll_ads);
        PthinkFacebookBannerSmall(AcitivityWebview.this, llbanner);
    }

    public void LoadPage(String Url) {
        webView1.setWebViewClient(new MyBrowser());
        webView1.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress == 100) {
                    swipeRefreshLayout.setRefreshing(false);
                } else {
                    swipeRefreshLayout.setRefreshing(true);
                }
            }
        });
        webView1.getSettings().setLoadsImagesAutomatically(true);
        webView1.getSettings().setJavaScriptEnabled(true);
        webView1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView1.loadUrl(Url);
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    public void setLocale(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);


    }

    @Override
    public void onBackPressed() {
        i_Ads2.ShowAdsBackPressedFinish(AcitivityWebview.this);
//        super.onBackPressed();
    }
}
