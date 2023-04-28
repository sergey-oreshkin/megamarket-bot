package group.megamarket.userservice;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class UserServiceApp {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setSilent(true);
        tomcat.getConnector().setPort(8082);

        Context tomcatContext = tomcat.addContext("", null);

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.scan("group.megamarket.userservice");
        context.setServletContext(tomcatContext.getServletContext());
        context.refresh();

        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
        Wrapper wrapper = Tomcat.addServlet(tomcatContext, "dispatcher", dispatcherServlet);
        wrapper.addMapping("/");
        wrapper.setLoadOnStartup(1);

        tomcat.start();
    }
}
