package com.feng.dao;

import com.feng.model.User;

public interface UserDAO {
    User selectUserByName(String name);
    void addUser(User user);
    User selectUserById(int id);

}
