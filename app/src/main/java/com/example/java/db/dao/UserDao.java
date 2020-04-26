package com.example.java.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.java.db.entity.UserEntity;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insertUsers(UserEntity... users);

    @Delete
    void deleteUsers(UserEntity... users);

    @Update
    void updateUsers(UserEntity... users);

    @Query("SELECT * FROM users")
    List<UserEntity> getUsers();
}
