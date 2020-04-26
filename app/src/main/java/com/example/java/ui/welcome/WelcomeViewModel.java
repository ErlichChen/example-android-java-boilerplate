package com.example.java.ui.welcome;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.java.db.entity.UserEntity;
import com.example.java.db.repo.UserRepo;

import java.util.List;

public class WelcomeViewModel extends AndroidViewModel {

    private final MutableLiveData<List<UserEntity>> mObservableUsers;

    public WelcomeViewModel(@NonNull Application app, UserRepo repo) {
        super(app);
        mObservableUsers = new MutableLiveData<>();
        mObservableUsers.setValue(repo.getUsers());
    }

    public LiveData<List<UserEntity>> getUsers() {
        return mObservableUsers;
    }


}
