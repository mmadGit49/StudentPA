package com.example.mohammad.studentpa.db_classes;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.mohammad.studentpa.db_classes.Entities.User;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private LiveData<List<User>> allUsers;
    private LiveData<List<User>> allUserEmails;
    private LiveData<List<User>> allUserPasswords;
    private LiveData<List<User>> allUserLoginPasswords;


    public UserViewModel (Application application){
        super(application);
        userRepository = new UserRepository(application);
        allUserEmails = userRepository.getAllUserEmails();
        allUserPasswords = userRepository.getAllUserPasswords();
        allUsers = userRepository.getAllUsers();
        allUserPasswords = userRepository.getAllUserLoginPasswords();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }
    public LiveData<List<User>> getAllUserEmails() {
        return allUserEmails;
    }
    public LiveData<List<User>> getAllUserPasswords() {
        return allUserPasswords;
    }
    public LiveData<List<User>> getAllUserLoginPasswords() {
        return allUserLoginPasswords;
    }
    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void insert(User user) {
        userRepository.insert(user);
    }

    public  void delete(User user){
        userRepository.delete(user);
    }

}
