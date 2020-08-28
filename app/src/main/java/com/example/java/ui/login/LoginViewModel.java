package com.example.java.ui.login;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.java.db.repo.UserRepo;

public class LoginViewModel extends ViewModel {

    private UserRepo userRepo;

    LoginViewModel(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void login(String username, String password) {

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
