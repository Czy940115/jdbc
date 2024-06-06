package com.czy.exec;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.czy.pojo.Brand;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * ClassName: JDBCTest
 * Package: com.czy.exec
 * Description:
 *
 * @Author Chen Ziyun
 * @Version 1.0
 */
public class JDBCTest {
    @Test
    public void testSelectAll() throws Exception {
        // 1.获取连接
        // 1.导入jar包
        // 2.定义配置文件
        // 3.加载配置文件
        // System.out.println(System.getProperty("user.dir"));
        Properties properties = new Properties();
        System.out.println(System.getProperty("user.dir"));
        properties.load(new FileInputStream("src/druid.properties"));
        // 4.获取数据库连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        // 5.获取连接
        Connection connection = dataSource.getConnection();

        // 2.定义SQL语句
        String sql = "select * from tb_brand";
        // 3.获取pre参数
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 4.设置SQL参数

        // 5.执行sql语句
        ResultSet resultSet = preparedStatement.executeQuery();

        // 6.处理结果
        List<Brand> list = new ArrayList<>();
        Brand brand = null;
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String brandName = resultSet.getString("brand_name");
            brand = new Brand(id, brandName,null,null,null,null);
            list.add(brand);
        }
        System.out.println(list);

        // 7.关闭连接
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
