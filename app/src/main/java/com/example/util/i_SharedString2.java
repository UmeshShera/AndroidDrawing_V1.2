package com.example.util;

public enum i_SharedString2 {

    Random_VideoCall(""),
    ADS_Number("0"),
    banner_native_ads("0"),
    Admob_Banner_Top("0"),
    Unity_ID("0"),
    News_Native_Ads("0"),


    // SplashScreen Dialog Use
    isUpdateForce("0"),
    isInternetConnected("0"),
    otherAppLinkShow("0"),
    appVersion("0"),
    forceUpdateMessage("0"),
    otherAppLinkShowMessage(""),
    otherAppLinkShowPackageName(""),

    iPhone_dialog(""),

    Admob_Interstitial1(""),
    Admob_Banner(""),
    Admob_Native(""),
    Admob_Appopen(""),

    Admob_Interstitial2(""),

    Admob_Interstitial3(""),

    Admob_Interstitial4(""),

    Admob_Interstitial5(""),

    Game_PredChamp(""),
    MGL_Game(""),
    SAX_Game(""),
    SAX_Quize(""),
    AdsExtraBtn(""),
    isDisplayExtraScreen("");


    private String mDefaultValue;

    private i_SharedString2(String defaultValue) {
        this.mDefaultValue = defaultValue;
    }

    public String getName() {
        return name();
    }

    public String getDefaultValue() {
        return this.mDefaultValue;
    }
}
