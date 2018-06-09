package com.example.mohammad.studentpa;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
//this interface handles the queries for USer entity
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE userID IN (:userIds)")
    List<com.example.mohammad.studentpa.User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND "
            + "last_name LIKE :last LIMIT 1")
    com.example.mohammad.studentpa.User findByName(String first, String last);

    @Insert
    void insertAll(com.example.mohammad.studentpa.User... users);

    @Delete
    void delete(com.example.mohammad.studentpa.User user);
}
