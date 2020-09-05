package com.example.java.binder;

import android.app.Activity;
import android.view.View;

import androidx.fragment.app.Fragment;

public class Utils {
    public static <T extends View> T findViewById(Activity activity, int viewId){
        return (T)activity.findViewById(viewId);
    }
    public static <T extends View> T findViewById(Fragment fragment, int viewId){
        return (T)fragment.getView().findViewById(viewId);
    }
}
