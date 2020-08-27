package com.runner.commons.vo;


import com.runner.commons.config.ResultConfig;
import lombok.Data;

/**
 * @program: OpenMain
 * @description
 * @author:
 * @create: 2020-07-28 16:47
 **/
@Data
public class R<T> {
    private Integer code;
    private String msg;
    private T data;

    public static <E> R<E> ok() {
        R<E> r = new R<>();
        r.setMsg("OK");
        r.setCode(ResultConfig.R_OK);

        return r;
    }
    public static <T> R<T> ok(String msg, T obj) {
        R<T> r = new R<>();
        r.setCode(ResultConfig.R_OK);
        r.setMsg(msg);
        r.setData(obj);
        return r;
    }
    public static <T> R<T> ok(T obj) {
      R<T> r = new R<>();
        r.setCode(ResultConfig.R_OK);
        r.setData(obj);
        return r;
    }
    public static <E> R<E> fail(String msg) {
    R<E> r = new R<>();
        r.setCode(ResultConfig.R_ERROR);
        r.setMsg(msg);
        return r;
    }


}
