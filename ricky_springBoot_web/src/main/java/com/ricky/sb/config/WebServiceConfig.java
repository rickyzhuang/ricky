package com.ricky.sb.config;

import com.ricky.sb.api.IWebServiceTestService;
import com.ricky.sb.service.WebServiceTestService;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * Describetion
 * Created  by  zhuangjiayin
 * Date : 2019/5/27
 */
@Configuration
public class WebServiceConfig {

        @Bean
        public ServletRegistrationBean dispatcherServlet(){
            return new ServletRegistrationBean(new CXFServlet(),"/service/*");//发布服务名称
        }

        @Bean(name = Bus.DEFAULT_BUS_ID)
        public SpringBus springBus()
        {
            return  new SpringBus();
        }

        @Bean
        public IWebServiceTestService webServiceTestService()
        {
            return  new WebServiceTestService();
        }

        @Bean
        public Endpoint endpoint() {
            EndpointImpl endpoint=new EndpointImpl(springBus(), webServiceTestService());//绑定要发布的服务
            endpoint.publish("/user"); //显示要发布的名称
            return endpoint;
        }
}
