package com.seecen.dao;

import com.seecen.entity.User;

import java.util.List;


public interface UserDao {
    public List<User> queryAllUser();
    User queryUserById(int id);
}
