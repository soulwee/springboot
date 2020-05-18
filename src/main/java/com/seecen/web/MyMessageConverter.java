package com.seecen.web;

import com.seecen.entity.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;


public class MyMessageConverter extends AbstractHttpMessageConverter<User> {

    public MyMessageConverter(){
        super(new MediaType("application","myjson",Charset.forName("utf-8")));
    }
    @Override
    protected boolean supports(Class<?> aClass) {
        //只处理User类的
        return User.class.equals(aClass);
    }

    @Override
    protected User readInternal(Class<? extends User> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        String s = StreamUtils.copyToString(httpInputMessage.getBody(), Charset.forName("utf-8"));
        String[] users = s.split(",");
        return new User(Integer.valueOf(users[0]),users[1],users[2]);
    }

    @Override
    protected void writeInternal(User user, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        HttpHeaders headers = httpOutputMessage.getHeaders();
        headers.setContentType(MediaType.valueOf("application/json;charset=UTF-8"));
         httpOutputMessage.getBody().write(("ID:"+user.getId()+",名字："+user.getName()+",密码："+user.getPass()).getBytes());
        //上下两种都行
        // FileCopyUtils.copy(("ID:"+user.getId()+",名字："+user.getName()+",密码："+user.getPass()),new OutputStreamWriter(httpOutputMessage.getBody(),Charset.forName("utf-8")));
    }

}
