package com.example.androiddrawing;

import static com.example.androiddrawing.MyApplication.mFirebaseRemoteConfig;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.example.util.Globals2;
import com.example.util.StatusBarUtil;
import com.example.util.i_SharedString2;
import com.example.util.i_SharedUtils2;
import howtodraw.drawing.lessons.art.R;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class ActivitySplash extends AppCompatActivity {

    private boolean isFirstTime = true;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        StatusBarUtil.setStatusBarGradiant(ActivitySplash.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
        ReceivedFirebaseMessage();
//        Thread splashThread = new Thread() {
//            public void run() {
//                try {
//                    int waited = 0;
//
//                    while (active && (waited < splashTime)) {
//                        sleep(100);
//                        if (active) {
//                            waited += 100;
//                        }
//                    }
//                } catch (Exception e) {
//                    e.toString();
//                } finally {
//                    Intent int1 = new Intent(getApplicationContext(), MainActivity.class);
//                    startActivity(int1);
//                    finish();
//
//                }
//            }
//        };
//
//        splashThread.start();
    }
    private void ReceivedFirebaseMessage() {

        try {
            if (mFirebaseRemoteConfig.getString(getResources().getString(R.string.isInternetConnected)).equals("")) {
                int internetConnected = Integer.parseInt(i_SharedUtils2.get(i_SharedString2.isInternetConnected));

                if (internetConnected != 0 && !(Globals2.isNetworkConnected(ActivitySplash.this))) {
                    Globals2 forceUpdate = new Globals2(ActivitySplash.this, new Globals2.gotNextPageInternetConnected() {
                        @Override
                        public void gotoNextPageIsNetworkConnected(boolean gotopage) {

                            if (gotopage) {
                                CheckForceUpdate();
                            }
                        }
                    });
                    forceUpdate.showNetConnectionDialog(ActivitySplash.this, true);

                } else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            CheckForceUpdate();
                        }
                    }, 500);
                }
            } else {
                checkInternetConnected();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void gotoNextActivity() {
        new Handler().postDelayed(new Runnable() {

            public void run() {
                if (isFirstTime) {
                    isFirstTime = false;
                    if (i_SharedUtils2.get(i_SharedString2.isDisplayExtraScreen).equalsIgnoreCase("1")) {
                        Intent localIntent = new Intent(ActivitySplash.this, ExtraStartActivity.class);
                        startActivity(localIntent);
                    } else {
                        Intent localIntent = new Intent(ActivitySplash.this, StartActivity.class);
                        startActivity(localIntent);
                    }
                    finish();
                }
            }
        }, 4500);
    }

    private void CheckForceUpdate() {
        try {

            int updateForce = Integer.parseInt(i_SharedUtils2.get(i_SharedString2.isUpdateForce));
            int appVersion = 0;
            if (i_SharedUtils2.get(i_SharedString2.appVersion).contains(".")) {
                appVersion = Integer.parseInt(i_SharedUtils2.get(i_SharedString2.appVersion).replace(".", ""));
            } else {
                appVersion = Integer.parseInt(i_SharedUtils2.get(i_SharedString2.appVersion));

            }
            String version = null;
            try {
                PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                version = pInfo.versionName;
                version = version.replace(".", "");
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            if (updateForce != 0 && Integer.parseInt(version) < appVersion) {

                Globals2 forceUpdate = new Globals2(ActivitySplash.this, new Globals2.gotNextPage() {
                    @Override
                    public void gotoNextPage(boolean gotopage) {

                        if (gotopage) {
                            otherAppLinkShowDialog();
                        }
                    }
                });
                forceUpdate.isForceUpdate();
            } else {
                otherAppLinkShowDialog();
            }
        } catch (Exception e) {
            e.printStackTrace();
            //otherAppLinkShowDialog();
        }
    }

    private void checkInternetConnected() {

        int internetConnected = Integer.parseInt(i_SharedUtils2.get(i_SharedString2.isInternetConnected));

        if (internetConnected != 0 && !(Globals2.isNetworkConnected(ActivitySplash.this))) {
            Globals2 forceUpdate = new Globals2(ActivitySplash.this, new Globals2.gotNextPageInternetConnected() {
                @Override
                public void gotoNextPageIsNetworkConnected(boolean gotopage) {

                    if (gotopage) {
                        CheckForceUpdate();
                    }
                }
            });
            forceUpdate.showNetConnectionDialog(ActivitySplash.this, true);

        } else {
            CheckForceUpdate();
        }
    }

    private void otherAppLinkShowDialog() {
        int internetConnected = Integer.parseInt(i_SharedUtils2.get(i_SharedString2.otherAppLinkShow));

        if (internetConnected != 0) {
            Globals2 forceUpdate = new Globals2(ActivitySplash.this, new Globals2.gotNextPage() {
                @Override
                public void gotoNextPage(boolean gotopage) {

                    if (gotopage) {
                        gotoNextActivity();
                    }
                }
            });
            forceUpdate.showOtherAppLink();

        } else {
            gotoNextActivity();
        }
    }

}
