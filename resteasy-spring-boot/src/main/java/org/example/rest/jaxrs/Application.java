package org.example.rest.jaxrs;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.jul.LevelChangePropagator;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@SpringBootApplication
@ServletComponentScan(basePackages = "org.example")
@ComponentScan(basePackages = "org.example")
//@ImportResource(locations = {
//        "classpath:/spring-configuration/*.xml",
//        "classpath*:/META-INF/spring/*.xml"})
public class Application extends SpringBootServletInitializer {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Application.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        // 该设置稍后会被初始化完成的Spring Boot覆盖.
        ((Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME)).setLevel(Level.INFO);
        application.profiles(getCurrentProfileName());
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
//        setHttpProxy();
        SpringApplicationBuilder builder = new SpringApplicationBuilder();
        builder.sources(Application.class);
        builder.profiles(getCurrentProfileName());
        ConfigurableApplicationContext applicationContext = builder.run(args);
        applicationContext.setId("www.xinjiakao.com");
    }

    private static void setHttpProxy() {
        System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("https.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "8888");
        System.setProperty("https.proxyPort", "8888");

        System.setProperty("javax.net.ssl.trustStore", "C:\\Users\\Administrator\\cacerts");
        System.setProperty("javax.net.ssl.trustStorePassword", "changeit");
        System.setProperty("sun.security.ssl.allowUnsafeRenegotiation", "true");
    }

    private static String getCurrentProfileName() {
        String profileName = null;
        String hardwareAddress = getHardwareAddress();

        Map<String, String> profileMap = getProfileMap();
        if (profileMap != null && profileMap.containsKey(hardwareAddress)) {
            profileName = profileMap.get(hardwareAddress);
        }
        if (profileName == null || "default".equals(profileName)) {
            // TODO: 应在正式环境中禁用Spring DevTools.
            // @see http://docs.spring.io/spring-boot/docs/1.3.5.RELEASE/reference/html/using-boot-devtools.html
            // TODO: 部分设置可能需要小心处理。
            // @see http://docs.spring.io/spring-boot/docs/1.3.5.RELEASE/reference/html/production-ready.html

            System.setProperty("spring.devtools.restart.enabled", "false");
            profileName = "default";
        }
        logger.info("Current profile name   : {}", profileName);
        return profileName;
    }

    private static Map<String, String> getProfileMap() {
        Properties properties = new Properties();
        try {
            //load a properties file from class path, inside static method
            properties.load(Application.class.getResourceAsStream("/spring-profiles.properties"));
            Map<String, String> profileMap = new HashMap<>();
            Set<String> propertyNames = properties.stringPropertyNames();
            for (String propertyName : propertyNames) {
                properties.getProperty(propertyName);
                String propertyValue = properties.getProperty(propertyName);
                String profileName = propertyName.split("\\.")[1];
                profileMap.put(propertyValue, profileName);
            }
            return profileMap;
        } catch (Exception e) {
            logger.error("error load spring-profiles.properties", e);
        }
        return null;
    }

    private static String getHardwareAddress() {
        InetAddress ip;
        try {
            ip = getLocalHost();
            if (ip != null) {
                logger.info("正在启动");
                logger.info("Current IP address     : {}", ip.getHostAddress());
            }
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            byte[] mac = network.getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            String macString = sb.toString();
            logger.info("Current MAC address    : {}", macString);
            return macString;
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static InetAddress getLocalHost() {
        try {
            for (Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces(); interfaces.hasMoreElements(); ) {
                NetworkInterface networkInterface = interfaces.nextElement();
                if (networkInterface.isLoopback() || networkInterface.isVirtual() || !networkInterface.isUp()) {
                    continue;
                }
                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                if (addresses.hasMoreElements()) {
                    return addresses.nextElement();
                }
            }
        } catch (SocketException e) {
            logger.debug("Error when getting host ip address: <{}>.", e);
        }
        return null;
    }

    /**
     * Spring Boot has been setting.
     */
    private static void configureJULBridge() {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        // Install the bridge
        LevelChangePropagator levelChangePropagator = new LevelChangePropagator();
        levelChangePropagator.setContext(loggerContext);
        levelChangePropagator.setResetJUL(true);
        loggerContext.addListener(levelChangePropagator);
        SLF4JBridgeHandler.install();
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }


}