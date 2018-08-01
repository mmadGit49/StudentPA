package com.example.mohammad.studentpa.db_classes;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.mohammad.studentpa.db_classes.entities.User;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private LiveData<List<User>> allUsers;


    public UserViewModel (Application application){
        super(application);
        userRepository = new UserRepository(application);
        allUsers = userRepository.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }
    public LiveData<User> getCredentials(String email, String password){
        return userRepository.getUserCredentials(email, password);
    }
//    public LiveData<List<User>> getUserByID(int userID){
//        return userRepository.getAllUsersbyID(userID);
//    }


    public void insert(User user) {
        userRepository.insert(user);
    }

    public  void delete(User user){
        userRepository.delete(user);
    }

}
