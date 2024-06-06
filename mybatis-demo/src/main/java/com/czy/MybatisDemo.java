package com.czy;

import com.czy.mapper.UserMapper;
import com.czy.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * ClassName: MybatisDemo
 * Package: com.czy
 * Description:
 *
 * @Author Chen Ziyun
 * @Version 1.0
 */
public class MybatisDemo {
    @Test
    public void test1() throws Exception {
        // 1.加载核心配置类
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 2.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 3.执行sql语句
        List<User> users = sqlSession.selectList("test.selectAll");
        System.out.println(users);

        // 4.释放资源
        sqlSession.close();
    }

    /*使用Mapper代理方式*/
    @Test
    public void test2() throws Exception {
        // 1.加载核心配置类
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 2.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 3.执行sql语句
        // List<User> users = sqlSession.selectList("test.selectAll");

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = userMapper.selectAll();
        System.out.println(users);

        // 4.释放资源
        sqlSession.close();
    }
}
