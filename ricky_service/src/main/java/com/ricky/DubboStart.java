package com.ricky;

import ch.qos.logback.classic.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Describetion
 * Created  by  zhuangjiayin
 * Date : 2018/2/23
 */
public class DubboStart {
/*    private  DubboStart(){}*/
    private static final  Logger LOGGER = LoggerFactory.getLogger(DubboStart.class);
    /**
     * 启动main方法
     *
     * @param args 参数
     */
    public static void main(String[] args) throws IOException {
        LOGGER.info("====================ricky service 启动成功1====================");
        com.alibaba.dubbo.container.Main.main(args);

//        System.setProperty("java.net.preferIPv4Stack", "true");
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/appCtx-provider.xml","META-INF/spring/mybatis-config.xml"});
//        context.start();

//        System.in.read(); // press any key to exit
        LOGGER.info("====================ricky service 启动成功2====================");
    }
}
