package com.example.util;

public enum i_SharedInt2 {
    ADS(1), NativeAdvanceBannerCounter(1), InBetweenListNativeBnr(5), ADS_BasedOnTimeInSecond(0);

    private int mDefaultValue;

    i_SharedInt2(int defaultValue) {
        this.mDefaultValue = defaultValue;
    }

    public String getName() {
        return name();
    }

    public int getDefaultValue() {
        return this.mDefaultValue;
    }
}
