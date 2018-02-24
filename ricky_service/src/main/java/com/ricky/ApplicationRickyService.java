package com.ricky;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication(scanBasePackages = {"com.ricky", },
        exclude = org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration.class)
@ImportResource({
        "classpath:/META-INF/spring/appCtx-provider.xml",
        "classpath:/META-INF/spring/appCtx-dubbo-client.xml",
        "classpath:/META-INF/spring/mybatis-config.xml"

})
@EnableAsync
public class ApplicationRickyService {

    private static Logger logger = LoggerFactory
            .getLogger(ApplicationRickyService.class);

    public static void main(String[] args) throws InterruptedException {
//        BasicConfigurator.configure();
        new SpringApplicationBuilder().sources(ApplicationRickyService.class).web(false).run(args);
        logger.info("ApplicationPeppaService 启动完成 info");
        logger.debug("ApplicationPeppaService 启动完成 debug！");
        System.out.println("ApplicationPeppaService 1启动完成！");
        new CountDownLatch(1).await();
    }

}