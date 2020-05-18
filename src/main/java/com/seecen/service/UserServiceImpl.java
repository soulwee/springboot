package com.seecen.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.seecen.dao.UserDao;
import com.seecen.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    @Transactional
    public PageInfo<User> queryAllUser(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> userList=userDao.queryAllUser();
        PageInfo<User> pageInfo=new PageInfo<>(userList);
        return pageInfo;
    }

    @Override
    @Transactional
    public User queryUserById(int id) {
        return userDao.queryUserById(id);
    }

    @Override
    public String testAspect(String a,Long b){
        return a+b;
    }
}
