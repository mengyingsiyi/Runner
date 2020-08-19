package com.runner.cache.exception;

/**
 * @Description:
 * @author: ZackJun
 * @date: 2020/8/19  11:44
 */
public class CacheException  extends Exception{
    public CacheException() {}

    public CacheException(String message) {
        super(message);
    }

    public CacheException(String message,CacheException e) {
        super(message,e);
    }
}
