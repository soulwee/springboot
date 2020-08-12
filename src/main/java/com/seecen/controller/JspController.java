package com.seecen.controller;

import com.seecen.config.RedisUtils;
import com.seecen.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Controller
public class JspController {

    @Autowired
    private RedisUtils redisUtils;

    @RequestMapping("/set/{key}/{value}")
    @ResponseBody
    public Map set(@PathVariable("key") String key, @PathVariable("value") String value) {
        boolean flag = redisUtils.hasKey(key);
        Map map = new HashMap();
        if (flag) {
            map.put("msg", "已存在!");
        } else {
            redisUtils.set(key, value);
            map.put("msg", "插入成功!");
        }
        return map;
    }

    @RequestMapping("/login1")
    public String login1() {
        return "login";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request,User user) {
        System.out.println("birth:"+user.getBirth());
        request.getSession().setAttribute("user", new User(1, user.getName(), user.getPass()));
        return "redirect:/index1";
    }

    //测试WebMvcConfig添加参数
    @RequestMapping("/index1")
    public ModelAndView index1(User user) {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("name", user.getName());
        System.out.println(user);
        return mav;
    }

    @RequestMapping("/index2")
    public String index2(Model model) {
        model.addAttribute("abc", "hhhhhhhhhhhh");
        return "index";
    }

    @RequestMapping(value = "/index3", produces = "application/json;charset=ISO-8859-1")
    @ResponseBody
    public String index3(Model model) {
        model.addAttribute("abc", "张三");
        return model.toString();
    }


    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("d", "4");
        map.put("b", "3");
        map.put("a", "2");
        map.put("c", "1");
        //这里将map.entrySet()转换成list
        List<Map.Entry<String, String>> list = new ArrayList<Map.Entry<String, String>>(map.entrySet());
        for (Map.Entry<String, String> mapping : list) {
            System.out.println(mapping.getKey() + ":" + mapping.getValue());
        }
        //然后通过比较器来实现排序
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            //升序排序
            public int compare(Map.Entry<String, String> o1,
                               Map.Entry<String, String> o2) {
               /* System.out.println(o1.getValue());
                System.out.println(o2.getValue());
                System.out.println(o1.getValue().compareTo(o2.getValue()));*/
               // return o1.getValue().compareTo(o2.getValue());//如果用value比较，返回1就是value升序1234，即dbac
             //  return o1.getKey().compareTo(o2.getKey());//如果根据key值比较，-1是降序dcba，1是升序abcd
               //return -1;//直接返回数字就只根据key值比较，-1是降序dcba，1是升序abcd
               return Integer.valueOf(o2.getValue())>Integer.valueOf(o1.getValue()) ? 1 : -1;//直接返回数字就只根据key值比较，-1是降序dcba，1是升序abcd
            }
        });
        for (Map.Entry<String, String> mapping : list) {
            System.out.println(mapping.getKey() + ":" + mapping.getValue());
        }
        System.out.println("a".compareTo("b"));
        System.out.println("b".compareTo("a"));
    }

}


