package com.seecen.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

//@Setter @Getter 注解导入成功，项目也可以运行，但是会出现红色波浪线
// 缺少lombok Plugin
@AllArgsConstructor
@Data
@TableName("reguser")
public class User implements Serializable {

    @TableId("usr_ent_id")
    private int id;
    @TableField("usr_ste_usr_id")
    private String name;
    @TableField("usr_pwd")
    private String pass;

    private static final long serialVersionUID = 1L;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @TableField("usr_bday")
    private Date birth;
    public User(){

    }
    public User(int id, String name, String pass) {
        this.id = id;
        this.name = name;
        this.pass = pass;
    }

}
