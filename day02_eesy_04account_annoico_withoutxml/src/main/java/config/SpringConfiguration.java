package config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;

/**
 * 该类是一个配置类，它的作用和beam.xml是一样的
 * spring中的新注解
 * Configuration
 *      作用：指定当前类是一个配置类
 * ComponentScan
 *      作用：用于通过注解指定spring在创建容器时要扫描的包
 *      属性：
 *          value：它和basePackages的作用是一样的，都是用于指定创建容器时要扫描的包
 *                  我们使用此注解就等同于在xml中配置了
 *                  <context:component-scan base-package="com.itheima"></context:component-scan>
 * Bean：
 *      作用：用于把当前方法的返回值作为bean对象存入spring的ioc容器
 *      属性：
 *          name：用于指定bean的id，当不写时，默认值是当前的方法名称
 *      细节：
 *          当我们使用注解配置方法时，如果方法有参数，spring框架会去容器中查找有没有课用的bean对象
 *          查找方式和Autowired注解的作用是一样的
 * */

@Configuration
@ComponentScan("com.itheima")
public class SpringConfiguration {

    /**
     * 用于创建一个QueryRunner对象
     * */
    @Bean(name = "runner")
    @Scope("prototype")
    public QueryRunner createQueryRunner(DataSource dataSource) {
        return new QueryRunner(dataSource);
    }

    /**
     * 创建数据源对象
     * */
    @Bean(name = "dataSource")
    public DataSource createDataSource() {
        try {
            ComboPooledDataSource ds = new ComboPooledDataSource();
            ds.setDriverClass("com.mysql.jdbc.Driver");
            ds.setJdbcUrl("jdbc:mysql://localhost:3306/eesy");
            ds.setUser("root");
            ds.setPassword("zhangxiang133");
            return ds;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}






