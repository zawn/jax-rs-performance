package org.example.rest.jaxrs;

import org.apache.catalina.Context;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;

import java.io.File;
import java.net.URISyntaxException;

public class RESTEasyTomcatMain {

    public static void main(String[] args) throws Exception {

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector(); // Trigger the creation of the default connector

        String docBase = getRootFolder().getPath();
        System.out.println(docBase);
        Context ctx = tomcat.addContext("/", docBase);

//        Tomcat.addServlet(ctx, "Embedded", new HttpServlet() {
//            @Override
//            protected void service(HttpServletRequest req, HttpServletResponse resp)
//                    throws ServletException, IOException {
//
//                Writer w = resp.getWriter();
//                w.write("Embedded Tomcat servlet.\n");
//                w.flush();
//                w.close();
//            }
//        });

        Wrapper restEasy = tomcat.addServlet("/", "RESTEasy", new HttpServletDispatcher());
        restEasy.addInitParameter("resteasy.servlet.mapping.prefix", "/");
        restEasy.addInitParameter("javax.ws.rs.Application", "org.example.rest.jaxrs.MyApplication");
        restEasy.addMapping("/*");

//        ctx.addServletMappingDecoded("/*", "Embedded");

        tomcat.start();
        tomcat.getServer().await();
    }

    private static File getRootFolder() {
        try {
            File root;
            String runningJarPath = RESTEasyTomcatMain.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath().replaceAll("\\\\", "/");
            int lastIndexOf = runningJarPath.lastIndexOf("/build/");
            if (lastIndexOf < 0) {
                root = new File("");
            } else {
                root = new File(runningJarPath.substring(0, lastIndexOf));
            }
            System.out.println("application resolved root folder: " + root.getAbsolutePath());
            return root;
        } catch (URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }
}