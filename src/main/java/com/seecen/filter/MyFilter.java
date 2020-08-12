package com.seecen.filter;

import com.seecen.entity.User;
import com.seecen.web.MyMessageConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MyFilter implements javax.servlet.Filter {
    Logger logger = LoggerFactory.getLogger(MyFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpSession session = request.getSession();
        User user = new User(1,"1","s");
        session.setAttribute("user",user);
        logger.info("ddddddddd-filter:"+request.getRequestURI());
        if(user != null || request.getRequestURI().contains("/login")){
            filterChain.doFilter(request, response);
        }/*else{
            response.sendRedirect("/login1");
        }*/
    }

    @Override
    public void destroy() {

    }
}
