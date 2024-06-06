package com.czy.jdbc;

import org.junit.Test;

import java.sql.*;

/**
 * ClassName: JdbcDemo
 * Package: com.czy.jdbc
 * Description:
 *      JDBC快速入门
 * @Author Chen Ziyun
 * @Version 1.0
 */
public class JdbcDemoResultSet {

    @Test
    public void testDQL() throws Exception {
        // 1.注册驱动
        // Class.forName("com.mysql.jdbc.Driver");

        // 2.创建连接
        String url = "jdbc:mysql://47.116.171.126:3306/db1?useSSL=false";
        String user = "root";
        String password = "228675";
        Connection connection = DriverManager.getConnection(url, user, password);

        // 3.创建sql语句
        String sql = "select * from tb_user";
        // 4.执行sql语句，获取结果
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        // 5.处理结果
        // 5.1 移动游标 并 判断该行是否为有效行
        while (resultSet.next()){
            // 5.2 获取有效行的数据
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String pass = resultSet.getString(3);
            System.out.println("id = " + id + ",name = " + name + ",pass = " + pass);
        }

        // 6.关闭连接
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void testPreparedStatement() throws Exception {
        // 1.注册驱动
        // Class.forName("com.mysql.jdbc.Driver");

        // 2.创建连接
        // useServerPrepStatus=true:开启预编译
        String url = "jdbc:mysql://47.116.171.126:3306/db1?useSSL=false&useServerPrepStatus=true";
        String user = "root";
        String password = "228675";
        Connection connection = DriverManager.getConnection(url, user, password);

        // 3.创建sql语句
        String sql = "select * from tb_user where username = ? & password = ?";
        // 4.执行sql语句，获取结果
        PreparedStatement statement = connection.prepareStatement(sql);
        // 4.1 设置预编译语句参数
        statement.setString(1, "czy");
        statement.setString(2, "123");
        // 4.2 执行预编译的语句
        ResultSet resultSet = statement.executeQuery();

        // 5.处理结果
        // 5.1 移动游标 并 判断该行是否为有效行
        if (resultSet.next()){
            System.out.println("执行成功");
        }else {
            System.out.println("执行失败");
        }

        // 6.关闭连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}
