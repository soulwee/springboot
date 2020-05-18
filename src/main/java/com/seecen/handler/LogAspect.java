package com.seecen.handler;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
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

    //发生异常会执行
    @AfterThrowing("test()")
    public void afte13r(){
        log.info("AfterThrowing");
    }


    @Pointcut("execution( * com.seecen.service.UserServiceImpl.*(..))")
    public void test2(){}

    //发生异常不执行
    @AfterReturning(value="test2()",returning="obj")
    public Object afte1r2(Object obj){
        log.info("执行controller方法AfterReturning");
        return obj;
    }

    //环绕通知
    @Around("test2()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("执行controller方法Around");
        //通过反射得到切点对象的类对象 就可以拿类里的方法 属性等等
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        //signature 当前切点的方法签名
        log.info("当前类名："+className+"。方法："+proceedingJoinPoint.getSignature().getName());
        //getArgs得到当前方法内的参数数组，返回值就是方法的返回值 不传参数会自动注入默认参数
        Object o = proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
        log.info("proceed:"+proceedingJoinPoint.proceed());
        log.info("proceed返回值："+o);
        return o;

    }

}
