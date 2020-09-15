package com.transcacheservice.cacheapp.configuration;

import com.transcacheservice.cacheapp.repository.soapRepository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Zahar Zaitsev
 * @version 1.0
 */

@Configuration
@ComponentScan(basePackages = "com.transcacheservice.cacheapp")
public class SoapConsumingConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.transcacheservice.cacheapp.dto.database");
        return marshaller;
    }

    @Bean
    public ItemSoapClient itemSoapClient(Jaxb2Marshaller marshaller) {
        ItemSoapClient itemClient = new ItemSoapClient();
        itemClient.setDefaultUri("http://localhost:8080/ws");
        itemClient.setMarshaller(marshaller);
        itemClient.setUnmarshaller(marshaller);
        return itemClient;
    }

    @Bean
    public BrandSoapClient brandSoapClient(Jaxb2Marshaller marshaller) {
        BrandSoapClient brandClient = new BrandSoapClient();
        brandClient.setDefaultUri("http://localhost:8080/ws");
        brandClient.setMarshaller(marshaller);
        brandClient.setUnmarshaller(marshaller);
        return brandClient;
    }

    @Bean
    public CategorySoapClient categorySoapClient(Jaxb2Marshaller marshaller) {
        CategorySoapClient categoryClient = new CategorySoapClient();
        categoryClient.setDefaultUri("http://localhost:8080/ws");
        categoryClient.setMarshaller(marshaller);
        categoryClient.setUnmarshaller(marshaller);
        return categoryClient;
    }

    @Bean
    public ManufacturerSoapClient manufacturerSoapClient(Jaxb2Marshaller marshaller) {
        ManufacturerSoapClient manufacturerClient = new ManufacturerSoapClient();
        manufacturerClient.setDefaultUri("http://localhost:8080/ws");
        manufacturerClient.setMarshaller(marshaller);
        manufacturerClient.setUnmarshaller(marshaller);
        return manufacturerClient;
    }

    @Bean
    public ObjectCreationDataClient objectCreationDataClient(Jaxb2Marshaller marshaller) {
        ObjectCreationDataClient metadataClient = new ObjectCreationDataClient();
        metadataClient.setDefaultUri("http://localhost:8080/ws");
        metadataClient.setMarshaller(marshaller);
        metadataClient.setUnmarshaller(marshaller);
        return metadataClient;
    }
}
