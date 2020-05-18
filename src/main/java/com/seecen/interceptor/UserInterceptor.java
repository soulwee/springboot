package com.seecen.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserInterceptor extends HandlerInterceptorAdapter {
    Logger logger = LoggerFactory.getLogger(UserInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object user = session.getAttribute("user");
        String url = request.getRequestURI();
        logger.info("interceptor:"+url);
        if(url.contains("/login")){
            if(user != null){
                response.sendRedirect("/index1");
            }else{
                return true;
            }
        }
        if(user == null){
            response.sendRedirect("/login1");
        }
        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Class<?> aClass = handlerMethod.getBean().getClass();
        System.out.println("AAAAAAA:"+aClass);
        return true;
    }
}
