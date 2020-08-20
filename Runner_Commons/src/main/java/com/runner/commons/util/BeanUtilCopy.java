package com.runner.commons.util;



import com.runner.commons.annotation.BeanDtoCopy;

import java.lang.reflect.Field;

/**
 * Dto复制到pojo工具类
 */
public class BeanUtilCopy {


    public static <T> T copy(Class<T> clazz, Object obj) {
        try {
            T t = clazz.newInstance();
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                String fn;
                //获取注解
                BeanDtoCopy annotation = field.getAnnotation(BeanDtoCopy.class);
                if (null != annotation){
                    fn = annotation.value();
                }else {
                    fn = field.getName();
                }
                //判断pojo是否有对应的属性
                Field fd = clazz.getDeclaredField(fn);
                if (fd != null){
                    fd.setAccessible(true);
                    field.setAccessible(true);
                    fd.set(t,field.get(obj));
                }
            }
            return t;
        } catch (Exception e) {
            return null;
        }
    }
}
