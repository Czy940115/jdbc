package com.czy.jdbc;

import java.sql.*;

/**
 * ClassName: JdbcDemo
 * Package: com.czy.jdbc
 * Description:
 *      JDBC快速入门
 * @Author Chen Ziyun
 * @Version 1.0
 */
public class JdbcDemo {
    public static void main(String[] args) throws Exception {
        // 1.注册驱动
        // Class.forName("com.mysql.jdbc.Driver");

        // 2.创建连接
        String url = "jdbc:mysql://47.116.171.126:3306/db1?useSSL=false";
        String user = "root";
        String password = "228675";
        Connection connection = DriverManager.getConnection(url, user, password);

        // 3.创建sql语句
        String sql1 = "update tb_user set password = \"789\" where id = 1;";
        String sql2 = "update tb_user set password = \"789\" where id = 2;";

        try {
            connection.setAutoCommit(false);
            // 4.执行sql语句，获取结果
            Statement statement1 = connection.createStatement();
            int count1 = statement1.executeUpdate(sql1);

            // 5.处理结果
            System.out.println(count1);

            int i = 1/0;
            // 4.执行sql语句，获取结果
            Statement statement2 = connection.createStatement();
            int count2 = statement2.executeUpdate(sql2);

            // 5.处理结果
            System.out.println(count2);
            // 6.关闭连接
            statement1.close();
            statement2.close();
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw new RuntimeException(e);
        }
    }
}
