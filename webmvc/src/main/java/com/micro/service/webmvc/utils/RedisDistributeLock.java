package com.micro.service.webmvc.utils;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * Created by xiaos 2018/8/3
 */
@Component
public class RedisDistributeLock implements DistributeLock{

    @Resource
    Jedis jedis;

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final Long RELEASE_SUCCESS = 1L;
    @Override
    public boolean tryLock(String key , String val , long expireTime) {
        String result = jedis.set(key, val, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean release(String lockKey, String val) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(val));
        if (RELEASE_SUCCESS.equals(result)) {
            return true;
          }
        return false;
        }
}
