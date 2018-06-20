package com.example.mohammad.studentpa;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

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

    /*@Query("SELECT email FROM user")//Method to get all emails/usernames
    LiveData<List<User>> getAllusernames();

    @Update
    void updateList(User user);//Update the table

    @Delete
    void delete(User user);//Delete the rows, I guess?

    @Insert
    void insert(User param);*/

    //void insert(User param);
}
