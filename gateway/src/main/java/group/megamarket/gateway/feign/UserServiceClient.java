package group.megamarket.gateway.feign;

import group.megamarket.gateway.dto.user.RequestDto;
import group.megamarket.gateway.dto.user.RequestRoleDto;
import group.megamarket.gateway.dto.user.UserDto;
import group.megamarket.gateway.dto.user.UserRequestRoleDto;
import group.megamarket.gateway.dto.user.UserRoleDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Клиент для доступа к userService
 */
@FeignClient(value = "userService", url = "${user-service.url}")
public interface UserServiceClient {

    @PostMapping("/users")
    UserDto saveUser(@RequestBody UserDto userDto);

    @PostMapping("/requests")
    RequestRoleDto saveRequestRole(@RequestBody RequestRoleDto requestRole);

    @GetMapping("/users")
    List<UserRoleDto> getUsers();

    @GetMapping("/request")
    List<RequestDto> getAllRequests();

    @PatchMapping("/users")
    UserDto updateUserRole(@RequestBody UserRequestRoleDto userRequestRoleDto);

}
