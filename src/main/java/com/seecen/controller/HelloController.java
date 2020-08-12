package com.seecen.controller;

import com.github.pagehelper.PageInfo;
import com.seecen.dao.UserDao;
import com.seecen.entity.User;
import com.seecen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HelloController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

   @RequestMapping("indexError")
    public String index(){
        String s=null;//这里有异常
        System.out.println(s.equals("aaa"));
        return "index";
    }
    @RequestMapping("aspect/{value}")
    public String testAspect(@PathVariable("value") long value){
        return userService.testAspect("hello",value);
    }

    @RequestMapping(value ="/user" ,produces = "application/myjson;charset=utf-8")
    public User getInfo(){
        User u = new User(1,"admin","admin");
        return u;
    }

    @RequestMapping(method = RequestMethod.POST,value ="/insert",produces = "application/myjson;charset=utf-8")
    public @ResponseBody User insert(@RequestBody User user){
        return user;
    }

    @RequestMapping("/queryMap")
    public Map queryMap(){
        Map<Integer, String> integerStringMap = userDao.queryMap();
        return integerStringMap;
    }

    @RequestMapping("/queryMapt")
    public Map queryMapt(){
        Map<Integer, String> integerStringMap = userDao.queryMapt();
        return integerStringMap;
    }

    @RequestMapping("/queryStatus")
    public List queryStats(){
        List<Map<String,Integer>> list = userDao.queryStatus();
        return list;
    }
    @RequestMapping("/queryDate")
    public List queryDate(){
        List<User> list = userDao.queryDate();
        System.out.println(list);
        return list;
    }
    @RequestMapping("/query")
    public PageInfo query(int pageNum,int pageSize) {
        return userService.queryAllUser(pageNum,pageSize);
    }
    @RequestMapping("/queryid/{id}")
    public User queryId(@PathVariable("id") int id){
        return userService.queryUserById(id);
    }
}
