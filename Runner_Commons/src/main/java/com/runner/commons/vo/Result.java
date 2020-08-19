package com.runner.commons.vo;


import com.runner.commons.config.ResultConfig;
import lombok.Data;

/**
 * @program: FmOpen
 * @description:
 * @author: Feri(邢朋辉)
 * @create: 2020-08-06 10:47
 */
@Data
public class Result<T> {
    private int code;
    private String msg;
    private T data;

    public static <E> Result<E> ok(String msg, E obj){
     Result r= new Result<>();
        r.setCode(ResultConfig.R_OK);
        r.setMsg(msg);
        r.setData(obj);
        return r;
    }
    public static <E> Result<E> ok(){
        return ok("OK",null);
    }

    public static <E> Result<E> ok(E obj){
       Result r= new Result<>();
        r.setCode(ResultConfig.R_OK);
        r.setMsg("OK");
        r.setData(obj);
        return r;
    }
    public static <E> Result<E> error(String msg, E obj){
       Result r= new Result<>();
        r.setCode(ResultConfig.R_ERROR);
        r.setMsg(msg);
        r.setData(obj);
        return r;
    }
    public static <E>Result<E> error(){
        Result r= new Result<>();
        r.setCode(ResultConfig.R_ERROR);
        r.setMsg("Error");
        r.setData(null);
        return r;
    }
    public static <E> Result<E> setR(boolean isscuccess){
        if(isscuccess){
            return ok();
        }else {
            return error();
        }
    }
}
