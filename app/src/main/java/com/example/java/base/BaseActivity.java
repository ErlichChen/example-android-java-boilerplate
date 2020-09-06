package com.example.java.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.java.binder.Binder;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(getLayoutId());
        Binder.bind(this);
        init();
    }

    protected abstract int getLayoutId();

    protected void init() { }
}

