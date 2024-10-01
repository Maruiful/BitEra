package com.github.paicoding.forum.core.util;

import org.springframework.beans.BeansException;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/** */
@Component
public class SpringUtil implements ApplicationContextAware, EnvironmentAware {
    private volatile static ApplicationContext context;
    private volatile static Environment environment;

    private static Binder binder;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException  {}

    @Override
    public void setEnvironment(Environment environment)  {}

    /**
     * 获取ApplicationContext
     *
     * @return
     */
    public static ApplicationContext getContext()  { return null; }

    /**
     * 获取bean
     *
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> bean) {
        if (context != null) {
            return context.getBean(bean);
        } else {
            throw new IllegalStateException("Spring ApplicationContext is not active or has been closed.");
        }
    }

    public static <T> T getBeanOrNull(Class<T> bean) {
        try {
            return context.getBean(bean);
        } catch (Exception e) {
            return null;
        }
    }

    public static Object getBean(String beanName)  { return null; }

    public static Object getBeanOrNull(String beanName)  { return null; }

    public static boolean hasConfig(String key)  { return false; }

    /**
     * 获取配置
     *
     * @param key
     * @return
     */
    public static String getConfig(String key)  { return null; }

    public static String getConfigOrElse(String mainKey, String slaveKey)  { return null; }

    /**
     * 获取配置
     *
     * @param key
     * @param val 配置不存在时的默认值
     * @return
     */
    public static String getConfig(String key, String val)  { return null; }

    /**
     * 发布事件消息
     *
     * @param event
     */
    public static void publishEvent(ApplicationEvent event)  {}


    /**
     * 配置绑定类
     *
     * @return
     */
    public static Binder getBinder()  { return null; }
}
