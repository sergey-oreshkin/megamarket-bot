package group.megamarket.userservice.service.impl;

import group.megamarket.userservice.exception.UserNotFoundException;
import group.megamarket.userservice.mapper.UserMapper;
import group.megamarket.userservice.model.dto.UserDto;
import group.megamarket.userservice.model.dto.UserRequestRoleDto;
import group.megamarket.userservice.model.entity.Role;
import group.megamarket.userservice.model.entity.RoleEnum;
import group.megamarket.userservice.model.entity.User;
import group.megamarket.userservice.repository.UserRepository;
import group.megamarket.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public List<User> findAllAdminAndSeller() {
        return userRepository.findAllAdminAndSeller();
    }

    @Override
    public UserDto save(UserDto userDto) {
        //нужна ли проверка на нал?
        User user = userRepository.save(userMapper.toUser(userDto));
        Set<Role> roles = new HashSet<>();
        Role role1 = new Role();
        role1.setRoleEnum(RoleEnum.USER);
        roles.add(role1);
        user.setRoles(roles);
        return userMapper.toUserDto(user);
    }

    @Override
    public Set<Role> findRoleUserByYserId(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("There is no such user")).getRoles();
    }

    @Override
    public UserDto updateUserRole(UserRequestRoleDto userRequestRoleDto) {
        User user = userRepository.findById(userRequestRoleDto.getUserId()).orElseThrow(() -> new UserNotFoundException("There is no such user"));
        Set<Role> userRole = user.getRoles();

        if(userRequestRoleDto.getIsAdmin() != null){
            Role role1 = new Role();
            role1.setRoleEnum(RoleEnum.ADMIN);
            userRole.add(role1);
        }
        if(userRequestRoleDto.getIsSeller() != null){
            Role role2 = new Role();
            role2.setRoleEnum(RoleEnum.SELLER);
            userRole.add(role2);
        }

        user.setRoles(userRole);

        return userMapper.toUserDto(userRepository.save(user));
    }
}
