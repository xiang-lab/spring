package com.itheima.test;

import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import com.itheima.service.impl.AccountServiceImpl;
import config.SpringConfiguration;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * 使用Junit单元测试：测试我们的配置
 * */
public class AccountServiceTest {

    @Test
    public void testFindAll() {
        /* 1. 获取容器 */
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        /* 2. 得到业务层对象  */
        IAccountService as = ac.getBean("accountService", AccountServiceImpl.class);
        /* 3. 执行方法 */
        List<Account> accounts = as.findAllAccount();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void testFindOne() {
        /* 1. 获取容器 */
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        /* 2. 得到业务层对象  */
        IAccountService as = ac.getBean("accountService", AccountServiceImpl.class);
        /* 3. 执行方法 */
        Account account = as.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testSave() {
        /* 1. 获取容器 */
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        /* 2. 得到业务层对象  */
        IAccountService as = ac.getBean("accountService", AccountServiceImpl.class);
        /* 3. 执行方法 */
        Account account = new Account();
        account.setName("zhangxiang");
        account.setMoney(100000000f);
        as.saveAccount(account);
        testFindAll();
    }

    @Test
    public void testUpdate() {
        /* 1. 获取容器 */
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        /* 2. 得到业务层对象  */
        IAccountService as = ac.getBean("accountService", AccountServiceImpl.class);
        /* 3. 执行方法 */
        Account account = new Account();
        account.setId(4);
        account.setName("xiangzhang");
        account.setMoney(200000000f);
        as.updateAccount(account);
        testFindAll();
    }

    @Test
    public void testDelete() {
        /* 1. 获取容器 */
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        /* 2. 得到业务层对象  */
        IAccountService as = ac.getBean("accountService", AccountServiceImpl.class);
        /* 3. 执行方法 */
        as.deleteAccount(4);
        testFindAll();
    }
}
