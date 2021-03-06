package com.example.mohammad.studentpa.db_classes.DAOs;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RoomWarnings;
import android.arch.persistence.room.Update;

import com.example.mohammad.studentpa.db_classes.entities.User;

import java.util.List;

/**little-no SQL required in this interface for updates, deleted etc
 * all methods are abstract
 * annotations will automatically generate the equivalent SQL
 * This interface handles the queries for User entity
 * sort of like ORM (Object-Relational mapping) if im not wrong
 */

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);//Insert a single user's details after registration into the table

    @Query("SELECT * FROM user")//Method to get all emails/usernames
    LiveData<List<User>> getAllUsers();

    //Method to get all user credentials for login
    @Query("SELECT userID,first_name, email, password FROM user WHERE email = :email AND password = :password")
    @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
    LiveData<User> getUserCredentialsLogin(String email, String password);//Get the email from DB to check if match

    @Update
    void updateList(User user);//Update the table

    @Delete
    void delete(User user);//Delete the rows, I guess?*/

}
