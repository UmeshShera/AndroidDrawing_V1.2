package com.example.androiddrawing;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.util.i_Ads2;

import howtodraw.drawing.lessons.art.R;

public class AdmobbanerAdAdapterILauncher extends RecyclerViewAdapterWrapper_Launcher {

    private static final int TYPE_FB_NATIVE_ADS = 900;
    private static final int DEFAULT_AD_ITEM_INTERVAL = 4;

    private static Param mParam;
    private static Activity activity;
//    private static NativeAd nativeAd;

    private AdmobbanerAdAdapterILauncher(Param param) {
        super(param.adapter);
        this.mParam = param;

        assertConfig();
        setSpanAds();
    }

    private void assertConfig() {
        if (mParam.gridLayoutManager != null) {
            //if user set span ads
            int nCol = mParam.gridLayoutManager.getSpanCount();
            if (mParam.adItemInterval % nCol != 0) {
                throw new IllegalArgumentException(String.format("The adItemInterval (%d) is not divisible by number of columns in GridLayoutManager (%d)", mParam.adItemInterval, nCol));
            }
        }
    }

    private int convertAdPosition2OrgPosition(int position) {
        return position - (position + 1) / (mParam.adItemInterval + 1);
    }

    @Override
    public int getItemCount() {
        int realCount = super.getItemCount();
        return realCount + realCount / mParam.adItemInterval;
    }

    @Override
    public int getItemViewType(int position) {
        if (isAdPosition(position)) {
            return TYPE_FB_NATIVE_ADS;
        }
        return super.getItemViewType(convertAdPosition2OrgPosition(position));
    }

    private boolean isAdPosition(int position) {
        return (position + 1) % (mParam.adItemInterval + 1) == 0;
    }

    private void onBindAdViewHolder(final RecyclerView.ViewHolder holder) {
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_FB_NATIVE_ADS) {
            onBindAdViewHolder(holder);
        } else {
            super.onBindViewHolder(holder, convertAdPosition2OrgPosition(position));
        }
    }

    private RecyclerView.ViewHolder onCreateAdViewHolder(ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View adLayoutOutline = inflater
                .inflate(mParam.itemContainerLayoutRes, parent, false);
        ViewGroup vg = adLayoutOutline.findViewById(mParam.itemContainerId);

        LinearLayout adLayoutContent = (LinearLayout) inflater
                .inflate(R.layout.item_facebook_native_ad_launcher, parent, false);
        vg.addView(adLayoutContent);
        return new AdViewHolder(adLayoutOutline);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FB_NATIVE_ADS) {
            return onCreateAdViewHolder(parent);
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    private void setSpanAds() {
        if (mParam.gridLayoutManager == null) {
            return;
        }
        final GridLayoutManager.SpanSizeLookup spl = mParam.gridLayoutManager.getSpanSizeLookup();
        mParam.gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (isAdPosition(position)) {
                    return spl.getSpanSize(position);
                }
                return 1;
            }
        });
    }

    private static class Param {
        RecyclerView.Adapter adapter;
        int adItemInterval;
        boolean forceReloadAdOnBind;

        @LayoutRes
        int itemContainerLayoutRes;

        @IdRes
        int itemContainerId;

        GridLayoutManager gridLayoutManager;
    }

    public static class Builder {
        private final Param mParam;

        private Builder(Param param) {
            mParam = param;
        }

        public static Builder with(RecyclerView.Adapter wrapped, Activity _iLauncher_mainActivity) {
            Param param = new Param();
            param.adapter = wrapped;
            activity = _iLauncher_mainActivity;

            //default value
            param.adItemInterval = DEFAULT_AD_ITEM_INTERVAL;
            param.itemContainerLayoutRes = R.layout.item_facebook_native_ad_outline_launcher;
            param.itemContainerId = R.id.ad_container;
            param.forceReloadAdOnBind = false;
            return new Builder(param);
        }

        public Builder adItemInterval(int interval) {
            mParam.adItemInterval = interval;
            return this;
        }

        public Builder adLayout(@LayoutRes int layoutContainerRes, @IdRes int itemContainerId) {
            mParam.itemContainerLayoutRes = layoutContainerRes;
            mParam.itemContainerId = itemContainerId;
            return this;
        }

        public AdmobbanerAdAdapterILauncher build() {
            return new AdmobbanerAdAdapterILauncher(mParam);
        }

        public Builder enableSpanRow(GridLayoutManager layoutManager) {
            mParam.gridLayoutManager = layoutManager;
            return this;
        }

        public Builder forceReloadAdOnBind(boolean forced) {
            mParam.forceReloadAdOnBind = forced;
            return this;
        }
    }

    private static class AdViewHolder extends RecyclerView.ViewHolder {

        FrameLayout fl_adplaceholder;

        AdViewHolder(View view) {
            super(view);

            fl_adplaceholder = view.findViewById(R.id.fl_adplaceholder);

            i_Ads2.NativeBiGAds(activity, fl_adplaceholder);
//            nativeBannerAd = new NativeBannerAd(getContext(), getContext().getString(R.string.fb_nativ_banner));
//            nativeBannerAd.setAdListener(new NativeAdListener() {
//
//
//                @Override
//                public void onMediaDownloaded(Ad ad) {
//
//                }
//
//                @Override
//                public void onError(Ad ad, AdError error) {
//                    // Ad error callback
//                    Log.e("nativeerror", "" + error.getErrorMessage() + error.getErrorCode());
//                    final AdView mAdView = new AdView(getContext());
//                    mAdView.setAdSize(AdSize.LARGE_BANNER);
//                    mAdView.setAdUnitId(getContext().getString(R.string.admob_banner));
//                    // Request for Ads
////                    AdRequest adRequest = new AdRequest.Builder().addTestDevice(getContext().getResources().getString(R.string.Oppotest))
////                            .build();
////                    mAdView.loadAd(adRequest);
//                    ll_AD.addView(mAdView);
//                    mAdView.setAdListener(new AdListener() {
//                        @Override
//                        public void onAdLoaded() {
//                            Log.e("admobbanner", "" + "loaded");
//                            ll_AD.setVisibility(View.VISIBLE);
//
//                        }
//
//                        @Override
//                        public void onAdFailedToLoad(int errorCode) {
//                            Log.e("admobbanner", "" + "failed");
//                        }
//
//                        @Override
//                        public void onAdOpened() {
//                        }
//
//                        @Override
//                        public void onAdLeftApplication() {
//                        }
//
//                        @Override
//                        public void onAdClosed() {
//                        }
//                    });
//                }
//
//                @Override
//                public void onAdLoaded(Ad ad) {
//                    ll_AD.setVisibility(View.VISIBLE);
//                    View adView = NativeBannerAdView.render(getContext(), nativeBannerAd, NativeBannerAdView.Type.HEIGHT_100);
//                    // Add the Native Ad View to your ad container
//                    ll_AD.addView(adView);
//                }
//
//                @Override
//                public void onAdClicked(Ad ad) {
//                    // Ad clicked callback
//                }
//
//                @Override
//                public void onLoggingImpression(Ad ad) {
//                    // Ad impression logged callback
//                }
//            });
//            // Request an ad
//            nativeBannerAd.loadAd();
//        }

//        public Context getContext() {
//            return nativeAdContainer.getContext();
//        }
        }
    }
}

