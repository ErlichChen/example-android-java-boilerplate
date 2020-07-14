package com.example.java.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

public class SharedPrefsUtils {

    private SharedPrefsUtils() { }

    private static Context context;

    public static void init(Context context) {
        SharedPrefsUtils.context = context;
    }

    public static boolean putString(String key, String value) {
        if (context == null) throw new NullPointerException("SharedPrefsUtils is not initialized");
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (preferences != null && !TextUtils.isEmpty(key)) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(key, value);
            return editor.commit();
        }
        return false;
    }

    public static String getString(String key) {
        if (context == null) throw new NullPointerException("SharedPrefsUtils is not initialized");
        String value = null;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (preferences != null) {
            value = preferences.getString(key, null);
        }
        return value;
    }

    public static boolean putInt(String key, int value) {
        if (context == null) throw new NullPointerException("SharedPrefsUtils is not initialized");
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (preferences != null && !TextUtils.isEmpty(key)) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt(key, value);
            return editor.commit();
        }
        return false;
    }

    public static int getInt(String key) {
        if (context == null) throw new NullPointerException("SharedPrefsUtils is not initialized");
        int value = 0;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (preferences != null) {
            value = preferences.getInt(key, 0);
        }
        return value;
    }

    public static boolean putLong(String key, long value) {
        if (context == null) throw new NullPointerException("SharedPrefsUtils is not initialized");
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (preferences != null && !TextUtils.isEmpty(key)) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putLong(key, value);
            return editor.commit();
        }
        return false;
    }

    public static long getLong(String key) {
        if (context == null) throw new NullPointerException("SharedPrefsUtils is not initialized");
        long value = 0;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (preferences != null) {
            value = preferences.getLong(key, 0);
        }
        return value;
    }

    public static boolean putFloat(String key, float value) {
        if (context == null) throw new NullPointerException("SharedPrefsUtils is not initialized");
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (preferences != null && !TextUtils.isEmpty(key)) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putFloat(key, value);
            return editor.commit();
        }
        return false;
    }

    public static float getFloat(String key) {
        if (context == null) throw new NullPointerException("SharedPrefsUtils is not initialized");
        float value = 0;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (preferences != null) {
            value = preferences.getFloat(key, 0);
        }
        return value;
    }

    public static boolean putBoolean(String key, boolean value) {
        if (context == null) throw new NullPointerException("SharedPrefsUtils is not initialized");
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (preferences != null && !TextUtils.isEmpty(key)) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(key, value);
            return editor.commit();
        }
        return false;
    }

    public static boolean getBoolean(String key) {
        if (context == null) throw new NullPointerException("SharedPrefsUtils is not initialized");
        boolean value = false;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (preferences != null) {
            value = preferences.getBoolean(key, false);
        }
        return value;
    }
}
