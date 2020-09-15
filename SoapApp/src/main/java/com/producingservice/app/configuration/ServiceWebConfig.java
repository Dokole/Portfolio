package com.producingservice.app.configuration;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import javax.servlet.Servlet;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@EnableWs
@Configuration
public class ServiceWebConfig {


    @Bean
    public ServletRegistrationBean<Servlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean
    public XsdSchema itemSchema() {
        return new SimpleXsdSchema(new ClassPathResource("dtoSchemas/database.xsd"));
    }

    @Bean(name = "database")
    public Wsdl11Definition databaseWsdl11Definition() {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("databasePort");
        wsdl11Definition.setLocationUri("/ws/database");
        wsdl11Definition.setTargetNamespace("http://com.producingservice.app.dto.database");
        wsdl11Definition.setSchema(itemSchema());
        return wsdl11Definition;
    }
}
