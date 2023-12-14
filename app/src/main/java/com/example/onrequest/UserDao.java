package com.example.onrequest;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Insert
    long registerUser(User user);

    @Query("SELECT * FROM User WHERE username = :username AND password = :password")
    User loginUser(String username, String password);
}
