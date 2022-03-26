package com.example.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import howtodraw.drawing.lessons.art.R;

public class i_ProgressDialog2 extends ProgressDialog {

    private final String title;

    public i_ProgressDialog2(Context context, String title) {
        super(context, R.style.PthinkProgressDialog);
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
        setContentView(R.layout.pthink_progress2);
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
