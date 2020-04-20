package com.example.java;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent();
            intent.setClass(WelcomeActivity.this, MainActivity.class);
            WelcomeActivity.this.startActivity(intent);
            finish();
        }, 3000);

    }
}
