package com.example.onrequest.schema.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.onrequest.schema.entity.UserProfile;

@Dao
public interface UserProfileDao {
    @Insert
    void insert (UserProfile userProfile);

    @Update
    void update(UserProfile userProfile);

    @Query("SELECT * FROM UserProfile")
    UserProfile getUserProfile();

    @Query("SELECT * FROM UserProfile WHERE id = :userId")
    UserProfile getUserProfileById(long userId);
}
