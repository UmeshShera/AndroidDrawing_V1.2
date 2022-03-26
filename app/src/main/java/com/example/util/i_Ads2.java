package com.example.util;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAd;
import com.google.android.gms.ads.admanager.AdManagerInterstitialAdLoadCallback;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;
import howtodraw.drawing.lessons.art.R;

import static com.example.util.Globals2.Ads_Counter;
import static com.example.util.Globals2.count_Ads_Banner_Native;
import static com.example.util.Globals2.count_Ads_Inter;
import static com.google.android.gms.ads.AdSize.SMART_BANNER;

public class i_Ads2 {

    private static UnityAdsListener myAdsListener;

    public static void ShowAds(Activity activity, Intent intent) {

        Log.e("--VV--", "ShowAds: Intent Firebase " + i_SharedUtils2.get(i_SharedInt2.ADS));
        Log.e("--VV--", "ShowAds Intent : " + i_SharedUtils2.get(i_SharedString2.ADS_Number));

        if (!checkInternetConnected(activity)) {
            return;
        }

        if (i_SharedUtils2.get(i_SharedInt2.ADS_BasedOnTimeInSecond) == 0) {

            Ads_Counter = Ads_Counter + 1;
            if (Ads_Counter % i_SharedUtils2.get(i_SharedInt2.ADS) == 0) {
                if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("0")) {
                    Pthink_Admob_Intent(activity, intent, i_SharedUtils2.get(i_SharedString2.Admob_Interstitial1));
                } else if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("1")) {
                    //Pthink_Facebook_Intent(activity, intent);
                } else if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("2")) {
                    Pthink_AppOpen_Intent(activity, intent);
                } else if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("3")) {
                    Pthink_Unity_Intent(activity, intent);
                } else if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("4")) {
                    if (count_Ads_Inter == 0) {
                        count_Ads_Inter = count_Ads_Inter + 1;
                        Pthink_Admob_Intent(activity, intent, i_SharedUtils2.get(i_SharedString2.Admob_Interstitial1));
                    } else if (count_Ads_Inter == 1) {
                        count_Ads_Inter = count_Ads_Inter + 1;
                        Pthink_AppOpen_Intent(activity, intent);
                    } else if (count_Ads_Inter == 2) {
                        count_Ads_Inter = count_Ads_Inter + 1;
                        Pthink_Unity_Intent(activity, intent);
                    } else {
                        count_Ads_Inter = 0;
                        //Pthink_Facebook_Intent(activity, intent);
                    }
                } else {
                    activity.startActivity(intent);
                }

            } else {
                activity.startActivity(intent);
            }

        } else {

            if (Globals2.timeCounter == 0) {
                countDownTimer();
                if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("0")) {
                    Pthink_Admob_Intent(activity, intent, i_SharedUtils2.get(i_SharedString2.Admob_Interstitial1));
                } else if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("1")) {
                    //Pthink_Facebook_Intent(activity, intent);
                } else if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("2")) {
                    Pthink_AppOpen_Intent(activity, intent);
                } else if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("3")) {
                    Pthink_Unity_Intent(activity, intent);
                } else if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("4")) {
                    if (count_Ads_Inter == 0) {
                        count_Ads_Inter = count_Ads_Inter + 1;
                        Pthink_Admob_Intent(activity, intent, i_SharedUtils2.get(i_SharedString2.Admob_Interstitial1));
                    } else if (count_Ads_Inter == 1) {
                        count_Ads_Inter = count_Ads_Inter + 1;
                        Pthink_AppOpen_Intent(activity, intent);
                    } else if (count_Ads_Inter == 2) {
                        count_Ads_Inter = count_Ads_Inter + 1;
                        Pthink_Unity_Intent(activity, intent);
                    } else {
                        count_Ads_Inter = 0;
                        //Pthink_Facebook_Intent(activity, intent);
                    }
                } else {
                    activity.startActivity(intent);
                }
            } else {
                activity.startActivity(intent);
            }
        }
    }

    public static boolean ShowAds(Activity activity) {
        boolean isConnected = true;
        Log.e("--VV--", "ShowAds: Intent Firebase " + i_SharedUtils2.get(i_SharedInt2.ADS));
        Log.e("--VV--", "ShowAds Intent : " + i_SharedUtils2.get(i_SharedString2.ADS_Number));
        if (checkInternetConnected(activity)) {
            isConnected = true;
        } else {
            isConnected = false;
        }
        if (i_SharedUtils2.get(i_SharedInt2.ADS_BasedOnTimeInSecond) == 0) {
            Ads_Counter = Ads_Counter + 1;
            if (Ads_Counter % i_SharedUtils2.get(i_SharedInt2.ADS) == 0) {

                if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("0")) {
                    Pthink_Admob_Intent(activity, i_SharedUtils2.get(i_SharedString2.Admob_Interstitial1));
                } else if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("1")) {
                    //Pthink_Facebook_Intent(activity);
                } else if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("2")) {
                    Pthink_AppOpen_Intent(activity);
                } else if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("3")) {
                    Pthink_Unity_Intent(activity);
                } else if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("4")) {

                    if (count_Ads_Inter == 0) {
                        count_Ads_Inter = count_Ads_Inter + 1;
                        Pthink_Admob_Intent(activity, i_SharedUtils2.get(i_SharedString2.Admob_Interstitial1));

                    } else if (count_Ads_Inter == 1) {
                        count_Ads_Inter = count_Ads_Inter + 1;
                        Pthink_AppOpen_Intent(activity);
                    } else if (count_Ads_Inter == 2) {
                        count_Ads_Inter = count_Ads_Inter + 1;
                        Pthink_Unity_Intent(activity);
                    } else {
                        count_Ads_Inter = 0;
                        //Pthink_Facebook_Intent(activity);

                    }

                }
            }
        } else {
            if (Globals2.timeCounter == 0) {
                countDownTimer();
                if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("0")) {
                    Pthink_Admob_Intent(activity, i_SharedUtils2.get(i_SharedString2.Admob_Interstitial1));
                } else if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("1")) {
                    //Pthink_Facebook_Intent(activity);
                } else if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("2")) {
                    Pthink_AppOpen_Intent(activity);
                } else if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("3")) {
                    Pthink_Unity_Intent(activity);
                } else if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("4")) {

                    if (count_Ads_Inter == 0) {
                        count_Ads_Inter = count_Ads_Inter + 1;
                        Pthink_Admob_Intent(activity, i_SharedUtils2.get(i_SharedString2.Admob_Interstitial1));

                    } else if (count_Ads_Inter == 1) {
                        count_Ads_Inter = count_Ads_Inter + 1;
                        Pthink_AppOpen_Intent(activity);
                    } else if (count_Ads_Inter == 2) {
                        count_Ads_Inter = count_Ads_Inter + 1;
                        Pthink_Unity_Intent(activity);
                    } else {
                        count_Ads_Inter = 0;
                        //Pthink_Facebook_Intent(activity);
                    }
                }
            }
        }
        return isConnected;
    }

    public static boolean ShowAdsBackPressedFinish(Activity activity) {
        boolean isConnected = true;
        Log.e("--VV--", "ShowAds : " + i_SharedUtils2.get(i_SharedString2.ADS_Number));
        if (checkInternetConnected(activity)) {
            isConnected = true;
        } else {
            isConnected = false;
        }
        if (i_SharedUtils2.get(i_SharedInt2.ADS_BasedOnTimeInSecond) == 0) {
            Ads_Counter = Ads_Counter + 1;
            if (Ads_Counter % i_SharedUtils2.get(i_SharedInt2.ADS) == 0) {
                if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("0")) {
                    Pthink_Admob_Finish(activity, i_SharedUtils2.get(i_SharedString2.Admob_Interstitial1));
                } else if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("1")) {
                    //Pthink_Facebook_Finish(activity);
                } else if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("2")) {
                    Pthink_AppOpen_Finish(activity);
                } else if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("3")) {
                    Pthink_Unity_Finish(activity);
                } else if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("4")) {

                    if (count_Ads_Inter == 0) {
                        count_Ads_Inter = count_Ads_Inter + 1;
                        Pthink_Admob_Finish(activity, i_SharedUtils2.get(i_SharedString2.Admob_Interstitial1));

                    } else if (count_Ads_Inter == 1) {
                        count_Ads_Inter = count_Ads_Inter + 1;
                        Pthink_AppOpen_Finish(activity);
                    } else if (count_Ads_Inter == 2) {
                        count_Ads_Inter = count_Ads_Inter + 1;
                        Pthink_Unity_Finish(activity);
                    } else {
                        count_Ads_Inter = 0;
                        //Pthink_Facebook_Finish(activity);

                    }

                } else {
                    activity.finish();
                }

            } else {
                activity.finish();
            }
        } else {
            if (Globals2.timeCounter == 0) {
                countDownTimer();
                if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("0")) {
                    Pthink_Admob_Finish(activity, i_SharedUtils2.get(i_SharedString2.Admob_Interstitial1));
                } else if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("1")) {
                    //Pthink_Facebook_Finish(activity);
                } else if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("2")) {
                    Pthink_AppOpen_Finish(activity);
                } else if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("3")) {
                    Pthink_Unity_Finish(activity);
                } else if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("4")) {

                    if (count_Ads_Inter == 0) {
                        count_Ads_Inter = count_Ads_Inter + 1;
                        Pthink_Admob_Finish(activity, i_SharedUtils2.get(i_SharedString2.Admob_Interstitial1));

                    } else if (count_Ads_Inter == 1) {
                        count_Ads_Inter = count_Ads_Inter + 1;
                        Pthink_AppOpen_Finish(activity);
                    } else if (count_Ads_Inter == 2) {
                        count_Ads_Inter = count_Ads_Inter + 1;
                        Pthink_Unity_Finish(activity);
                    } else {
                        count_Ads_Inter = 0;
                        //Pthink_Facebook_Finish(activity);

                    }

                } else {
                    activity.finish();
                }
            } else {
                activity.finish();
            }
        }
        return isConnected;
    }

    public static void ShowAdsFinish(Activity activity, Intent intent) {

        Log.e("--VV--", "ShowAds Intent : " + i_SharedUtils2.get(i_SharedString2.ADS_Number));

        if (!checkInternetConnected(activity)) {
            return;
        }

        if (i_SharedUtils2.get(i_SharedInt2.ADS_BasedOnTimeInSecond) == 0) {
            Ads_Counter = Ads_Counter + 1;
            if (Ads_Counter % i_SharedUtils2.get(i_SharedInt2.ADS) == 0) {
                if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("0")) {
                    Pthink_Admob_Intent_Finish(activity, intent, i_SharedUtils2.get(i_SharedString2.Admob_Interstitial1));
                } else if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("1")) {
                    //Pthink_Facebook_Intent_Finish(activity, intent);
                } else if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("2")) {
                    Pthink_AppOpen_Intent_Finish(activity, intent);
                } else if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("3")) {
                    Pthink_Unity_Intent_Finish(activity, intent);
                } else if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("4")) {

                    if (count_Ads_Inter == 0) {
                        count_Ads_Inter = count_Ads_Inter + 1;
                        Pthink_Admob_Intent_Finish(activity, intent, i_SharedUtils2.get(i_SharedString2.Admob_Interstitial1));

                    } else if (count_Ads_Inter == 1) {
                        count_Ads_Inter = count_Ads_Inter + 1;
                        Pthink_AppOpen_Intent_Finish(activity, intent);
                    } else if (count_Ads_Inter == 2) {
                        count_Ads_Inter = count_Ads_Inter + 1;
                        Pthink_Unity_Intent_Finish(activity, intent);
                    } else {
                        count_Ads_Inter = 0;
                        //Pthink_Facebook_Intent_Finish(activity, intent);

                    }
                } else {
                    activity.startActivity(intent);
                    activity.finish();
                }
            } else {
                activity.startActivity(intent);
                activity.finish();
            }
        } else {

            if (Globals2.timeCounter == 0) {
                if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("0")) {
                    Pthink_Admob_Intent_Finish(activity, intent, i_SharedUtils2.get(i_SharedString2.Admob_Interstitial1));
                } else if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("1")) {
                    //Pthink_Facebook_Intent_Finish(activity, intent);
                } else if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("2")) {
                    Pthink_AppOpen_Intent_Finish(activity, intent);
                } else if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("3")) {
                    Pthink_Unity_Intent_Finish(activity, intent);
                } else if (i_SharedUtils2.get(i_SharedString2.ADS_Number).equals("4")) {

                    if (count_Ads_Inter == 0) {
                        count_Ads_Inter = count_Ads_Inter + 1;
                        Pthink_Admob_Intent_Finish(activity, intent, i_SharedUtils2.get(i_SharedString2.Admob_Interstitial1));

                    } else if (count_Ads_Inter == 1) {
                        count_Ads_Inter = count_Ads_Inter + 1;
                        Pthink_AppOpen_Intent_Finish(activity, intent);
                    } else if (count_Ads_Inter == 2) {
                        count_Ads_Inter = count_Ads_Inter + 1;
                        Pthink_Unity_Intent_Finish(activity, intent);
                    } else {
                        count_Ads_Inter = 0;
                        //Pthink_Facebook_Intent_Finish(activity, intent);

                    }
                } else {
                    activity.startActivity(intent);
                    activity.finish();
                }
            } else {
                activity.startActivity(intent);
                activity.finish();
            }
        }

    }

    public static void countDownTimer() {

        try {
            new CountDownTimer(i_SharedUtils2.get(i_SharedInt2.ADS_BasedOnTimeInSecond) * 1000L, 1000) {

                public void onTick(long millisUntilFinished) {
                    Globals2.timeCounter = millisUntilFinished / 1000;
                    Log.e("----TimeCounter----", "remaining:" + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    Globals2.timeCounter = 0;
                    Log.e("----TimeCounter----", "Completed:");
                }
            }.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void NativeBigAdsWithCounter(Activity context, final FrameLayout frameLayout) {

        Globals2.NativeAdvanceBannerCounter = Globals2.NativeAdvanceBannerCounter + 1;
        if (Globals2.NativeAdvanceBannerCounter % i_SharedUtils2.get(i_SharedInt2.NativeAdvanceBannerCounter) == 0) {
        } else {
            frameLayout.setVisibility(View.GONE);
        }
    }

    public static void NativeBiGAds(final Activity context, FrameLayout frameLayout) {

        try {
            Log.e("--VV--", "banner_native_ads: " + i_SharedUtils2.get(i_SharedString2.banner_native_ads));

            if (i_SharedUtils2.get(i_SharedString2.banner_native_ads).equals("0")) {


                AdLoader.Builder builder = new AdLoader.Builder(context, i_SharedUtils2.get(i_SharedString2.Admob_Native));

                builder.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {

                    private com.google.android.gms.ads.nativead.NativeAd nativeAd;

                    // OnUnifiedNativeAdLoadedListener implementation.
                    @Override
                    public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAds) {
                        // You must call destroy on old ads when you are done with them,
                        // otherwise you will have a memory leak.
                        if (nativeAd != null) {
                            nativeAd.destroy();
                        }
                        nativeAd = nativeAds;

                        View view = context.getLayoutInflater()
                                .inflate(R.layout.ad_unified, null);

                        NativeAdView adView = view.findViewById(R.id.uadview);

                        adView.setMediaView(adView.findViewById(R.id.ad_media));
                        adView.getMediaView().setImageScaleType(ImageView.ScaleType.FIT_XY);

                        // Set other ad assets.
                        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
                        adView.setBodyView(adView.findViewById(R.id.ad_body));
                        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
                        adView.setIconView(adView.findViewById(R.id.ad_app_icon));


                        // The headline and mediaContent are guaranteed to be in every UnifiedNativeAd.
                        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
                        //                    adView.getMediaView().setMediaContent(nativeAd.getMediaContent());

                        // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
                        // check before trying to display them.
                        if (nativeAd.getBody() == null) {
                            adView.getBodyView().setVisibility(View.INVISIBLE);
                        } else {
                            adView.getBodyView().setVisibility(View.VISIBLE);
                            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
                        }

                        if (nativeAd.getCallToAction() == null) {
                            adView.getCallToActionView().setVisibility(View.INVISIBLE);
                        } else {
                            adView.getCallToActionView().setVisibility(View.VISIBLE);
                            ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
                        }

                        if (nativeAd.getIcon() == null) {
                            adView.getIconView().setVisibility(View.GONE);
                        } else {
                            ((ImageView) adView.getIconView()).setImageDrawable(
                                    nativeAd.getIcon().getDrawable());
                            adView.getIconView().setVisibility(View.VISIBLE);
                        }

                        // This method tells the Google Mobile Ads SDK that you have finished populating your
                        // native ad view with this native ad.
                        adView.setNativeAd(nativeAd);

                        frameLayout.removeAllViews();
                        frameLayout.addView(view);

                        frameLayout.setVisibility(View.VISIBLE);

                    }

                });

                VideoOptions videoOptions = new VideoOptions.Builder()
                        .setStartMuted(true)
                        .build();

                NativeAdOptions adOptions = new NativeAdOptions.Builder()
                        .setVideoOptions(videoOptions)
                        .build();

                builder.withNativeAdOptions(adOptions);

                AdLoader adLoader = builder.withAdListener(new com.google.android.gms.ads.AdListener() {

                    @Override
                    public void onAdFailedToLoad(LoadAdError errorCode) {


                    }
                }).build();

                adLoader.loadAd(new AdRequest.Builder().build());

            } else {
                count_Ads_Banner_Native = count_Ads_Banner_Native + 1;
                if (count_Ads_Banner_Native % 2 == 0) {

                    AdLoader.Builder builder = new AdLoader.Builder(context, i_SharedUtils2.get(i_SharedString2.Admob_Native));

                    builder.forNativeAd(new com.google.android.gms.ads.nativead.NativeAd.OnNativeAdLoadedListener() {
                        private com.google.android.gms.ads.nativead.NativeAd nativeAd;

                        // OnUnifiedNativeAdLoadedListener implementation.
                        @Override
                        public void onNativeAdLoaded(com.google.android.gms.ads.nativead.NativeAd nativeAds) {
                            // You must call destroy on old ads when you are done with them,
                            // otherwise you will have a memory leak.
                            if (nativeAd != null) {
                                nativeAd.destroy();
                            }
                            nativeAd = nativeAds;

                            View view = context.getLayoutInflater()
                                    .inflate(R.layout.ad_unified, null);

                            NativeAdView adView = view.findViewById(R.id.uadview);

                            adView.setMediaView(adView.findViewById(R.id.ad_media));
                            adView.getMediaView().setImageScaleType(ImageView.ScaleType.FIT_XY);

                            // Set other ad assets.
                            adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
                            adView.setBodyView(adView.findViewById(R.id.ad_body));
                            adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
                            adView.setIconView(adView.findViewById(R.id.ad_app_icon));


                            // The headline and mediaContent are guaranteed to be in every UnifiedNativeAd.
                            ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
                            //                    adView.getMediaView().setMediaContent(nativeAd.getMediaContent());

                            // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
                            // check before trying to display them.
                            if (nativeAd.getBody() == null) {
                                adView.getBodyView().setVisibility(View.INVISIBLE);
                            } else {
                                adView.getBodyView().setVisibility(View.VISIBLE);
                                ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
                            }

                            if (nativeAd.getCallToAction() == null) {
                                adView.getCallToActionView().setVisibility(View.INVISIBLE);
                            } else {
                                adView.getCallToActionView().setVisibility(View.VISIBLE);
                                ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
                            }

                            if (nativeAd.getIcon() == null) {
                                adView.getIconView().setVisibility(View.GONE);
                            } else {
                                ((ImageView) adView.getIconView()).setImageDrawable(
                                        nativeAd.getIcon().getDrawable());
                                adView.getIconView().setVisibility(View.VISIBLE);
                            }

                            // This method tells the Google Mobile Ads SDK that you have finished populating your
                            // native ad view with this native ad.
                            adView.setNativeAd(nativeAd);

                            frameLayout.removeAllViews();
                            frameLayout.addView(view);

                            frameLayout.setVisibility(View.VISIBLE);

                        }

                    });

                    VideoOptions videoOptions = new VideoOptions.Builder()
                            .setStartMuted(true)
                            .build();

                    NativeAdOptions adOptions = new NativeAdOptions.Builder()
                            .setVideoOptions(videoOptions)
                            .build();

                    builder.withNativeAdOptions(adOptions);

                    AdLoader adLoader = builder.withAdListener(new com.google.android.gms.ads.AdListener() {


                        @Override
                        public void onAdFailedToLoad(LoadAdError errorCode) {


                        }
                    }).build();

                    adLoader.loadAd(new AdRequest.Builder().build());

                } else {

                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void PthinkFacebookBannerSmall(final Activity context, final LinearLayout frameLayout) {

        if (i_SharedUtils2.get(i_SharedString2.banner_native_ads).equals("0")) {

            AdView adView = new AdView(context);
            adView.setAdSize(SMART_BANNER);
            adView.setAdUnitId(i_SharedUtils2.get(i_SharedString2.Admob_Banner));
            adView.setAdListener(new com.google.android.gms.ads.AdListener() {
                @Override
                public void onAdLoaded() {
                    // Code to be executed when an ad finishes loading.
                    frameLayout.removeAllViews();
                    frameLayout.addView(adView);
                }

                @Override
                public void onAdFailedToLoad(LoadAdError loadAdError) {

                }

                @Override
                public void onAdOpened() {
                    // Code to be executed when an ad opens an overlay that
                    // covers the screen.
                }

                @Override
                public void onAdClicked() {
                    // Code to be executed when the user clicks on an ad.
                }

                @Override
                public void onAdClosed() {
                    // Code to be executed when the user is about to return
                    // to the app after tapping on an ad.
                }
            });
            AdRequest request = new AdRequest.Builder().build();
            adView.loadAd(request);

        } else if (i_SharedUtils2.get(i_SharedString2.banner_native_ads).equals("1")) {

        } else {
            count_Ads_Banner_Native = count_Ads_Banner_Native + 1;
            if (count_Ads_Banner_Native % 2 == 0) {
                AdView adView = new AdView(context);
                adView.setAdSize(SMART_BANNER);
                adView.setAdUnitId(i_SharedUtils2.get(i_SharedString2.Admob_Banner));
                adView.setAdListener(new com.google.android.gms.ads.AdListener() {
                    @Override
                    public void onAdLoaded() {
                        // Code to be executed when an ad finishes loading.
                        frameLayout.removeAllViews();
                        frameLayout.addView(adView);
                    }

                    @Override
                    public void onAdFailedToLoad(LoadAdError loadAdError) {


                    }

                    @Override
                    public void onAdOpened() {
                        // Code to be executed when an ad opens an overlay that
                        // covers the screen.
                    }

                    @Override
                    public void onAdClicked() {
                        // Code to be executed when the user clicks on an ad.
                    }


                    @Override
                    public void onAdClosed() {
                        // Code to be executed when the user is about to return
                        // to the app after tapping on an ad.
                    }
                });
                AdRequest request = new AdRequest.Builder().build();
                adView.loadAd(request);

            } else {

            }
        }
    }

    // Activity Change Intent

    public static void Pthink_Admob_Intent(Activity context, Intent intent, String interstitial) {

        final ProgressDialog customProgressDialog = new ProgressDialog(context, "Display Ad...");
        customProgressDialog.setCancelable(false);
        customProgressDialog.show();

        AdManagerAdRequest adRequest = new AdManagerAdRequest.Builder().build();

        AdManagerInterstitialAd.load(context, interstitial, adRequest, new AdManagerInterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull AdManagerInterstitialAd interstitialAd) {
                // The mAdManagerInterstitialAd reference will be null until
                // an ad is loaded.
                Log.e("--VV--", "Admob onAdLoaded");

                try {
                    customProgressDialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                interstitialAd.show(context);


                interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        // Called when fullscreen content is dismissed.
                        Log.e("--VV--", "Admob The ad was dismissed.");
                        context.startActivity(intent);
                    }


                    @Override
                    public void onAdShowedFullScreenContent() {
                        // Called when fullscreen content is shown.
                        // Make sure to set your reference to null so you don't
                        // show it a second time.
                    }
                });

            }


            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                Log.e("--VV--", "Admob Error" + loadAdError.getMessage());

                try {
                    customProgressDialog.dismiss();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (!Globals2.isNetworkConnected(context) &&
                        Integer.parseInt(i_SharedUtils2.get(i_SharedString2.isInternetConnected)) == 0) {
                    context.startActivity(intent);
                    return;
                }

                if (interstitial.equalsIgnoreCase(i_SharedUtils2.get(i_SharedString2.Admob_Interstitial1)) &&
                        !(i_SharedUtils2.get(i_SharedString2.Admob_Interstitial2).isEmpty())) {
                    Log.e("--VV--", "Admob Interstitial2 Loaded");
                    Pthink_Admob_Intent(context, intent, i_SharedUtils2.get(i_SharedString2.Admob_Interstitial2));
                } else if (interstitial.equalsIgnoreCase(i_SharedUtils2.get(i_SharedString2.Admob_Interstitial2)) &&
                        !(i_SharedUtils2.get(i_SharedString2.Admob_Interstitial3).isEmpty())) {
                    Log.e("--VV--", "Admob Interstitial3 Loaded");
                    Pthink_Admob_Intent(context, intent, i_SharedUtils2.get(i_SharedString2.Admob_Interstitial3));
                } else if (interstitial.equalsIgnoreCase(i_SharedUtils2.get(i_SharedString2.Admob_Interstitial3)) &&
                        !(i_SharedUtils2.get(i_SharedString2.Admob_Interstitial4).isEmpty())) {
                    Log.e("--VV--", "Admob Interstitial4 Loaded");
                    Pthink_Admob_Intent(context, intent, i_SharedUtils2.get(i_SharedString2.Admob_Interstitial4));
                } else if (interstitial.equalsIgnoreCase(i_SharedUtils2.get(i_SharedString2.Admob_Interstitial4)) &&
                        !(i_SharedUtils2.get(i_SharedString2.Admob_Interstitial5).isEmpty())) {
                    Log.e("--VV--", "Admob Interstitial5 Loaded");
                    Pthink_Admob_Intent(context, intent, i_SharedUtils2.get(i_SharedString2.Admob_Interstitial5));
                } else {
                    if (UnityAds.isReady()) {
                        context.startActivity(intent);
                        UnityAds.show(context);
                    } else {
                        context.startActivity(intent);
                    }
                }
            }
        });

    }

    public static void Pthink_AppOpen_Intent(Activity context, Intent intent) {

        final ProgressDialog customProgressDialog = new ProgressDialog(context, "Display Ad...");
        customProgressDialog.setCancelable(false);
        customProgressDialog.show();

        FullScreenContentCallback fullScreenContentCallback = new FullScreenContentCallback() {
            @Override
            public void onAdDismissedFullScreenContent() {
                context.startActivity(intent);
            }

            @Override
            public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                super.onAdFailedToShowFullScreenContent(adError);
                context.startActivity(intent);
            }

            @Override
            public void onAdShowedFullScreenContent() {
            }
        };

        AppOpenAd.AppOpenAdLoadCallback loadCallback = new AppOpenAd.AppOpenAdLoadCallback() {

            @Override
            public void onAdLoaded(@NonNull AppOpenAd appOpenAd) {
                super.onAdLoaded(appOpenAd);
                Log.e("--VV--", "appOpen onAdLoaded: ");
                try {
                    customProgressDialog.dismiss();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                appOpenAd.setFullScreenContentCallback(fullScreenContentCallback);
                appOpenAd.show(context);
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Log.e("--VV--", "appOpen onAdFailedToLoad: ");
                if (UnityAds.isReady()) {
                    customProgressDialog.dismiss();
                    context.startActivity(intent);
                    UnityAds.show(context);
                } else {
                    customProgressDialog.dismiss();
                    context.startActivity(intent);
                }
            }
        };

        AppOpenAd.load(context, i_SharedUtils2.get(i_SharedString2.Admob_Appopen), new AdRequest.Builder().build(),
                AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, loadCallback);

    }

    public static void Pthink_Unity_Intent(Activity context, Intent intent) {
        if (UnityAds.isReady()) {
            context.startActivity(intent);
            UnityAds.show(context);
        } else {
            context.startActivity(intent);
        }
    }

    // Only Show Ads without Intent

    public static void Pthink_Admob_Intent(Activity context, String interstitial) {

        final ProgressDialog customProgressDialog = new ProgressDialog(context, "Display Ad...");
        customProgressDialog.setCancelable(false);
        customProgressDialog.show();

        AdManagerAdRequest adRequest = new AdManagerAdRequest.Builder().build();

        AdManagerInterstitialAd.load(context, interstitial, adRequest, new AdManagerInterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull AdManagerInterstitialAd interstitialAd) {
                // The mAdManagerInterstitialAd reference will be null until
                // an ad is loaded.
                Log.e("--VV--", "Admob onAdLoaded");

                try {
                    customProgressDialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                interstitialAd.show(context);


                interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        // Called when fullscreen content is dismissed.
                        Log.e("--VV--", "Admob The ad was dismissed.");
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        // Called when fullscreen content is shown.
                        // Make sure to set your reference to null so you don't
                        // show it a second time.
                    }
                });

            }


            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                Log.e("--VV--", "Admob Error" + loadAdError.getMessage());

                try {
                    customProgressDialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (!Globals2.isNetworkConnected(context) &&
                        Integer.parseInt(i_SharedUtils2.get(i_SharedString2.isInternetConnected)) == 0) {
                    return;
                }

                if (interstitial.equalsIgnoreCase(i_SharedUtils2.get(i_SharedString2.Admob_Interstitial1)) &&
                        !(i_SharedUtils2.get(i_SharedString2.Admob_Interstitial2).isEmpty())) {
                    Log.e("--VV--", "Admob Interstitial2 Loaded");
                    Pthink_Admob_Intent(context, i_SharedUtils2.get(i_SharedString2.Admob_Interstitial2));
                } else if (interstitial.equalsIgnoreCase(i_SharedUtils2.get(i_SharedString2.Admob_Interstitial2)) &&
                        !(i_SharedUtils2.get(i_SharedString2.Admob_Interstitial3).isEmpty())) {
                    Log.e("--VV--", "Admob Interstitial3 Loaded");
                    Pthink_Admob_Intent(context, i_SharedUtils2.get(i_SharedString2.Admob_Interstitial3));
                } else if (interstitial.equalsIgnoreCase(i_SharedUtils2.get(i_SharedString2.Admob_Interstitial3)) &&
                        !(i_SharedUtils2.get(i_SharedString2.Admob_Interstitial4).isEmpty())) {
                    Log.e("--VV--", "Admob Interstitial4 Loaded");
                    Pthink_Admob_Intent(context, i_SharedUtils2.get(i_SharedString2.Admob_Interstitial4));
                } else if (interstitial.equalsIgnoreCase(i_SharedUtils2.get(i_SharedString2.Admob_Interstitial4)) &&
                        !(i_SharedUtils2.get(i_SharedString2.Admob_Interstitial5).isEmpty())) {
                    Log.e("--VV--", "Admob Interstitial5 Loaded");
                    Pthink_Admob_Intent(context, i_SharedUtils2.get(i_SharedString2.Admob_Interstitial5));
                } else {
                    if (UnityAds.isReady()) {
                        UnityAds.show(context);
                    }
                }

            }
        });

    }

    public static void Pthink_AppOpen_Intent(Activity context) {

        final ProgressDialog customProgressDialog = new ProgressDialog(context, "Display Ad...");
        customProgressDialog.setCancelable(false);
        customProgressDialog.show();

        FullScreenContentCallback fullScreenContentCallback = new FullScreenContentCallback() {
            @Override
            public void onAdDismissedFullScreenContent() {
            }

            @Override
            public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                super.onAdFailedToShowFullScreenContent(adError);
            }

            @Override
            public void onAdShowedFullScreenContent() {
            }
        };

        AppOpenAd.AppOpenAdLoadCallback loadCallback = new AppOpenAd.AppOpenAdLoadCallback() {

            @Override
            public void onAdLoaded(@NonNull AppOpenAd appOpenAd) {
                super.onAdLoaded(appOpenAd);
                Log.e("--VV--", "appOpen onAdLoaded: ");
                try {
                    customProgressDialog.dismiss();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                appOpenAd.setFullScreenContentCallback(fullScreenContentCallback);
                appOpenAd.show(context);
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Log.e("--VV--", "appOpen onAdFailedToLoad: ");
                if (UnityAds.isReady()) {
                    customProgressDialog.dismiss();
                    UnityAds.show(context);
                } else {
                    customProgressDialog.dismiss();
                }
            }
        };

        AppOpenAd.load(context, i_SharedUtils2.get(i_SharedString2.Admob_Appopen), new AdRequest.Builder().build(),
                AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, loadCallback);
    }

    public static void Pthink_Unity_Intent(Activity context) {
        if (UnityAds.isReady()) {
            UnityAds.show(context);
        }
    }

    // Only Finish

    public static void Pthink_Admob_Finish(Activity context, String interstitial) {

        final ProgressDialog customProgressDialog = new ProgressDialog(context, "Display Ad...");
        customProgressDialog.setCancelable(false);
        customProgressDialog.show();

        AdManagerAdRequest adRequest = new AdManagerAdRequest.Builder().build();

        AdManagerInterstitialAd.load(context, interstitial, adRequest, new AdManagerInterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull AdManagerInterstitialAd interstitialAd) {
                // The mAdManagerInterstitialAd reference will be null until
                // an ad is loaded.
                Log.e("--VV--", "Admob onAdLoaded");

                try {
                    customProgressDialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                interstitialAd.show(context);


                interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        // Called when fullscreen content is dismissed.
                        Log.e("--VV--", "Admob The ad was dismissed.");
                        context.finish();
                    }


                    @Override
                    public void onAdShowedFullScreenContent() {
                        // Called when fullscreen content is shown.
                        // Make sure to set your reference to null so you don't
                        // show it a second time.
                    }
                });

            }


            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                Log.e("--VV--", "Admob Error" + loadAdError.getMessage());

                try {
                    customProgressDialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (!Globals2.isNetworkConnected(context) &&
                        Integer.parseInt(i_SharedUtils2.get(i_SharedString2.isInternetConnected)) == 0) {
                    context.finish();
                    return;
                }

                if (interstitial.equalsIgnoreCase(i_SharedUtils2.get(i_SharedString2.Admob_Interstitial1)) &&
                        !(i_SharedUtils2.get(i_SharedString2.Admob_Interstitial2).isEmpty())) {
                    Log.e("--VV--", "Admob Interstitial2 Loaded");
                    Pthink_Admob_Finish(context, i_SharedUtils2.get(i_SharedString2.Admob_Interstitial2));
                } else if (interstitial.equalsIgnoreCase(i_SharedUtils2.get(i_SharedString2.Admob_Interstitial2)) &&
                        !(i_SharedUtils2.get(i_SharedString2.Admob_Interstitial3).isEmpty())) {
                    Log.e("--VV--", "Admob Interstitial3 Loaded");
                    Pthink_Admob_Finish(context, i_SharedUtils2.get(i_SharedString2.Admob_Interstitial3));
                } else if (interstitial.equalsIgnoreCase(i_SharedUtils2.get(i_SharedString2.Admob_Interstitial3)) &&
                        !(i_SharedUtils2.get(i_SharedString2.Admob_Interstitial4).isEmpty())) {
                    Log.e("--VV--", "Admob Interstitial4 Loaded");
                    Pthink_Admob_Finish(context, i_SharedUtils2.get(i_SharedString2.Admob_Interstitial4));
                } else if (interstitial.equalsIgnoreCase(i_SharedUtils2.get(i_SharedString2.Admob_Interstitial4)) &&
                        !(i_SharedUtils2.get(i_SharedString2.Admob_Interstitial5).isEmpty())) {
                    Log.e("--VV--", "Admob Interstitial5 Loaded");
                    Pthink_Admob_Finish(context, i_SharedUtils2.get(i_SharedString2.Admob_Interstitial5));
                } else {
                    if (UnityAds.isReady()) {
                        UnityAds.show(context);
                        context.finish();
                    } else {
                        context.finish();
                    }
                }
            }
        });

    }

    public static void Pthink_AppOpen_Finish(Activity context) {

        final ProgressDialog customProgressDialog = new ProgressDialog(context, "Display Ad...");
        customProgressDialog.setCancelable(false);
        customProgressDialog.show();

        FullScreenContentCallback fullScreenContentCallback = new FullScreenContentCallback() {
            @Override
            public void onAdDismissedFullScreenContent() {
                context.finish();
            }

            @Override
            public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                super.onAdFailedToShowFullScreenContent(adError);
                context.finish();
            }

            @Override
            public void onAdShowedFullScreenContent() {
            }
        };

        AppOpenAd.AppOpenAdLoadCallback loadCallback = new AppOpenAd.AppOpenAdLoadCallback() {

            @Override
            public void onAdLoaded(@NonNull AppOpenAd appOpenAd) {
                super.onAdLoaded(appOpenAd);
                Log.e("--VV--", "appOpen onAdLoaded: ");
                try {
                    customProgressDialog.dismiss();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                appOpenAd.setFullScreenContentCallback(fullScreenContentCallback);
                appOpenAd.show(context);
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Log.e("--VV--", "appOpen onAdFailedToLoad: ");
                if (UnityAds.isReady()) {
                    customProgressDialog.dismiss();
                    UnityAds.show(context);
                    context.finish();
                } else {
                    customProgressDialog.dismiss();
                    context.finish();
                }
            }
        };

        AppOpenAd.load(context, i_SharedUtils2.get(i_SharedString2.Admob_Appopen), new AdRequest.Builder().build(),
                AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, loadCallback);
    }

    public static void Pthink_Unity_Finish(Activity context) {
        if (UnityAds.isReady()) {
            UnityAds.show(context);
            context.finish();
        } else {
            context.finish();
        }
    }

    // Intent With Finish

    public static void Pthink_Admob_Intent_Finish(Activity context, Intent intent, String intertitial) {

        final ProgressDialog customProgressDialog = new ProgressDialog(context, "Display Ad...");
        customProgressDialog.setCancelable(false);
        customProgressDialog.show();

        AdManagerAdRequest adRequest = new AdManagerAdRequest.Builder().build();

        AdManagerInterstitialAd.load(context, intertitial, adRequest, new AdManagerInterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull AdManagerInterstitialAd interstitialAd) {
                // The mAdManagerInterstitialAd reference will be null until
                // an ad is loaded.
                Log.e("--VV--", "Admob onAdLoaded");

                try {
                    customProgressDialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                interstitialAd.show(context);


                interstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        // Called when fullscreen content is dismissed.
                        Log.e("--VV--", "Admob The ad was dismissed.");
                        context.startActivity(intent);
                        context.finish();
                    }


                    @Override
                    public void onAdShowedFullScreenContent() {
                        // Called when fullscreen content is shown.
                        // Make sure to set your reference to null so you don't
                        // show it a second time.

                    }
                });

            }


            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                Log.e("--VV--", "Admob Error" + loadAdError.getMessage());

                try {
                    customProgressDialog.dismiss();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (!Globals2.isNetworkConnected(context) &&
                        Integer.parseInt(i_SharedUtils2.get(i_SharedString2.isInternetConnected)) == 0) {
                    context.startActivity(intent);
                    context.finish();
                    return;
                }

                if (intertitial.equalsIgnoreCase(i_SharedUtils2.get(i_SharedString2.Admob_Interstitial1)) &&
                        !(i_SharedUtils2.get(i_SharedString2.Admob_Interstitial2).isEmpty())) {
                    Log.e("--VV--", "Admob Interstitial2 Loaded");
                    Pthink_Admob_Intent_Finish(context, intent, i_SharedUtils2.get(i_SharedString2.Admob_Interstitial2));
                } else if (intertitial.equalsIgnoreCase(i_SharedUtils2.get(i_SharedString2.Admob_Interstitial2)) &&
                        !(i_SharedUtils2.get(i_SharedString2.Admob_Interstitial3).isEmpty())) {
                    Log.e("--VV--", "Admob Interstitial3 Loaded");
                    Pthink_Admob_Intent_Finish(context, intent, i_SharedUtils2.get(i_SharedString2.Admob_Interstitial3));
                } else if (intertitial.equalsIgnoreCase(i_SharedUtils2.get(i_SharedString2.Admob_Interstitial3)) &&
                        !(i_SharedUtils2.get(i_SharedString2.Admob_Interstitial4).isEmpty())) {
                    Log.e("--VV--", "Admob Interstitial4 Loaded");
                    Pthink_Admob_Intent_Finish(context, intent, i_SharedUtils2.get(i_SharedString2.Admob_Interstitial4));
                } else if (intertitial.equalsIgnoreCase(i_SharedUtils2.get(i_SharedString2.Admob_Interstitial4)) &&
                        !(i_SharedUtils2.get(i_SharedString2.Admob_Interstitial5).isEmpty())) {
                    Log.e("--VV--", "Admob Interstitial5 Loaded");
                    Pthink_Admob_Intent_Finish(context, intent, i_SharedUtils2.get(i_SharedString2.Admob_Interstitial5));
                } else {
                    if (UnityAds.isReady()) {
                        context.startActivity(intent);
                        UnityAds.show(context);
                        context.finish();
                    } else {
                        context.startActivity(intent);
                        context.finish();
                    }
                }
            }
        });


    }

    public static void Pthink_AppOpen_Intent_Finish(Activity context, Intent intent) {

        final ProgressDialog customProgressDialog = new ProgressDialog(context, "Display Ad...");
        customProgressDialog.setCancelable(false);
        customProgressDialog.show();

        FullScreenContentCallback fullScreenContentCallback = new FullScreenContentCallback() {
            @Override
            public void onAdDismissedFullScreenContent() {
                context.startActivity(intent);
                context.finish();
            }

            @Override
            public void onAdFailedToShowFullScreenContent(com.google.android.gms.ads.AdError adError) {
                super.onAdFailedToShowFullScreenContent(adError);
                context.startActivity(intent);
                context.finish();
            }

            @Override
            public void onAdShowedFullScreenContent() {
            }
        };

        AppOpenAd.AppOpenAdLoadCallback loadCallback = new AppOpenAd.AppOpenAdLoadCallback() {

            @Override
            public void onAdLoaded(@NonNull AppOpenAd appOpenAd) {
                super.onAdLoaded(appOpenAd);
                Log.e("--VV--", "appOpen onAdLoaded: ");
                try {
                    customProgressDialog.dismiss();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                appOpenAd.setFullScreenContentCallback(fullScreenContentCallback);
                appOpenAd.show(context);


            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Log.e("--VV--", "appOpen onAdFailedToLoad: ");
                if (UnityAds.isReady()) {
                    customProgressDialog.dismiss();
                    context.startActivity(intent);
                    UnityAds.show(context);
                    context.finish();
                } else {
                    customProgressDialog.dismiss();
                    context.startActivity(intent);
                    context.finish();
                }

            }
        };

        AppOpenAd.load(context, i_SharedUtils2.get(i_SharedString2.Admob_Appopen), new AdRequest.Builder().build(),
                AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, loadCallback);
    }

    public static void Pthink_Unity_Intent_Finish(Activity context, Intent intent) {
        if (UnityAds.isReady()) {
            context.startActivity(intent);
            UnityAds.show(context);
            context.finish();
        } else {
            context.startActivity(intent);
            context.finish();
        }
    }

    public static void Load_Unity_Ads(Activity context) {

        myAdsListener = new UnityAdsListener();
        UnityAds.addListener(myAdsListener);
        UnityAds.initialize(context, i_SharedUtils2.get(i_SharedString2.Unity_ID));

        Log.e("--VV--", "Unity ID : " + i_SharedUtils2.get(i_SharedString2.Unity_ID));

    }

    private static class UnityAdsListener implements IUnityAdsListener {

        @Override
        public void onUnityAdsReady(String adUnitId) {
            Log.e("--VV--", "onUnityAdsReady: " + adUnitId);
        }

        @Override
        public void onUnityAdsStart(String adUnitId) {
            Log.e("--VV--", "onUnityAdsStart: " + adUnitId);
        }

        @Override
        public void onUnityAdsFinish(String adUnitId, UnityAds.FinishState finishState) {
            Log.e("--VV--", "onUnityAdsFinish: " + adUnitId);
        }

        @Override
        public void onUnityAdsError(UnityAds.UnityAdsError error, String message) {
            Log.e("--VV--", "onUnityAdsError: " + error + " MSG :  " + message);
        }
    }

    private static boolean checkInternetConnected(Activity activity) {
        boolean isnotconnected;
        int internetConnected = Integer.parseInt(i_SharedUtils2.get(i_SharedString2.isInternetConnected));
        if (internetConnected != 0 && !(Globals2.isNetworkConnected(activity))) {
            isnotconnected = false;
            Globals2.showNetConnectionDialog(activity, false);
        } else {
            isnotconnected = true;
        }

        return isnotconnected;
    }
}
