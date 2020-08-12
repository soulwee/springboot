package com.seecen.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

//@Setter @Getter 注解导入成功，项目也可以运行，但是会出现红色波浪线
// 缺少lombok Plugin
@Setter
@Getter
@AllArgsConstructor
public class User implements Serializable {
    private int id;
    private String name;
    private String pass;

    private static final long serialVersionUID = 1L;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    public User(){

    }
    public User(int id, String name, String pass) {
        this.id = id;
        this.name = name;
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pass='" + pass + '\'' +
                ", birth=" + birth +
                '}';
    }
}
