package com.seecen.controller;

import com.seecen.config.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class JspController {



    @Autowired
    private RedisUtils redisUtils;
    @RequestMapping("/set/{key}/{value}")
    @ResponseBody
    public Map set(@PathVariable("key")String key, @PathVariable("value")String value){
        boolean flag=redisUtils.hasKey(key);
        Map map=new HashMap();
        if(flag){
            map.put("msg","已存在!");
        }else{
            redisUtils.set(key,value);
            map.put("msg","插入成功!");
        }
        return map;
    }
    @RequestMapping("/index1")
    public String index1(){
        return "index";
    }

    @RequestMapping("/index2")
    public String index2(Model model){
        model.addAttribute("abc","hhhhhhhhhhhh");
        return "index";
    }

    @RequestMapping("/index3")
    @ResponseBody
    public String index3(Model model){
        model.addAttribute("abc","abccc");
        return model.toString();
    }




}


