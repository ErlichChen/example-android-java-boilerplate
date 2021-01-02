package com.example.java.ui.login;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.java.R;
import com.example.java.db.repo.UserRepo;

public class LoginViewModel extends ViewModel {

    private UserRepo userRepo;

    LoginViewModel(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void login(Activity activity, String username, String password) {
        if ("Admin".equals(username)) {


        }
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        private Application mApp;

        public Factory(Application mApp) {
            this.mApp = mApp;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new LoginViewModel(UserRepo.getInstance(mApp.getApplicationContext()));
        }
    }
}
