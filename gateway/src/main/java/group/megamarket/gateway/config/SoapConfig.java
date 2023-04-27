package group.megamarket.gateway.config;

import group.megamarket.gateway.soap.StorageService;
import group.megamarket.gateway.soap.StorageServiceImplService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

@Configuration
public class SoapConfig {

    @Bean
    public StorageService storageService() throws MalformedURLException {
        QName serviceQName = new QName("http://localhost:8000/soap", "StorageServiceImplService");
        Service service = StorageServiceImplService.create(new URL("http://localhost:8000/soap?wsdl"), serviceQName);
        return service.getPort(StorageService.class);
    }
}
