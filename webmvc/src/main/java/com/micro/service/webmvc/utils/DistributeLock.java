package com.micro.service.webmvc.utils;

/**
 * Created by xiaos 2018/8/3
 */
public interface DistributeLock {

     boolean tryLock(String key , String val , long expireTime);



     boolean release(String key , String val);
}
