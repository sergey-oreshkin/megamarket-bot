package group.megamarket.userservice.controller;

import group.megamarket.userservice.mapper.RoleMapper;
import group.megamarket.userservice.mapper.UserMapper;
import group.megamarket.userservice.model.dto.RoleDto;
import group.megamarket.userservice.model.dto.UserDto;
import group.megamarket.userservice.model.dto.UserRequestRoleDto;
import group.megamarket.userservice.model.dto.UserRoleDto;
import group.megamarket.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Контроллер для работы c user endpoints
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users",
        produces = "application/json")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

    @GetMapping
    public List<UserRoleDto> getUsers() {
        List<UserRoleDto> userRoleDtos = userMapper.toListUserRoleDto(userService.findAllAdminAndSeller());
        log.info("received userRoleDtos: {}", userRoleDtos);
        return userRoleDtos;
    }

    @GetMapping("/{id}")
    public Set<RoleDto> getRoleUserById(@PathVariable(value = "id") Long id) {
        log.info("userId: {}", id);
        Set<RoleDto> roleDtos = roleMapper.toSetRoleDto(userService.findRoleUserByUserId(id));
        log.info("get roleDto: {}", roleDtos);
        return roleDtos;
    }

    @PostMapping
    public UserDto saveUser(@RequestBody UserDto userDto) {
        log.info("save user. UserDto: {}", userDto);
        UserDto savedUserDto = userService.save(userDto);
        log.info("user saved. savedUser: {}", savedUserDto);
        return savedUserDto;
    }

    @PatchMapping
    public UserDto updateUserRole(@RequestBody UserRequestRoleDto userRequestRoleDto) {
        log.info("incoming userRequestRoleDto: {}", userRequestRoleDto);
        UserDto userDto = userService.updateUserRole(userRequestRoleDto);
        log.info("updated userDto: {}", userDto);
        return userDto;
    }
}
