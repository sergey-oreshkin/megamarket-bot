package group.megamarket.marketservice.configuration;

import group.megamarket.marketservice.dto.OrderProductDto;
import group.megamarket.marketservice.dto.OrderRequestDto;
import group.megamarket.marketservice.entity.OrderProduct;
import group.megamarket.marketservice.entity.OrderProductPK;
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

@Configuration
@PropertySource("classpath:/application.properties")
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {

        var modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                   .setMatchingStrategy(MatchingStrategies.STRICT)
                   .setSkipNullEnabled(true);

        var orderProductMap = modelMapper.typeMap(OrderProduct.class, OrderProductDto.class);
        orderProductMap.addMappings(mapping -> {
            mapping.map(op -> op.getPk().getProductId(), OrderProductDto::setProductId);
            mapping.map(OrderProduct::getQuantity, OrderProductDto::setQuantity);
        });

        var orderRequestDtoMapOrderProduct = modelMapper.typeMap(OrderRequestDto.class, OrderProduct.class);
        orderRequestDtoMapOrderProduct.addMapping(OrderRequestDto::getQuantity, OrderProduct::setQuantity);

        var orderRequestDtoMapOrderProductPK = modelMapper.typeMap(OrderRequestDto.class, OrderProductPK.class);
        orderRequestDtoMapOrderProductPK.addMapping(OrderRequestDto::getProductId, OrderProductPK::setProductId);

        var orderProductMapToProductDto = modelMapper.typeMap(OrderProduct.class, ProductDto.class);
        orderProductMapToProductDto.addMappings(mapping -> {
            mapping.map(op -> op.getPk().getProductId(), ProductDto::setId);
            mapping.map(OrderProduct::getQuantity, ProductDto::setCount);
        });

        return modelMapper;
    }

    @Bean
    public StorageService storageService() throws MalformedURLException {
        var serviceQName = new QName("http://localhost:8000/soap", "StorageServiceImplService");
        var service = StorageServiceImplService.create(new URL("http://localhost:8000/soap?wsdl"), serviceQName);
        return service.getPort(StorageService.class);
    }


}