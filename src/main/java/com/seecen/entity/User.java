package com.seecen.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
//@Setter @Getter 注解导入成功，项目也可以运行，但是会出现红色波浪线
// 缺少lombok Plugin
@Setter
@Getter
public class User implements Serializable {
    private int id;
    private String name;
    private String pass;
    public User(){

    }
    public User(int id, String name, String pass) {
        this.id = id;
        this.name = name;
        this.pass = pass;
    }
}
