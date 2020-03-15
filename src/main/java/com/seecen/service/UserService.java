package com.seecen.service;

import com.github.pagehelper.PageInfo;
import com.seecen.entity.User;


public interface UserService {
    public PageInfo<User> queryAllUser(int pageNum, int pageSize);
    User queryUserById(int id);
}
