package com.example.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class i_SharedUtils2 {
    private static final String SHARED_NAME = "shared_file";
    private static SharedPreferences instance;

    private i_SharedUtils2() {
    }

    public static void init(Context context) {
        if (instance == null) {
            instance = context.getSharedPreferences(SHARED_NAME, 0);
        }
    }

    public static int get(i_SharedInt2 param) {
        return instance.getInt(param.getName(), param.getDefaultValue());
    }

    public static String get(i_SharedString2 param) {
        return instance.getString(param.getName(), param.getDefaultValue());
    }

    public static void set(i_SharedInt2 param, int value) {
        Editor e = instance.edit();
        e.putInt(param.getName(), value);
        e.apply();
    }
    public static void set(i_SharedString2 param, String value) {
        Editor e = instance.edit();
        e.putString(param.getName(), value);
        e.apply();
    }

}
