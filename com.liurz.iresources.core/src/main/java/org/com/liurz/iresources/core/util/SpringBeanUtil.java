package org.com.liurz.iresources.core.util;

import org.springframework.context.ApplicationContext;

/**
 * 获取动态bean
 */
public class SpringBeanUtil {
    public static ApplicationContext applicationContext;

    /**
     * 获取bean
     *
     * @param name name
     * @return Object
     */
    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }
    /**
     * 获取bean
     *
     * @param clazz clazz
     * @return Object
     */
    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }


}
