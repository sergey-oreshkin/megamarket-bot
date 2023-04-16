package group.megamarket.storageservice;

import group.megamarket.storageservice.config.ApplicationConfig;
import group.megamarket.storageservice.server.Server;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class StorageServiceApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
//        context.refresh();
        Server server = context.getBean(Server.class);
        server.start();
    }
}
