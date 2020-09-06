package com.example.java.ui.dashboard;

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

public class DashboardFragment extends BaseFragment {

    private DashboardViewModel dashboardViewModel;

    @BindView(R.id.text_dashboard)
    TextView text_dashboard;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dashboard;
    }

    @Override
    protected void init() {
        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                text_dashboard.setText(s);
            }
        });
    }

}
