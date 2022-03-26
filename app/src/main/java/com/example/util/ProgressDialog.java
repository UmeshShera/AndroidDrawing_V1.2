package com.example.util;

import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import howtodraw.drawing.lessons.art.R;

public class ProgressDialog extends android.app.ProgressDialog {

    private final String title;
    private final Activity context;

    public ProgressDialog(Activity context, String title) {
        super(context, R.style.PthinkProgressDialog);
        this.context = context;
        this.title = title;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.ads_progress);
        TextView tv_title = findViewById(R.id.tv_title_ads);
        tv_title.setText(title);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
    }

    @Override
    public void show() {
        super.show();
    }
}
