package com.example.androiddrawing;

import static com.example.util.i_Ads2.PthinkFacebookBannerSmall;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.util.JsonUtils;
import com.example.util.StatusBarUtil;
import com.example.util.i_Ads2;
import com.example.util.i_SharedString2;
import com.example.util.i_SharedUtils2;

import howtodraw.drawing.lessons.art.R;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class ActivityAboutUs extends AppCompatActivity {

    TextView txtAppName, txtVersion, txtCompany, txtEmail, txtWebsite, txtContact;
    WebView webView;
    JsonUtils jsonUtils;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setStatusBarGradiant(ActivityAboutUs.this);
        setContentView(R.layout.activity_aboutus);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.menu_about));
        setSupportActionBar(toolbar);
        toolbar.setTitleTextAppearance(this, R.style.RobotoTextViewStyle);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        LinearLayout llbanner = findViewById(R.id.ll_ads);
        PthinkFacebookBannerSmall(ActivityAboutUs.this, llbanner);

        i_Ads2.NativeBiGAds(ActivityAboutUs.this,findViewById(R.id.fl_adplaceholder));
        i_Ads2.NativeBiGAds(ActivityAboutUs.this,findViewById(R.id.fl_adplaceholder1));

        jsonUtils = new JsonUtils(this);
        jsonUtils.forceRTLIfSupported(getWindow());

        txtAppName = findViewById(R.id.text_app_name);
        txtVersion = findViewById(R.id.text_version);
        txtCompany = findViewById(R.id.text_company);
        txtEmail = findViewById(R.id.text_email);
        txtWebsite = findViewById(R.id.text_website);
        txtContact = findViewById(R.id.text_contact);
        webView = findViewById(R.id.webView);

        txtAppName.setText(getString(R.string.app_name));
        txtVersion.setText(getString(R.string.version_name));
        txtCompany.setText(getString(R.string.company_name));
        txtEmail.setText(getString(R.string.email_id));
        txtWebsite.setText(getString(R.string.website_name));
        txtContact.setText(getString(R.string.contact_no));

        webView.setBackgroundColor(Color.TRANSPARENT);
        webView.setFocusableInTouchMode(false);
        webView.setFocusable(false);
        webView.getSettings().setDefaultTextEncodingName("UTF-8");
        String mimeType = "text/html";
        String encoding = "utf-8";
        String htmlText = getString(R.string.about_desc);

        String text = "<html><head>"
                + "<style type=\"text/css\">@font-face {font-family: MyFont;src: url(\"file:///android_asset/fonts/Montserrat-Medium_0.otf\")}body{font-family: MyFont;color: #8D8D8D;text-align:justify}"
                + "</style></head>"
                + "<body>"
                + htmlText
                + "</body></html>";

        webView.loadDataWithBaseURL(null, text, mimeType, encoding, null);

        LinearLayout linearLayout_email = findViewById(R.id.linearLayout_email_about_us);
        LinearLayout linearLayout_website = findViewById(R.id.linearLayout_web_about_us);
        LinearLayout linearLayout_phone = findViewById(R.id.linearLayout_contact_about_us);

        linearLayout_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((!i_Ads2.ShowAds(ActivityAboutUs.this))){
                    return;
                }
                try {
                    Intent emailIntent = new Intent(Intent.ACTION_VIEW);
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{
                            getResources().getString(R.string.email_id)
                    });
                    emailIntent.setData(Uri.parse("mailto:?subject="));
                    startActivity(emailIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    jsonUtils.alertBox(getResources().getString(R.string.wrong));
                }
            }
        });

        linearLayout_website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((!i_Ads2.ShowAds(ActivityAboutUs.this))){
                    return;
                }
                try {
                    String url = getResources().getString(R.string.website_name);
                    if (!url.startsWith("http://") && !url.startsWith("https://")) {
                        url = "http://" + url;
                    }
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(browserIntent);
                } catch (Exception e) {
                    jsonUtils.alertBox(getResources().getString(R.string.wrong));
                }
            }
        });

        linearLayout_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + getResources().getString(R.string.contact_no)));
                    startActivity(callIntent);
                } catch (Exception e) {
                    jsonUtils.alertBox(getResources().getString(R.string.wrong));
                }
            }
        });

        onlyBtnAddShow();

    }

    private void onlyBtnAddShow() {

        LinearLayout btn_version =findViewById(R.id.btn_version);
        btn_version.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!i_Ads2.ShowAds(ActivityAboutUs.this))){
                    return;
                }
            }
        });

        LinearLayout btn_company =findViewById(R.id.btn_company);
        btn_company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!i_Ads2.ShowAds(ActivityAboutUs.this))){
                    return;
                }
            }
        });


        ImageView btnplay = findViewById(R.id.btnplay);
        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent advertise = new Intent(ActivityAboutUs.this, AdvertisementClass.class);
                i_Ads2.ShowAds(ActivityAboutUs.this, advertise);
            }
        });
        if (i_SharedUtils2.get(i_SharedString2.AdsExtraBtn).equalsIgnoreCase("1")) {
            btnplay.setVisibility(View.VISIBLE);
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 300f);
            //repeats the animation 2 times
            valueAnimator.setRepeatCount(200000);
            valueAnimator.setRepeatMode(ValueAnimator.RESTART);
            valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator()); // increase the speed first and then decrease
            // animate over the course of 700 milliseconds
            valueAnimator.setDuration(2000);
// define how to update the view at each "step" of the animation
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float progress = (float) animation.getAnimatedValue();
                    btnplay.setRotationX(progress);

                }
            });
            valueAnimator.start();
        } else {
            btnplay.setVisibility(View.GONE);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;

            default:
                return super.onOptionsItemSelected(menuItem);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        i_Ads2.ShowAdsBackPressedFinish(ActivityAboutUs.this);
    }
}