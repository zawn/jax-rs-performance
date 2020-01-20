package org.example.rest.jaxrs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Apache CXF 配置.
 * Created by Administrator on 2016/6/15.
 */
@Configuration
public class JaxRsConfig {

    @Autowired
    private ApplicationContext ctx;

    /*@Bean
    public ServletRegistrationBean cxfServlet() {
        final ServletRegistrationBean servletRegistrationBean =
                new ServletRegistrationBean(new CXFServlet(), "/rest/*");
        return servletRegistrationBean;
    }*/

    @Bean
    public ServletRegistrationBean RESTEasyServlet() {
        final ServletRegistrationBean servletRegistrationBean =
                new ServletRegistrationBean(new HttpServletDispatcher(), "/*");
        servletRegistrationBean.addInitParameter("resteasy.servlet.mapping.prefix", "/");
        servletRegistrationBean.addInitParameter("javax.ws.rs.Application", "org.example.rest.jaxrs.MyApplication");
        return servletRegistrationBean;
    }


    @Bean
    public JacksonJsonProvider jacksonJsonProvider() {
        JacksonJsonProvider jacksonJsonProvider = new JacksonJsonProvider();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        jacksonJsonProvider.setMapper(mapper);
        return jacksonJsonProvider;
    }

}
