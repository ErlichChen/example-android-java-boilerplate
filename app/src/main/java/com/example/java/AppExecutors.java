package com.example.java;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {

    private static AppExecutors sInstance;

    public static AppExecutors getInstance() {
        if (sInstance == null) {
            synchronized (AppExecutors.class) {
                if (sInstance == null) {
                    sInstance = new AppExecutors();
                }
            }
        }
        return sInstance;
    }

    private final Executor diskIO;
    private final Executor networkIO;
    private final Executor uiThread;

    private AppExecutors(Executor localIO, Executor remoteIO, Executor uiThread) {
        this.diskIO    = localIO;
        this.networkIO = remoteIO;
        this.uiThread  = uiThread;
    }

    public AppExecutors() {
        this(Executors.newSingleThreadExecutor(),
                Executors.newFixedThreadPool(3),
                new UiThreadExecutor());
    }

    public Executor diskIO() {
        return diskIO;
    }

    public Executor networkIO() {
        return networkIO;
    }

    public Executor uiThread() {
        return uiThread;
    }

    private static class UiThreadExecutor implements Executor {
        private Handler uiHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            uiHandler.post(command);
        }
    }
}
