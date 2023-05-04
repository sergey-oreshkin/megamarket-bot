package group.megamarket.marketservice.configuration;

import group.megamarket.marketservice.entity.OrderProduct;
import group.megamarket.marketservice.soap.ProductDto;
import group.megamarket.marketservice.soap.StorageService;
import group.megamarket.marketservice.soap.StorageServiceImplService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Конфигурационный класс приложения для создания и настройки бинов.
 *
 * @author Eldar
 */
@Configuration
@PropertySource("classpath:/application.properties")
public class AppConfig {

    /**
     * Создает экземпляр ModelMapper и настраивает его конфигурацию.
     *
     * @return ModelMapper
     */
    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setSkipNullEnabled(true)
                .setFieldMatchingEnabled(true);

        var orderProductMapToProductDto = modelMapper.typeMap(OrderProduct.class, ProductDto.class);
        orderProductMapToProductDto.addMappings(mapping -> {
            mapping.map(OrderProduct::getProductId, ProductDto::setId);
            mapping.map(OrderProduct::getQuantity, ProductDto::setCount);
        });

        return modelMapper;
    }

    /**
     * @return объект StorageService
     * @throws MalformedURLException если URL-адрес некорректен
     */
    @Bean
    public StorageService storageService() throws MalformedURLException {
        var serviceQName = new QName("http://localhost:8000/soap", "StorageServiceImplService");
        var service = StorageServiceImplService.create(new URL("http://storage:8000/soap?wsdl"), serviceQName);
        return service.getPort(StorageService.class);
    }
}