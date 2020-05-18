package com.seecen.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
//如果在webmvcconfig里配置过HandlerExceptionResolver 就不会进这里
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
