package com.example.java.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class UserEntity {

    @PrimaryKey
    public int id;

    @ColumnInfo(name = "user_name")
    public String username;

}
