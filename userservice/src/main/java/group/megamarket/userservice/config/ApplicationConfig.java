package group.megamarket.userservice.config;

import group.megamarket.userservice.soap.StorageService;
import group.megamarket.userservice.soap.StorageServiceImplService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Общая конфигурация приложения
 */
@Configuration
@EnableWebMvc
@ComponentScan("group.megamarket.userservice")
@PropertySource("classpath:/application.properties")
public class ApplicationConfig {

    @Value("${storage.url}")
    private String storageServiceUrl;
    @Bean
    public StorageService storageService() throws MalformedURLException {
        QName serviceQName = new QName("http://localhost:8000/soap", "StorageServiceImplService");
        Service service = StorageServiceImplService.create(new URL(storageServiceUrl), serviceQName);
        return service.getPort(StorageService.class);
    }
}
