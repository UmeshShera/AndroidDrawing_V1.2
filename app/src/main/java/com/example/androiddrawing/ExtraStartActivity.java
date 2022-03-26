package com.example.androiddrawing;

import static com.example.util.i_Ads2.PthinkFacebookBannerSmall;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.util.i_Ads2;
import howtodraw.drawing.lessons.art.R;

public class ExtraStartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_start);

        LinearLayout llbanner = findViewById(R.id.ll_ads);
        PthinkFacebookBannerSmall(ExtraStartActivity.this, llbanner);

        i_Ads2.NativeBiGAds(ExtraStartActivity.this, findViewById(R.id.fl_adplaceholder1));
        i_Ads2.NativeBiGAds(ExtraStartActivity.this, findViewById(R.id.fl_adplaceholder));

        ImageView tv_start = findViewById(R.id.btn_start);
        ImageView btn_play = findViewById(R.id.btn_play);

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExtraStartActivity.this, AdvertisementClass.class);
                i_Ads2.ShowAds(ExtraStartActivity.this, intent);
            }
        });
        tv_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent localIntent = new Intent(ExtraStartActivity.this, StartActivity.class);
//                startActivity(localIntent);
                i_Ads2.ShowAds(ExtraStartActivity.this, localIntent);
            }
        });

        ImageView img_back = findViewById(R.id.img_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent localIntent = new Intent(ExtraStartActivity.this, ExtraExitActivity.class);
        //startActivity(localIntent);
        i_Ads2.ShowAds(ExtraStartActivity.this, localIntent);
    }
}
