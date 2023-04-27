package group.megamarket.storageservice.config;

import group.megamarket.storageservice.mapper.ProductMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Создание бинов мапперов
 */
@Configuration
public class MapperConfig {
    /**
     * @return ProductMapper
     */
    @Bean
    public ProductMapper productMapper() {
        return Mappers.getMapper(ProductMapper.class);
    }
}
