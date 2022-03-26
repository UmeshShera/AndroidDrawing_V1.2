package com.example.androiddrawing;

import static com.example.util.i_Ads2.Load_Unity_Ads;
import static com.example.util.i_Ads2.PthinkFacebookBannerSmall;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.util.Globals2;
import com.example.util.i_Ads2;
import com.example.util.i_SharedString2;
import com.example.util.i_SharedUtils2;

import howtodraw.drawing.lessons.art.BuildConfig;
import howtodraw.drawing.lessons.art.R;
import howtodraw.drawing.lessons.art.databinding.ActivityStartBinding;

public class StartActivity extends AppCompatActivity {
    ActivityStartBinding binding;
    Context context;
    ImageView iv_rate, iv_share, iv_privecy, btnStart, btnGame, btnQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_start);
        Load_Unity_Ads(StartActivity.this);
        iv_rate = findViewById(R.id.iv_rate);
        iv_share = findViewById(R.id.iv_share);
        iv_privecy = findViewById(R.id.iv_privecy);
        btnStart = findViewById(R.id.btnStart);
        btnGame = findViewById(R.id.btnGame);
        btnQuiz = findViewById(R.id.btnQuiz);

        i_Ads2.NativeBiGAds(StartActivity.this, findViewById(R.id.fl_adplaceholder));

        LinearLayout llbanner = findViewById(R.id.ll_ads);
        PthinkFacebookBannerSmall(StartActivity.this, llbanner);

        context = this;

        iv_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String appName = context.getPackageName();
                i_Ads2.ShowAds(StartActivity.this);
                try {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appName)));
                }
            }
        });
        iv_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i_Ads2.ShowAds(StartActivity.this);
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                    String shareMessage = "\nLet me recommend you this application\n\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch (Exception e) {
                }
            }
        });
        iv_privecy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StartActivity.this, AcitivityWebview.class);
                i.putExtra("URL", "file:///android_asset/privacy_policy.html");
                i.putExtra("Title", getResources().getString(R.string.prv_policy));
                //startActivity(i);
                i_Ads2.ShowAds(StartActivity.this, i);
            }
        });
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start = new Intent(StartActivity.this, MainActivity.class);
                //startActivity(start);
                i_Ads2.ShowAds(StartActivity.this, start);
            }
        });
        btnGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent game = new Intent(StartActivity.this, AcitivityWebview.class);
                game.putExtra("URL", i_SharedUtils2.get(i_SharedString2.SAX_Game));
                game.putExtra("Title", getResources().getString(R.string.game));
                i_Ads2.ShowAds(StartActivity.this, game);
            }
        });
        btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quiz = new Intent(StartActivity.this, AcitivityWebview.class);
                quiz.putExtra("URL", i_SharedUtils2.get(i_SharedString2.SAX_Quize));
                quiz.putExtra("Title", getResources().getString(R.string.quiz));
                //startActivity(quiz);
                i_Ads2.ShowAds(StartActivity.this, quiz);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (i_SharedUtils2.get(i_SharedString2.isDisplayExtraScreen).equalsIgnoreCase("1")) {
//            super.onBackPressed();
            i_Ads2.ShowAdsBackPressedFinish(StartActivity.this);

        } else {
            Globals2.backToFinish(StartActivity.this);
        }
        //super.onBackPressed();
    }
}
