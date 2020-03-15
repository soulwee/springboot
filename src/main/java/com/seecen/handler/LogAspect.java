package com.seecen.handler;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
/*
* slf4j为了只用log
* Aspect表示这是个切面类
* component用来注册bean
* */
@Slf4j
@Aspect
@Component
public class LogAspect {

    Logger log = LoggerFactory.getLogger(LogAspect.class);
    @Pointcut("execution(public * com.seecen.controller.*.*(..))")
    public void test(){

    }
    @Before("test()")
    public void before(){
        log.info("执行controller方法前");
    }
    @After("test()")
    public void after(){
        log.info("执行controller方法后");
    }

    @AfterReturning("test()")
    public void afte1r2(){
        log.info("执行controller方法AfterReturning");
    }
    @AfterThrowing("test()")
    public void afte13r(){
        log.info("AfterThrowing");
    }


}
