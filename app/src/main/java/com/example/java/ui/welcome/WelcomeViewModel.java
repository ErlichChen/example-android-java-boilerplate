package com.example.java.ui.welcome;

import android.app.Application;
import android.view.animation.Transformation;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.java.AppExecutors;
import com.example.java.db.entity.UserEntity;
import com.example.java.db.repo.UserRepo;

import java.util.List;

public class WelcomeViewModel extends AndroidViewModel {

    private final MutableLiveData<List<UserEntity>> mObservableUsers;
    private UserRepo repo;

    public WelcomeViewModel(@NonNull Application app, UserRepo repo) {
        super(app);
        this.repo = repo;
        mObservableUsers = new MutableLiveData<>();
    }

    public LiveData<List<UserEntity>> getUsers() {
        AppExecutors.getInstance().diskIO().execute(() -> {
            mObservableUsers.postValue(repo.getUsers());
        });
        return mObservableUsers;
    }

}
