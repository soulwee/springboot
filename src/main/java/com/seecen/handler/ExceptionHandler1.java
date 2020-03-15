package com.seecen.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(basePackages = "com.seecen.controller")
public class ExceptionHandler1 {
    @ExceptionHandler(RuntimeException.class)
    public ModelAndView errorHandler(Exception e){
        Map map=new HashMap();
        map.put("code","500");
        map.put("message",e.getMessage());
        return new ModelAndView("error",map);
    }

}
