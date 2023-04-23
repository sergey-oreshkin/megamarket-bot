package group.megamarket.marketservice.configuration;

import group.megamarket.marketservice.dto.OrderProductDto;
import group.megamarket.marketservice.dto.OrderResponse;
import group.megamarket.marketservice.entity.Order;
import group.megamarket.marketservice.entity.OrderProduct;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

import java.util.stream.Collectors;

@Configuration
@PropertySource("classpath:/application.properties")
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper =  new ModelMapper();

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        TypeMap<OrderProduct, OrderProductDto> orderProductMap
                = modelMapper.typeMap(OrderProduct.class, OrderProductDto.class);

        orderProductMap.addMappings(mapping -> {
            mapping.map(op -> op.getPk().getProductId(), OrderProductDto::setProductId);
            mapping.map(OrderProduct::getQuantity, OrderProductDto::setQuantity);
        });

        return modelMapper;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}