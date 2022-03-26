package com.example.androiddrawing;

import static com.example.util.i_Ads2.PthinkFacebookBannerSmall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.util.i_Ads2;
import com.example.util.i_SharedString2;
import com.example.util.i_SharedUtils2;

import howtodraw.drawing.lessons.art.R;

import pl.droidsonroids.gif.GifImageView;

public class AdvertisementClass extends AppCompatActivity {
    GifImageView game, quiz, game_predchamp, mgl_game;
    ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertisement_class);

        game = findViewById(R.id.game);
        quiz = findViewById(R.id.quiz);
        game_predchamp = findViewById(R.id.game_predchamp);
        mgl_game = findViewById(R.id.mgl_game);
        img_back = findViewById(R.id.img_back);

        LinearLayout llbanner = findViewById(R.id.ll_ads);
        PthinkFacebookBannerSmall(AdvertisementClass.this, llbanner);

        i_Ads2.NativeBiGAds(AdvertisementClass.this, findViewById(R.id.fl_adplaceholder1));
        i_Ads2.NativeBiGAds(AdvertisementClass.this, findViewById(R.id.fl_adplaceholder));

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent game = new Intent(AdvertisementClass.this, AcitivityWebview.class);
                game.putExtra("URL", i_SharedUtils2.get(i_SharedString2.SAX_Game));
                game.putExtra("Title", getResources().getString(R.string.game));
                i_Ads2.ShowAds(AdvertisementClass.this, game);
            }
        });

        quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent game = new Intent(AdvertisementClass.this, AcitivityWebview.class);
                game.putExtra("URL", i_SharedUtils2.get(i_SharedString2.SAX_Quize));
                game.putExtra("Title", getResources().getString(R.string.quiz));
                i_Ads2.ShowAds(AdvertisementClass.this, game);
            }
        });

        game_predchamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent game = new Intent(AdvertisementClass.this, AcitivityWebview.class);
                game.putExtra("URL", i_SharedUtils2.get(i_SharedString2.Game_PredChamp));
                game.putExtra("Title", getResources().getString(R.string.Game_PredChamp));
                i_Ads2.ShowAds(AdvertisementClass.this, game);
            }
        });

        mgl_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent game = new Intent(AdvertisementClass.this, AcitivityWebview.class);
                game.putExtra("URL", i_SharedUtils2.get(i_SharedString2.MGL_Game));
                game.putExtra("Title", getResources().getString(R.string.MGL_Game));
                i_Ads2.ShowAds(AdvertisementClass.this, game);
            }
        });


    }

    @Override
    public void onBackPressed() {
        i_Ads2.ShowAdsBackPressedFinish(AdvertisementClass.this);
    }
}