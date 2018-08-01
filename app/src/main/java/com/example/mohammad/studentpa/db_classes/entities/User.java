//This class is one of the entities of the database
package com.example.mohammad.studentpa.db_classes.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class User {

    public User(String firstName, String lastName, String email, String password, String dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

    @PrimaryKey (autoGenerate = true) //Obvious,and it shall autoGenerate
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

    @ColumnInfo (name="date_of_birth")
    private String dateOfBirth;
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

//    @Ignore
//    private List<MilestoneEntity> milestoneEntities;
//    public List<MilestoneEntity> getMilestoneEntities() {
//        return milestoneEntities;
//    }
//    public void setMilestoneEntities(List<MilestoneEntity> milestoneEntities) {
//        this.milestoneEntities = milestoneEntities;
//    }

}
