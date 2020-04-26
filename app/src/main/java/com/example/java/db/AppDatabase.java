package com.example.java.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.java.AppExecutors;
import com.example.java.db.converter.DateConverter;
import com.example.java.db.dao.UserDao;
import com.example.java.db.entity.UserEntity;

import java.util.concurrent.Executor;


@Database(entities = {
        UserEntity.class
}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase sInstance;

    private static final String DATABASE_NAME = "com-example-java-db";

    public abstract UserDao userDao();

    public static AppDatabase getInstance(final Context context) {
        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                if (sInstance == null) {
                    sInstance = buildDB(context);
                }
            }
        }
        return sInstance;
    }

    private static AppDatabase buildDB(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        AppExecutors.getInstance().diskIO().execute(() -> {

                        });
                    }
                })
                .addMigrations()
                .build();
    }


}
