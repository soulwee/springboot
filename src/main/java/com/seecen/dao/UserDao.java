package com.seecen.dao;

import com.seecen.entity.User;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;


public interface UserDao {
    List<User> queryAllUser();
    List<User> queryDate();
    @MapKey("id")
    Map<Integer,String> queryMap();

    Map<Integer,String> queryMapt();
    List<Map<String,Integer>> queryStatus();
    User queryUserById(int id);

    User testById(int id);
}
