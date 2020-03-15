package com.seecen.controller;

import com.github.pagehelper.PageInfo;
import com.seecen.entity.User;
import com.seecen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {
    @Autowired
    private UserService userService;
    @RequestMapping("index")
    public String index(){
        String s=null;
        System.out.println(s.equals("aaa"));
        return "index";
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
