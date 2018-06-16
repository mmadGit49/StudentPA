package com.example.mohammad.studentpa;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**little-no SQL required in this interface for updates, deleted etc
 * all methods are abstract
 * annotations will automatically generate the equivalent SQL
 *
 * This interface handles the queries for User entity
 */

@Dao
public interface UserDao {

    @Insert
    void insertSingleUser(User user);//Insert a single user's details after registration into the table

    //@Insert (onConflict = OnConflictStrategy.REPLACE)
    //in case 2 rows with same pri key are entered
    //will replace a row

    @Query("SELECT * FROM user")//Method to get all rows
    LiveData<List<User>> getAllRows();//LiveData allows DB to be automatically updated

    @Query("SELECT * FROM user WHERE userID IN (:userIds)")
    List<com.example.mohammad.studentpa.User> loadAllByIds(int[] userIds);

    @Update
    void updateList(User user);//Update the table

    @Delete
    void delete(User user);//Delete the rows, I guess?

    @Insert
    void insert(User param);

    //void insert(User param);
}
