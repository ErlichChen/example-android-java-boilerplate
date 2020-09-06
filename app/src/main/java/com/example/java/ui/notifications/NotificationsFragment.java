package com.example.java.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.java.R;
import com.example.java.base.BaseFragment;
import com.example.java.binder.annotations.BindView;

public class NotificationsFragment extends BaseFragment {

    private NotificationsViewModel notificationsViewModel;

    @BindView(R.id.text_notifications)
    TextView text_notifications;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_notifications;
    }

    @Override
    protected void init() {
        notificationsViewModel = ViewModelProviders.of(this).get(NotificationsViewModel.class);
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                text_notifications.setText(s);
            }
        });
    }

}
