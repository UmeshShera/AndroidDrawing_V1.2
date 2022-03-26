package com.example.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;

import howtodraw.drawing.lessons.art.R;

public class Globals2 {

    public static int defaultTime = 1800;

    public static String sharestring = "Hey, I have found this Amazing Application Iphone Style Launcher 12, It will make your phone like Iphone :- ";
    public static String package_name = "https://bit.ly/3826pof";
    public static String acc_link = "https://play.google.com/store/apps/developer?id=VVP+Developers";
    public static String privacy_link = "";
    public static int background_rand = 10;
    public static boolean isSystemSettingOpen = false;
    public static int news_ads = 0;
    public static int menu_app_click_ads = 0;
    public static int alterNative_ads = 1;
    public static int Ads_Counter = 0;
    public static int NativeAdvanceBannerCounter = 0;
    public static int count_Ads_Inter = 0;
    public static int count_Ads_Banner_Native = 1;
    public static boolean reloadNeeded = false;
    public static android.app.AlertDialog.Builder builderInternet;
    public static gotNextPage gotNextPage;
    public static gotNextPageInternetConnected gotNextPageInternetConnected;
    public static long timeCounter = 0;
    Context ctx;

    public interface gotNextPage {
        void gotoNextPage(boolean gotopage);
    }

    public interface gotNextPageInternetConnected {
        void gotoNextPageIsNetworkConnected(boolean gotopage);
    }

    public Globals2(Context context, gotNextPage nextpage) {
        this.ctx = context;
        gotNextPage = null;
        gotNextPage = nextpage;
    }

    public Globals2(Context context, gotNextPageInternetConnected gotNextPageInternetConnected) {
        this.ctx = context;
        this.gotNextPageInternetConnected = null;
        this.gotNextPageInternetConnected = gotNextPageInternetConnected;
    }

    @SuppressLint("WrongConstant")
    public static boolean isNetworkConnected(Context c) {
        return ((ConnectivityManager) c.getSystemService("connectivity")).getActiveNetworkInfo() != null;
    }

    public static void showNetConnectionDialog(final Activity activity, boolean isFromSplashScreen) {
        if (builderInternet == null) {
            builderInternet = new android.app.AlertDialog.Builder(activity, android.app.AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
            builderInternet.setTitle("Network Connection Error");
            builderInternet.setCancelable(false);
            builderInternet.setMessage("Oops!! No Internet Connection Available. Please try again.");
            builderInternet.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    builderInternet = null;
                    try {
                        if (isNetworkConnected(activity)) {
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                            if (isFromSplashScreen) {
                                gotNextPageInternetConnected.gotoNextPageIsNetworkConnected(true);
                            }

                        } else {
                            if (dialog != null) {
                                dialog.dismiss();
                            }
                            showNetConnectionDialog(activity, isFromSplashScreen);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //context.startActivity(new Intent(android.provider.Settings.ACTION_SETTINGS));
                }
            });
            builderInternet.show();

        } else {
            builderInternet.show();
        }
    }

    public static void backToFinish(Activity context) {
        AlertDialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.AlertDialogCustom));
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewInput = inflater.inflate(R.layout.exit_dailogue, null, false);
        builder.setView(viewInput);
        builder.setCancelable(true);
        i_Ads2.NativeBiGAds(context, viewInput.findViewById(R.id.fl_adplaceholder1));
        Button btn_exit_app = viewInput.findViewById(R.id.btn_exit_app);
        Button btn_rate_app = viewInput.findViewById(R.id.btn_rate_app);

        dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        AlertDialog finalDialog = dialog;
        btn_rate_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String appName = context.getPackageName();
                i_Ads2.ShowAds(context);
                try {

                    finalDialog.dismiss();
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appName)));
                }
            }
        });
        btn_exit_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.finishAffinity();

            }
        });
    }

    public void isForceUpdate() {

        AlertDialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(ctx, R.style.AlertDialogCustom));
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewInput = inflater.inflate(R.layout.app_update_dialog, null, false);
        builder.setView(viewInput);
        builder.setCancelable(false);
        TextView tv_message = viewInput.findViewById(R.id.tv_message);
        Button btn_ok = viewInput.findViewById(R.id.btn_ok);
        Button btn_cancel = viewInput.findViewById(R.id.btn_cancel);
        tv_message.setText(i_SharedUtils2.get(i_SharedString2.forceUpdateMessage));
        if (i_SharedUtils2.get(i_SharedString2.isUpdateForce).equals("1")) {
            btn_cancel.setVisibility(View.VISIBLE);
        } else {
            btn_cancel.setVisibility(View.GONE);
        }

        dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        AlertDialog finalDialog = dialog;
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalDialog != null)
                    finalDialog.dismiss();
                gotNextPage.gotoNextPage(true);
            }
        });
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotNextPage.gotoNextPage(false);
                if (finalDialog != null)
                    finalDialog.dismiss();
                final String appName = ctx.getPackageName();
                i_Ads2.ShowAds((Activity) ctx);
                try {
                    ctx.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    ctx.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appName)));
                }
                ((Activity) ctx).finish();
            }
        });
    }

    public void showOtherAppLink() {

        AlertDialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(ctx, R.style.AlertDialogCustom));
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewInput = inflater.inflate(R.layout.app_update_dialog, null, false);
        i_Ads2.NativeBiGAds((Activity) ctx, viewInput.findViewById(R.id.fl_adplaceholder1));
        builder.setView(viewInput);
        builder.setCancelable(false);
        TextView tv_message = viewInput.findViewById(R.id.tv_message);
        Button btn_ok = viewInput.findViewById(R.id.btn_ok);
        Button btn_cancel = viewInput.findViewById(R.id.btn_cancel);
        tv_message.setText(i_SharedUtils2.get(i_SharedString2.otherAppLinkShowMessage));
        if (i_SharedUtils2.get(i_SharedString2.otherAppLinkShow).equals("1")) {
            btn_cancel.setVisibility(View.VISIBLE);
        } else {
            btn_cancel.setVisibility(View.GONE);
        }

        dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        AlertDialog finalDialog = dialog;
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalDialog != null)
                    finalDialog.dismiss();
                gotNextPage.gotoNextPage(true);
            }
        });
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalDialog != null)
                    finalDialog.dismiss();
                try {
                    String url = i_SharedUtils2.get(i_SharedString2.otherAppLinkShowPackageName);
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    ctx.startActivity(i);
                    //ctx.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + i_SharedUtils2.get(i_SharedString2.otherAppLinkShowPackageName))));
                } catch (android.content.ActivityNotFoundException anfe) {
                    String url = i_SharedUtils2.get(i_SharedString2.otherAppLinkShowPackageName);
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    ctx.startActivity(i);
                    //  ctx.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + (i_SharedString2.otherAppLinkShowPackageName))));
                }
            }
        });

    }
}