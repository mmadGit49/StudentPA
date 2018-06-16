//This class is one of the entities of the database
package com.example.mohammad.studentpa;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity
public class User {
    @PrimaryKey (autoGenerate = true) //Obvious,and it shall autoGenerate
    @NonNull//Obvious
    @ColumnInfo (name="userID")//Not necessary unless you want column name to differ from variable
    private int userID;
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }

    @ColumnInfo (name="first_name") //really not necessary to name in this case but for learning purposes..
    private String firstName;
    public String getFirstName() {

        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @ColumnInfo (name="last_name")
    private String lastName;
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @ColumnInfo (name="email")
    private String email;
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @ColumnInfo (name="password")
    private String password;
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @ColumnInfo (name="age")
    private int age;
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    @ColumnInfo (name="gender")
    private String gender;
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    //TODO: resolve date parsing issue
    /*@ColumnInfo (name="date_of_birth")
    private Date dateOfBirth;
    private java.sql.Date dateConverted;

    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }*/
}
