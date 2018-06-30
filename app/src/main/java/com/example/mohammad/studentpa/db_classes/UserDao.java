package com.example.mohammad.studentpa.db_classes;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

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

    @Query("SELECT userID, email, password FROM user WHERE email = :email")//Method to get all user names
    LiveData<List<User>> getUserPassword(String email);//Get the PW from DB to check if match

    @Update
    void updateList(User user);//Update the table

    @Delete
    void delete(User user);//Delete the rows, I guess?*/

}
