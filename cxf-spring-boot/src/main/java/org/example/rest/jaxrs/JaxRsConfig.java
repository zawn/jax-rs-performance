package org.example.rest.jaxrs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.ResourceProvider;
import org.apache.cxf.jaxrs.provider.JAXBElementProvider;
import org.apache.cxf.jaxrs.spring.SpringResourceFactory;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import javax.ws.rs.Path;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Map;

/**
 * Apache CXF 配置.
 * Created by Administrator on 2016/6/15.
 */
@Configuration
@ImportResource(locations = {"classpath:META-INF/cxf/cxf.xml",
        "classpath:META-INF/cxf/cxf-servlet.xml"})
public class JaxRsConfig {
    @Autowired
    private Bus bus;

    @Bean
    public ServletRegistrationBean cxfServlet() {
        final ServletRegistrationBean servletRegistrationBean =
                new ServletRegistrationBean(new CXFServlet(), "/*");
        return servletRegistrationBean;
    }

    @Bean
    public Server rsServer() {
        JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
        endpoint.setBus(bus);
        endpoint.setAddress("/");
        // Register 2 JAX-RS root resources supporting "/sayHello/{id}" and "/sayHello2/{id}" relative paths
        endpoint.setServiceBeans(Arrays.<Object>asList(new HelloResource()));
        endpoint.setApplication(new MyApplication());
        endpoint.setProvider(jacksonJsonProvider());
        return endpoint.create();
    }

    public JacksonJsonProvider jacksonJsonProvider() {
        JacksonJsonProvider jacksonJsonProvider = new JacksonJsonProvider();
        ObjectMapper mapper = new ObjectMapper();
//        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        jacksonJsonProvider.setMapper(mapper);
        return jacksonJsonProvider;
    }

}
