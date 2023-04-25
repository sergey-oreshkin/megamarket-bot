package group.megamarket.gateway.feign;

import group.megamarket.gateway.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Клиент для доступа к userService
 */
@FeignClient(value = "userService", url = "${user-service.url}")
public interface UserServiceClient {

    @PostMapping("/users")
    UserDto saveUser(@RequestBody UserDto userDto);
}
