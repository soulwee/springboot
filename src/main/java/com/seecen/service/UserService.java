package com.seecen.service;

import com.github.pagehelper.PageInfo;
import com.seecen.entity.User;


public interface UserService {
    PageInfo<User> queryAllUser(int pageNum, int pageSize);
    User queryUserById(int id);
    String testAspect(String a,Long b);
    default void a(){//jdk8无需实现default方法

    }
}
