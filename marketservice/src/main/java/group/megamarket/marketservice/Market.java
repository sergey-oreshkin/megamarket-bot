package group.megamarket.marketservice;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class Market {
    public static void main(String[] args) throws LifecycleException {
        var tomcat = new Tomcat();
        tomcat.setSilent(true);
        tomcat.getConnector().setPort(8085);

        var tomcatContext = tomcat.addContext("", null);

        var applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.scan("group.megamarket.marketservice");
        applicationContext.setServletContext(tomcatContext.getServletContext());
        applicationContext.refresh();

        // добавляем диспетчер запросов
        var dispatcherServlet = new DispatcherServlet(applicationContext);
        var dispatcherWrapper = Tomcat.addServlet(tomcatContext, "dispatcher", dispatcherServlet);
        dispatcherWrapper.addMapping("/");
        dispatcherWrapper.setLoadOnStartup(1);

        tomcat.start();
    }
}
