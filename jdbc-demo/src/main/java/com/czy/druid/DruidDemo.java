package com.czy.druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * ClassName: DruidDemo
 * Package: com.czy.druid
 * Description:
 *
 * @Author Chen Ziyun
 * @Version 1.0
 */
public class DruidDemo {
    public static void main(String[] args) throws Exception {
        // 1.导入jar包
        // 2.定义配置文件
        // 3.加载配置文件
        // System.out.println(System.getProperty("user.dir"));
        Properties properties = new Properties();
        properties.load(new FileInputStream("jdbc-demo/src/druid.properties"));

        // 4.获取数据库连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);

        // 5.获取连接
        Connection connection = dataSource.getConnection();
    }
}
