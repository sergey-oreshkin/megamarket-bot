package group.megamarket.userservice.controller;

import group.megamarket.userservice.mapper.RoleMapper;
import group.megamarket.userservice.mapper.UserMapper;
import group.megamarket.userservice.model.dto.RoleDto;
import group.megamarket.userservice.model.dto.UserDto;
import group.megamarket.userservice.model.dto.UserRequestRoleDto;
import group.megamarket.userservice.model.dto.UserRoleDto;
import group.megamarket.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users",
                produces = "application/json")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;

//    @GetMapping
//    public List<UserRoleDto> users(){
//        System.out.println("aaa");
//        List<User> userList =  userService.findAllAdminAndSeller();
//        System.out.println(userList);
//        System.out.println("aaa");
//        List<UserRoleDto> roleDtos = new ArrayList<>();
//        for (User user:userService.findAllAdminAndSeller()){
//            UserRoleDto roleDto = userMapper.toUserRoleDto(user);
//            roleDtos.add(roleDto);
//        }
//        return roleDtos;
//    }

    @GetMapping
    public List<UserRoleDto> getUsers(){
        return userMapper.toListUserRoleDto(userService.findAllAdminAndSeller());
    }

    @GetMapping("/{id}")
    public Set<RoleDto> getRoleUserById(@PathVariable(value = "id") Long id){
        return roleMapper.toSetRoleDto(userService.findRoleUserByUserId(id));
    }

    @PostMapping
    public UserDto saveUser(@RequestBody UserDto userDto){
        return userService.save(userDto);
    }

    @PatchMapping
    public UserDto updateUserRole(@RequestBody UserRequestRoleDto userRequestRoleDto){
        return userService.updateUserRole(userRequestRoleDto);
    }
}
