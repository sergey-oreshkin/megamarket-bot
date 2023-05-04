package group.megamarket.gateway.config;

import group.megamarket.gateway.soap.StorageService;
import group.megamarket.gateway.soap.StorageServiceImplService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Класс для конфигурации soap сервиса
 */
@Configuration
public class SoapConfig {

    @Value("${storage-service.url}")
    String url;

    @Bean
    public StorageService storageService() throws MalformedURLException {
        QName serviceQName = new QName("http://localhost:8000/soap", "StorageServiceImplService");
        Service service = StorageServiceImplService.create(new URL(url), serviceQName);
        return service.getPort(StorageService.class);
    }
}
