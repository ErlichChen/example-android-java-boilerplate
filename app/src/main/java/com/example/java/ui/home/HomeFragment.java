package com.example.java.ui.home;

import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.java.R;
import com.example.java.base.BaseFragment;
import com.example.java.binder.annotations.BindView;
import com.example.java.binder.annotations.OnClick;


public class HomeFragment extends BaseFragment {

    @BindView(R.id.text_home)
    TextView textView;

    private HomeViewModel homeViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init() {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });
    }

    @OnClick(R.id.btn_home)
    public void onClick() {

    }
}
