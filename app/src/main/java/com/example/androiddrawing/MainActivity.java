package com.example.androiddrawing;

import static com.example.util.i_Ads2.PthinkFacebookBannerSmall;

import android.Manifest;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.example.util.JsonUtils;
import com.example.util.StatusBarUtil;
import com.example.util.i_Ads2;
import com.example.util.i_SharedString2;
import com.example.util.i_SharedUtils2;
import com.google.android.material.navigation.NavigationView;
import com.ixidev.gdpr.GDPRChecker;

import howtodraw.drawing.lessons.art.R;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FragmentManager fragmentManager;
    //    MyApplication MyApp;
    final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 102;
    String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    LinearLayout ll_ab, ll_share, ll_rate, ll_more, ll_privacy;
    NavigationView navigationView;
    private DrawerLayout mDrawerLayout;
    //    LinearLayout adLayout;
    JsonUtils jsonUtils;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setStatusBarGradiant(MainActivity.this);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextAppearance(this, R.style.RobotoTextViewStyle);
        toolbar.post(new Runnable() {
            @Override
            public void run() {
                Drawable d = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_nav, null);
                toolbar.setNavigationIcon(d);
            }
        });

//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//            }
//        });
//        AudienceNetworkAds.initialize(this);

        jsonUtils = new JsonUtils(this);
        jsonUtils.forceRTLIfSupported(getWindow());

        fragmentManager = getSupportFragmentManager();
        //MyApp = MyApplication.getInstance();
        navigationView = findViewById(R.id.nav_view);
        mDrawerLayout = findViewById(R.id.drawer_layout);

        //adLayout = findViewById(R.id.adView);

        LinearLayout llbanner = findViewById(R.id.ll_ads);
        PthinkFacebookBannerSmall(MainActivity.this, llbanner);


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if ((!i_Ads2.ShowAds(MainActivity.this))){
                    return;
                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }
        };

        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        FragmentDrawingList homeFragment = new FragmentDrawingList();
        fragmentManager.beginTransaction().replace(R.id.Container, homeFragment).commit();

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
        }

        ll_ab = findViewById(R.id.ll_ab);
        ll_more = findViewById(R.id.ll_more);
        ll_privacy = findViewById(R.id.ll_priv);
        ll_rate = findViewById(R.id.ll_rate);
        ll_share = findViewById(R.id.ll_share);

        ll_ab.setOnClickListener(this);
        ll_more.setOnClickListener(this);
        ll_privacy.setOnClickListener(this);
        ll_rate.setOnClickListener(this);
        ll_share.setOnClickListener(this);

        //checkForConsent();

        ImageView btnplay = findViewById(R.id.btnplay);
        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent advertise = new Intent(MainActivity.this, AdvertisementClass.class);
                i_Ads2.ShowAds(MainActivity.this, advertise);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.ll_ab) {
            Intent intent = new Intent(MainActivity.this, ActivityAboutUs.class);
            //startActivity(intent);
            i_Ads2.ShowAds(MainActivity.this, intent);
        } else if (id == R.id.ll_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_msg) + "https://play.google.com/store/apps/details?id=" + getPackageName());
            sendIntent.setType("text/plain");
//            startActivity(sendIntent);
            i_Ads2.ShowAds(MainActivity.this, sendIntent);
        } else if (id == R.id.ll_rate) {
            if ((!i_Ads2.ShowAds(MainActivity.this))){
                return;
            }
            final String appName = getPackageName();//your application package name i.e play store application url
            Log.e("package:", appName);
            try {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("market://details?id="
                                + appName)));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id="
                                + appName)));
            }
        } else if (id == R.id.ll_more) {
            if ((!i_Ads2.ShowAds(MainActivity.this))){
                return;
            }
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.play_more_apps))));

        } else if (id == R.id.ll_priv) {
            Intent i = new Intent(MainActivity.this, AcitivityWebview.class);
            i.putExtra("URL", "file:///android_asset/privacy_policy.html");
            i.putExtra("Title", getResources().getString(R.string.prv_policy));
            //startActivity(i);
            i_Ads2.ShowAds(MainActivity.this, i);
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
//        ExitApp();
        i_Ads2.ShowAdsBackPressedFinish(MainActivity.this);
    }

    private void ExitApp() {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle(getString(R.string.app_name))
                .setMessage(getString(R.string.exit_msg))
                .setIcon(R.mipmap.app_icon)
                .setPositiveButton(getString(R.string.exit_yes), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton(getString(R.string.exit_no), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .show();
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        boolean canUseExternalStorage = false;

        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    canUseExternalStorage = true;
                }
                if (!canUseExternalStorage) {
                    Toast.makeText(MainActivity.this, getString(R.string.image_permission), Toast.LENGTH_SHORT).show();
                } else {
                    // user now provided permission
                    // perform function for what you want to achieve
                    Log.i("Permission", "granted");
                }
            }
        }
    }

    public void checkForConsent() {
        new GDPRChecker()
                .withContext(MainActivity.this)
                .withPrivacyUrl(getString(R.string.privacy_gdpr_url))
                .withPublisherIds(getString(R.string.admob_publisher_id))
                //.withTestMode("FA0F55855A8169A47EB9D713413B9FE9")
                .check();
        //BannerAds.ShowBannerAds(MainActivity.this, adLayout);
    }
}