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
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SimpleTimeZone;

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
//        String resource = "maven_build/mybatis-config.xml";
        String resource = "META-INF/spring/mybatis-config.xml";
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
    @Test
    public  void testStringFomat() throws  Exception{
        System.out.println(String.format("%1$,.100f", new BigDecimal("12345678909.1234567890123456789")));
//转换结果保留5位小数，3位数字用,隔开
//输出：1,234,567,890.12346
        System.out.println(String.format("%1$,.2f", new BigDecimal(1234567890.123456789)));
        BigDecimal rent = new BigDecimal(0.01);
        BigDecimal serviceFee =new BigDecimal(0.01);
        BigDecimal bond = new BigDecimal(0.01);
        System.out.println(String.format("%1$,.2f元",rent));
        String templae="%s,租金：%1$,.2f 元/月，服务费：%1$,.2f元/月，保证金：%1$,.2f元 ";
        System.out.println(String.format(templae,"11",rent,serviceFee,bond));

    }
    @Test
    public  void  testEnum(){
        EnumFruit apple=EnumFruit.APPLE;
        EnumFruit pine=EnumFruit.PINE;
        Map<EnumFruit,String> map=new HashMap<EnumFruit, String>();
        map.put(apple,"苹果");
        map.put(EnumFruit.ORIANGE,"juzi");
        System.out.println(map.get(apple));
        System.out.println(map.get(EnumFruit.APPLE));
        System.out.println(map.get(EnumFruit.ORIANGE));
        System.out.println(map.containsKey(apple));
        System.out.println(map.containsKey(EnumFruit.ORIANGE));
    }

    @Test
    public  void  testDate() throws ParseException {
        Date now=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date d1=sdf.parse("2018-04-15");
        Date d2=sdf.parse("2018-04-16");
        Date d3=sdf.parse("2018-04-17");
        System.out.println(d1.after(now));
        System.out.println(d2.after(now));
        System.out.println(d3.after(now));
        System.out.println(d1.after(d2));
    }

}
