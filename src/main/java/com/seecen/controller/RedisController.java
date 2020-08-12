package com.seecen.controller;

import com.seecen.config.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class RedisController {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/kill/{seckillId}")
    public String seckillHandle(@PathVariable("seckillId") String seckillId) {
        String key = "key:" + seckillId ;
       // redisTemplate.opsForHash().put(key+":info","stock" ,5);
      //  redisUtils.hset("key:3:info","stock","11");
        try {
            Boolean lockFlag = redisTemplate.opsForValue().setIfAbsent("key", "val", 1, TimeUnit.SECONDS);
            if (lockFlag) {
                // HTTP请求用户服务进行用户相关的校验
                // 用户活动校验

                // 库存校验 非原子性的，采用的是get and compare
                Object stock = redisTemplate.opsForHash().get(key+":info", "stock");
                assert stock != null;
                if (Integer.parseInt(stock.toString()) <= 0) {
                    // 业务异常
                    System.out.println("小于0，抛出");
                    return "gg";
                } else {
                    redisTemplate.opsForHash().increment(key+":info", "stock", -1);
                    System.out.println(Thread.currentThread().getName()+"--1");
                    try {
                        Thread.sleep(4000);//模拟延迟
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // 生成订单
                    // 发布订单创建成功事件
                    // 构建响应VO
                }
            }else{
                System.out.println(Thread.currentThread().getName()+"没拿到锁");
            }
        } finally {
            // 释放锁
            Boolean isDel = stringRedisTemplate.delete("key");
            System.out.println(Thread.currentThread().getName()+"是否删了锁："+isDel);
            // 构建响应VO
        }
        return "jj";
    }
}
