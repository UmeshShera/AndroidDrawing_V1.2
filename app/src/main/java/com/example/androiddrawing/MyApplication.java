package com.example.androiddrawing;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.StrictMode;
import android.util.DisplayMetrics;
import android.util.Log;

import com.example.util.Manager1AppOpen;
import com.example.util.SessionManagerAppLang;
import com.example.util.i_SharedInt2;
import com.example.util.i_SharedString2;
import com.example.util.i_SharedUtils2;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.onesignal.OneSignal;
import howtodraw.drawing.lessons.art.R;

import java.util.Locale;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;


public class MyApplication extends Application {
    SessionManagerAppLang sessionManagerAppLang;
    public static FirebaseRemoteConfig mFirebaseRemoteConfig;
    Manager1AppOpen appOpenManager;

//    private static MyApplication mInstance;
//
//    public MyApplication() {
//        mInstance = this;
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        //OneSignal.startInit(this).inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification).init();
//        mInstance = this;
//        ViewPump.init(ViewPump.builder()
//                .addInterceptor(new CalligraphyInterceptor(
//                        new CalligraphyConfig.Builder()
//                                .setDefaultFontPath("fonts/Montserrat-Medium_0.otf")
//                                .setFontAttrId(R.attr.fontPath)
//                                .build()))
//                .build());
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
//        builder.detectFileUriExposure();

        FirebaseApp.initializeApp(this);
        FirebaseMessaging.getInstance().subscribeToTopic("all");
        sessionManagerAppLang = new SessionManagerAppLang(getApplicationContext());
        setLocale(sessionManagerAppLang.getLanguage());

        i_SharedUtils2.init(this);
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
        initializeFirebase();

        i_SharedUtils2.init(this);

        appOpenManager = new Manager1AppOpen(this);
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(3600)
                .build();
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);

        new Thread(() -> {
            try {
                mFirebaseRemoteConfig.fetchAndActivate()
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                String data_2 = mFirebaseRemoteConfig.getString(getResources().getString(R.string.ADS_Number));
                                Log.e("-----vvvvv-------", "onCreate data_2 : " + data_2);

                                String ADS_BasedOnTimeInSecond = mFirebaseRemoteConfig.getString(getResources().getString(R.string.ADS_BasedOnTimeInSecond));
//                                int ADS_BasedOnTimeInSecond = 30;

                                String banner_native_ads = mFirebaseRemoteConfig.getString(getResources().getString(R.string.banner_native_ads));
                                String Admob_Banner_Top = mFirebaseRemoteConfig.getString(getResources().getString(R.string.Admob_Banner_Top));
                                String Unity_ID = mFirebaseRemoteConfig.getString(getResources().getString(R.string.Unity_ID));
                                String News_Native_Ads = mFirebaseRemoteConfig.getString(getResources().getString(R.string.News_Native_Ads));
                                String ADS = mFirebaseRemoteConfig.getString(getResources().getString(R.string.ADS));
//                                String ADS = "5";
                                String NativeAdvanceBannerCounter = mFirebaseRemoteConfig.getString(getResources().getString(R.string.NativeAdvanceBannerCounter));
                                String InBetweenListNativeBnr = mFirebaseRemoteConfig.getString(getResources().getString(R.string.InBetweenListNativeBnr));
                                String isDisplayExtraScreen = mFirebaseRemoteConfig.getString(getResources().getString(R.string.isDisplayExtraScreen));
                                String AdsExtraBtn = mFirebaseRemoteConfig.getString(getResources().getString(R.string.AdsExtraBtn));
//                                String AdsExtraBtn ="1";

                                String data4 = mFirebaseRemoteConfig.getString(getResources().getString(R.string.Kite_Game));
                                String data5 = mFirebaseRemoteConfig.getString(getResources().getString(R.string.Kite_Quiz));
                                String MGL_Game = mFirebaseRemoteConfig.getString(getResources().getString(R.string.MGL_Game));
                                String Game_PredChamp = mFirebaseRemoteConfig.getString(getResources().getString(R.string.Game_PredChamp));

                                String isUpdateForce = mFirebaseRemoteConfig.getString(getResources().getString(R.string.isUpdateForce));
                                String isInternetConnected = mFirebaseRemoteConfig.getString(getResources().getString(R.string.isInternetConnected));
                                String appVersion = mFirebaseRemoteConfig.getString(getResources().getString(R.string.appVersion));
                                String otherAppLinkShow = mFirebaseRemoteConfig.getString(getResources().getString(R.string.otherAppLinkShow));
                                String forceUpdateMessage = mFirebaseRemoteConfig.getString(getResources().getString(R.string.forceUpdateMessage));
                                String otherAppLinkShowMessage = mFirebaseRemoteConfig.getString(getResources().getString(R.string.otherAppLinkShowMessage));
                                String otherAppLinkShowPackageName = mFirebaseRemoteConfig.getString(getResources().getString(R.string.otherAppLinkShowPackageName));

                                //Log.e("---vvv---", "onCreate : "+data );

                                String Admob_Interstitial1 = mFirebaseRemoteConfig.getString(getResources().getString(R.string.Admob_Interstitial1));
                                String Admob_Banner = mFirebaseRemoteConfig.getString(getResources().getString(R.string.Admob_Banner));
                                String Admob_Native = mFirebaseRemoteConfig.getString(getResources().getString(R.string.Admob_Native));
                                String Admob_Appopen = mFirebaseRemoteConfig.getString(getResources().getString(R.string.Admob_Appopen));

                                String Admob_Interstitial2 = mFirebaseRemoteConfig.getString(getResources().getString(R.string.Admob_Interstitial2));
                                String Admob_Interstitial3 = mFirebaseRemoteConfig.getString(getResources().getString(R.string.Admob_Interstitial3));
                                String Admob_Interstitial4 = mFirebaseRemoteConfig.getString(getResources().getString(R.string.Admob_Interstitial4));
                                String Admob_Interstitial5 = mFirebaseRemoteConfig.getString(getResources().getString(R.string.Admob_Interstitial5));

                                /*Mandatory Test Key*/
//                                i_SharedUtils2.set(i_SharedString2.isUpdateForce, "0");
//                                i_SharedUtils2.set(i_SharedString2.appVersion, "5");
//                                i_SharedUtils2.set(i_SharedString2.isInternetConnected, "0");
//                                i_SharedUtils2.set(i_SharedString2.otherAppLinkShow, "0");
//                                i_SharedUtils2.set(i_SharedString2.isDisplayExtraScreen, "0");

//                                String Admob_Interstitial1 = "/6499/example/interstitial";
//                                String Admob_Banner = "/6499/example/banner";
//                                String Admob_Native = "/6499/example/native";
//                                String Admob_Appopen = "/6499/example/app-open";

//                                String Admob_Interstitial = "0";
//                                String Admob_Banner = "0";
//                                String Admob_Native = "0";
//                                String Admob_Appopen = "0";

                                Log.e("-----vvvvv-------", "onCreate Admob_Appopen : " + Admob_Appopen);

                                i_SharedUtils2.set(i_SharedString2.Admob_Interstitial1, Admob_Interstitial1);
                                i_SharedUtils2.set(i_SharedString2.Admob_Banner, Admob_Banner);
                                i_SharedUtils2.set(i_SharedString2.Admob_Native, Admob_Native);
                                i_SharedUtils2.set(i_SharedString2.Admob_Appopen, Admob_Appopen);

                                i_SharedUtils2.set(i_SharedString2.Admob_Interstitial2, Admob_Interstitial2);
                                i_SharedUtils2.set(i_SharedString2.Admob_Interstitial3, Admob_Interstitial3);
                                i_SharedUtils2.set(i_SharedString2.Admob_Interstitial4, Admob_Interstitial4);
                                i_SharedUtils2.set(i_SharedString2.Admob_Interstitial5, Admob_Interstitial5);

                                i_SharedUtils2.set(i_SharedString2.SAX_Game, data4);
                                i_SharedUtils2.set(i_SharedString2.SAX_Quize, data5);
                                i_SharedUtils2.set(i_SharedString2.MGL_Game, MGL_Game);
                                i_SharedUtils2.set(i_SharedString2.Game_PredChamp, Game_PredChamp);

                                i_SharedUtils2.set(i_SharedString2.ADS_Number, data_2);

                                //Log.e("----VVH----", "onCreate Ads Interval : "+Integer.parseInt(ADS));
                                i_SharedUtils2.set(i_SharedString2.Unity_ID, Unity_ID);
                                i_SharedUtils2.set(i_SharedString2.Admob_Banner_Top, Admob_Banner_Top);
                                i_SharedUtils2.set(i_SharedString2.banner_native_ads, banner_native_ads);
                                i_SharedUtils2.set(i_SharedString2.News_Native_Ads, News_Native_Ads);


                                i_SharedUtils2.set(i_SharedString2.isUpdateForce, isUpdateForce);
                                i_SharedUtils2.set(i_SharedString2.appVersion, appVersion);
                                i_SharedUtils2.set(i_SharedString2.isInternetConnected, isInternetConnected);
                                i_SharedUtils2.set(i_SharedString2.otherAppLinkShow, otherAppLinkShow);
                                i_SharedUtils2.set(i_SharedString2.forceUpdateMessage, forceUpdateMessage);
                                i_SharedUtils2.set(i_SharedString2.otherAppLinkShowMessage, otherAppLinkShowMessage);
                                i_SharedUtils2.set(i_SharedString2.otherAppLinkShowPackageName, otherAppLinkShowPackageName);
                                i_SharedUtils2.set(i_SharedString2.isDisplayExtraScreen, isDisplayExtraScreen);
                                i_SharedUtils2.set(i_SharedString2.AdsExtraBtn, AdsExtraBtn);

                                try {
                                    i_SharedUtils2.set(i_SharedInt2.ADS, Integer.parseInt(ADS));
                                    i_SharedUtils2.set(i_SharedInt2.ADS_BasedOnTimeInSecond, Integer.parseInt(ADS_BasedOnTimeInSecond));
                                    i_SharedUtils2.set(i_SharedInt2.NativeAdvanceBannerCounter, Integer.parseInt(NativeAdvanceBannerCounter));
                                    i_SharedUtils2.set(i_SharedInt2.InBetweenListNativeBnr, Integer.parseInt(InBetweenListNativeBnr));

                                } catch (Exception e) {

                                }
                            }
                        });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }

//    public static synchronized MyApplication getInstance() {
//        return mInstance;
//    }

    public void setLocale(String lang) {
        if (lang.equals("")) {
            lang = "en";
        }
        Log.d("Support", lang + "");
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }

    public static void initializeFirebase() {
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        mFirebaseRemoteConfig.setConfigSettingsAsync(new FirebaseRemoteConfigSettings.Builder().setMinimumFetchIntervalInSeconds(0).build());
    }

}
