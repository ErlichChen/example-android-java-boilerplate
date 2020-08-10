package com.example.utils;

import java.util.Locale;

public class NumUtils {

    private static NumUtils sInstance;

    public static NumUtils getInstance() {
        if (sInstance == null) {
            synchronized (NumUtils.class) {
                if (sInstance == null) {
                    sInstance = new NumUtils();
                }
            }
        }
        return sInstance;
    }

    public float toFixed(float num, int digit) {
        return Float.parseFloat(String.format(Locale.ENGLISH, "%." + digit + "f", num));
    }

}
