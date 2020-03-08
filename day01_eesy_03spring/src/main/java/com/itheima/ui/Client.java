package com.itheima.ui;

import com.itheima.dao.IAccountDao;
import com.itheima.service.IAccountService;
import com.itheima.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * 模拟一个表现层，用于调用业务
 * */
public class Client {
    /**
     * 获取spring的Ioc核心容器，并根据id获取对象
     *
     * ApplicationContext的三个常用实现类：
     *          ClassPathXmlApplicationContext  : 它可以加载类路径下的配置文件，要求配置文件必须在类路径下，否则加载不了(更常用)
     *          FileSystemXmlApplicationContext     : 它可以加载磁盘任意路径下的文件（必须有访问权限）
     *          AnnotationConfigApplicationContext  : 它用于读取注解创建容器
     *
     * 核心容器的两个接口引发的问题:
     *    ApplicationContext：单例对象适用     (采用的更多)
     *          在构建核心容器时，创建对象采取的策略是采用立即加载模式。即，一读完配置文件，立马创建配置文件中的配置对象
     *    BeanFactory:        多例对象适用
     *          在构建核心容器时，创建对象采取的策略是采用延迟加载的方式。即，什么时候根据id获取对象，什么时候创建真正的对象
     *
     */
    public static void main(String[] args) {
//        /* 1. 获取核心容器对象 */
//        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
////        ApplicationContext ac = new FileSystemXmlApplicationContext("bean.xml");
//        /* 2. 根据id获取Bean对象 */
//        IAccountService as = (IAccountService) ac.getBean("accountService");
//        IAccountDao adao = ac.getBean("accountDao", IAccountDao.class);
//
//        System.out.println(as);
//        System.out.println(adao);
////        as.saveAccount();


        /*-------------------------------BeanFactory------------------------------------*/
        Resource resource = new ClassPathResource("bean.xml");
        BeanFactory factory = new XmlBeanFactory(resource);
        IAccountService as = (IAccountService) factory.getBean("accountService");
        System.out.println(as);

    }
}
