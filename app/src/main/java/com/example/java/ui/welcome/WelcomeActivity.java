package com.example.java.ui.welcome;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.example.java.R;
import com.example.java.base.BaseActivity;
import com.example.java.db.repo.UserRepo;
import com.example.java.ui.login.LoginActivity;
import com.example.java.ui.main.MainActivity;
import com.example.java.BuildConfig;

import java.lang.ref.WeakReference;

public class WelcomeActivity extends BaseActivity {

    private static final int GOTO_LOGIN = 100;
    private static final int GOTO_MAIN = 200;
    private static final int DURATION = 2000;

    private int versioncode = BuildConfig.VERSION_CODE;
    private String versionname = BuildConfig.VERSION_NAME;

    private final MyHandler mHandler = new MyHandler(this);

    private WelcomeViewModel viewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void init() {
        viewModel = new WelcomeViewModel(getApplication(), UserRepo.getInstance(this));

        viewModel.getUsers().observe(this, UserEntities -> {
            if (UserEntities != null && UserEntities.size() > 0) {
                mHandler.sendEmptyMessageDelayed(GOTO_MAIN, DURATION);
            } else {
                mHandler.sendEmptyMessageDelayed(GOTO_LOGIN, DURATION);
            }
        });
    }

    private static class MyHandler extends Handler {
        private final WeakReference<WelcomeActivity> mActivity;

        private MyHandler(WelcomeActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            WelcomeActivity activity = mActivity.get();
            switch (msg.what) {
                case GOTO_LOGIN:
                    activity.startActivity(new Intent(activity, LoginActivity.class));
                    break;

                case GOTO_MAIN:
                    activity.startActivity(new Intent(activity, MainActivity.class));
                    break;

                default:
                    break;
            }
            activity.finish();
        }
    }
}
