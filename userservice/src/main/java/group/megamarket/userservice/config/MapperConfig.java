package group.megamarket.userservice.config;

import group.megamarket.userservice.mapper.RequestMapper;
import group.megamarket.userservice.mapper.RoleMapper;
import group.megamarket.userservice.mapper.UserMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Создание бинов мапперов
 */
@Configuration
public class MapperConfig {

    /**
     * @return UserMapper
     */
    @Bean
    public UserMapper userMapper() {
        return Mappers.getMapper(UserMapper.class);
    }

    /**
     * @return RequestMapper
     */
    @Bean
    public RequestMapper requestMapper() {
        return Mappers.getMapper(RequestMapper.class);
    }

    /**
     * @return RoleMapper
     */
    @Bean
    public RoleMapper roleMapper() {
        return Mappers.getMapper(RoleMapper.class);
    }
}
