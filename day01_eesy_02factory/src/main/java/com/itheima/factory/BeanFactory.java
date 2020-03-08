package com.itheima.factory;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 创建一个Bean对象的工厂
 *
 * Bean：可重用组件的含义
 * JavaBean：用Java语言编写的可重用组件
 *          JavaBean > Bean
 *
 *      它就是创建servcie和dao的对象
 *
 *      第一个：需要一个配置文件来配置我们的service和dao
 *              配置的内容：唯一标识 = 全限定类名
 *      第二个：通过读取配置问价的内容，反射创建对象
 *
 *      配置文件类型:
 *              xml
 *              properties
 **/
public class BeanFactory {
    /* 定义一个properties对象 */
    private static Properties props;

    /* 定义一个map，用于存放我们要创建的对象，称为容器 */
    private static Map<String, Object> beans;

    /* 用一个静态代码块为Properties对象赋值 */
    static {
        try {
            /* 获取实例化对象 */
            props = new Properties();
            /* 获取Properties文件的流对象 */
            InputStream in = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
            props.load(in);

            /* 实例化容器 */
            beans = new HashMap<String, Object>();
            /* 去除配置文件中所有的key */
            Enumeration keys = props.keys();
            /* 遍历枚举 */
            while (keys.hasMoreElements()) {
                /* 取出每个key */
                String key = keys.nextElement().toString();
                /* 根据key获取value */
                String beanPath = props.getProperty(key);
                /* 反射创建对象 */
                Object value = Class.forName(beanPath).newInstance();
                /* 把key和value存入容器中 */
                beans.put(key, value);
            }
        } catch (Exception e) {
            throw new ExceptionInInitializerError("初始化properties对象");
        }
    }

    /**
     * 根据bean的名称获取对象
     * */
    public static Object getBean(String beanName) {
        return beans.get(beanName);
    }

/*
    /**
     * 根据bean名称后去bean对象

    public static Object getBean(String beanName) {
        Object bean = null;
        try {
            String beanPath = props.getProperty(beanName);
            bean = Class.forName(beanPath).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
*/

}













