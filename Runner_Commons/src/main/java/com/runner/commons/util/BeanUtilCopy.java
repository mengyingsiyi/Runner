package com.runner.commons.util;



import com.runner.commons.annotation.BeanDtoCopy;

import java.lang.reflect.Field;

/**
 * Dto复制到pojo工具类
 */
public class BeanUtilCopy {

    /**
     * 类的属性复制：反射实现 dto 复制 pojo
     *
     * @param pojoClz pojo类的 Class 对象
     * @param dto dto类的实例对象
     * @param <T>
     * @return
     */
    public static <T> T copyDto(Class<T> pojoClz, Object dto) {
        try {
            T pojo = pojoClz.getConstructor().newInstance();
            Field[] fields = dto.getClass().getDeclaredFields();

            for (Field field : fields) {
                String value;
                BeanDtoCopy bcf = field.getAnnotation(BeanDtoCopy.class);
                if (bcf != null) {
                    value = bcf.value();
                } else {
                    value = field.getName();
                }
                try {
                    Field fieldT = pojoClz.getDeclaredField(value);
                    if (null != fieldT){
                        fieldT.setAccessible(true);
                        field.setAccessible(true);
                        fieldT.set(pojo,field.get(dto));
                    }
                }catch (NoSuchFieldException e){
                    //捕获了pojo中有pojo中没有的属性的异常
                    //e.printStackTrace();
                }

            }
            return pojo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}