package com.example.java.binder;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import java.lang.reflect.Constructor;

public class Binder {
    public static Unbinder bind(Activity activity){
        try {
            Class<? extends Unbinder> clazz = (Class<? extends Unbinder>) Class.forName(activity.getClass().getName() + "_ViewBinding");
            //构造函数

            Constructor<? extends Unbinder> unbinderConstuctor = clazz.getDeclaredConstructor(activity.getClass());
            Unbinder unbinder = unbinderConstuctor.newInstance(activity);
            return unbinder;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Unbinder.EMPTY;
    }

    public static Unbinder bind(Fragment fragment){
        try {
            Class<? extends Unbinder> clazz = (Class<? extends Unbinder>) Class.forName(fragment.getClass().getName() + "_ViewBinding");
            //构造函数

            Constructor<? extends Unbinder> unbinderConstuctor = clazz.getDeclaredConstructor(fragment.getClass());
            Unbinder unbinder = unbinderConstuctor.newInstance(fragment);
            return unbinder;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Unbinder.EMPTY;
    }
}
