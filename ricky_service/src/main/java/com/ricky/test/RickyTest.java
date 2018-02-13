package com.ricky.test;

import com.ricky.user.entity.UserEntity;
import com.ricky.user.entity.UserEntityMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * Describetion
 * Created  by  zhuangjiayin
 * Date : 2018/2/13
 */
public class RickyTest   {

    @Test
    public  void testMybatis() throws  Exception{
          Logger logger = LoggerFactory
                .getLogger(RickyTest.class);
        //加载配置文件
        String resource = "maven_build/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 获取sqlSession
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        //取得框架的dao
        UserEntityMapper mapper = session.getMapper(UserEntityMapper.class);
        //验证准确性
        UserEntity userEntity = mapper.selectByPrimaryKey("2");
        System.out.println(userEntity.getName());
        logger.info("演示结束");
        session.close();
    }
}
