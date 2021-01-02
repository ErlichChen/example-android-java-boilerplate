package com.example.java.ui.login;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.java.base.BaseActivity;
import com.example.java.binder.Binder;
import com.example.java.binder.annotations.BindView;
import com.example.java.binder.annotations.OnClick;

import com.example.java.R;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_username)
    EditText et_username;

    @BindView(R.id.et_password)
    EditText et_password;

    private LoginViewModel loginViewModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {
        et_username.setText("admin");
    }

}
