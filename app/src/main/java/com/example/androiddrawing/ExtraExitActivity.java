package com.example.androiddrawing;

import static com.example.util.i_Ads2.PthinkFacebookBannerSmall;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.util.Globals2;
import com.example.util.i_Ads2;
import howtodraw.drawing.lessons.art.R;

public class ExtraExitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_exit);

        LinearLayout llbanner = findViewById(R.id.ll_ads);
        PthinkFacebookBannerSmall(ExtraExitActivity.this, llbanner);

        i_Ads2.NativeBiGAds(ExtraExitActivity.this, findViewById(R.id.fl_adplaceholder1));
        i_Ads2.NativeBiGAds(ExtraExitActivity.this, findViewById(R.id.fl_adplaceholder));

        ImageView tv_quit = findViewById(R.id.tv_quit);
        tv_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ImageView tv_cancel = findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExtraExitActivity.this, ExtraStartActivity.class);
                i_Ads2.ShowAds(ExtraExitActivity.this, intent);
                //                startActivity(intent);
            }
        });

        ImageView img_back = findViewById(R.id.img_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(i_Ads2.ShowAds(ExtraExitActivity.this))) {
                    return;
                }
                Globals2.backToFinish(ExtraExitActivity.this);

            }
        });

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if (!(i_Ads2.ShowAds(ExtraExitActivity.this))) {
            return;
        }
        Globals2.backToFinish(ExtraExitActivity.this);

    }
}
