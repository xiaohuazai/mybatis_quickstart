package com.lagou.test;

import com.lagou.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @创建者 小华华
 * @创建时间 2021/6/24 23:34
 * @描述
 */
public class MyBatisTest {

    @Test
    public void mybatisQuickStart() throws IOException {

        //1 加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2 获取 sqlSessionFactory 工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //3 获取sqlSession会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //4 执行sql 参数：statementid： namespace.id
        List<User> users = sqlSession.selectList("userMapper.findAll");
        // 5 遍历打印
        for (User user : users) {
            System.out.println(user);
        }
        //
        sqlSession.close();

    }

    @Test
    public void testSave() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        User user = new User();
        user.setUsername("zhouyu");
        user.setBirthday(new Date());
        user.setAddress("济南");
        user.setSex("男");
        sqlSession.insert("userMapper.saveUser", user);
        //sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testUpdate() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User();
        user.setId(3);
        user.setUsername("刘备");
        user.setBirthday(new Date());
        user.setAddress("荆州");
        user.setSex("男");
        sqlSession.update("userMapper.update", user);
        sqlSession.commit();
        sqlSession.close();
    }


    @Test
    public void testDelete() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();


        sqlSession.delete("userMapper.delete", 4 );
        sqlSession.commit();
        sqlSession.close();
    }
}
