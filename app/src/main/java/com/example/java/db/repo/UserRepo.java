package com.example.java.db.repo;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.java.db.AppDatabase;
import com.example.java.db.entity.UserEntity;

import java.util.List;

public class UserRepo {

    private static UserRepo sInstance;

    private final AppDatabase mDatabase;

    private UserRepo(final AppDatabase database) {
        mDatabase = database;
    }

    public static UserRepo getInstance(Context context) {
        if (sInstance == null) {
            synchronized (UserRepo.class) {
                if (sInstance == null) {
                    AppDatabase database = AppDatabase.getInstance(context);
                    sInstance = new UserRepo(database);
                }
            }
        }
        return sInstance;
    }

    public List<UserEntity> getUsers() {
        return mDatabase.userDao().getUsers();
    }
}
