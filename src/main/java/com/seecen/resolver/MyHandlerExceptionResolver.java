package com.seecen.resolver;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyHandlerExceptionResolver extends ExceptionHandlerExceptionResolver{
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        return super.resolveException(request, response, handler, ex);
    }

    @Override
    protected ModelAndView doResolveHandlerMethodException(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod, Exception exception) {
        /*try {
            response.getWriter().append("method:"+handlerMethod.getMethod().getName()+",msg:"+exception.getMessage()).flush();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        ModelAndView mav = new ModelAndView();
        mav.addObject("message",exception.getMessage());
        mav.addObject("code","500:"+handlerMethod.getMethod().getName());
        return mav;
    }
}
