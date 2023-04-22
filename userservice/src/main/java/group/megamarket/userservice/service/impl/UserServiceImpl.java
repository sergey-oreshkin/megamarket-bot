package group.megamarket.userservice.service.impl;

import group.megamarket.userservice.exception.UserNotFoundException;
import group.megamarket.userservice.mapper.UserMapper;
import group.megamarket.userservice.model.dto.UserDto;
import group.megamarket.userservice.model.dto.UserRequestRoleDto;
import group.megamarket.userservice.model.entity.Role;
import group.megamarket.userservice.model.entity.RoleEnum;
import group.megamarket.userservice.model.entity.User;
import group.megamarket.userservice.repository.RoleRepository;
import group.megamarket.userservice.repository.UserRepository;
import group.megamarket.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserMapper userMapper;

    @Override
    public List<User> findAllAdminAndSeller() {
        return userRepository.findAllAdminAndSeller();
    }

    @Override
    public UserDto save(UserDto userDto) {
        if(userDto == null) throw new RuntimeException();

        User user = userRepository.save(userMapper.toUser(userDto));
        user.getRoles().add(roleRepository.findByRoleEnum(RoleEnum.USER));

        userRepository.save(user);
        return userMapper.toUserDto(user);
    }

    @Override
    public Set<Role> findRoleUserByUserId(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("There is no such user")).getRoles();
    }

    @Override
    public UserDto updateUserRole(UserRequestRoleDto userRequestRoleDto) {
        User user = userRepository.findById(userRequestRoleDto.getUserId()).orElseThrow(() -> new UserNotFoundException("There is no such user"));
        Set<Role> userRole = user.getRoles();

        if(userRequestRoleDto.getIsAdmin() != null){
            Role roleAdmin = roleRepository.findByRoleEnum(RoleEnum.ADMIN);
            if(userRequestRoleDto.getIsAdmin()){
                userRole.add(roleAdmin);
            }else {
                userRole.remove(roleAdmin);
            }
        }
        if(userRequestRoleDto.getIsSeller() != null){
            Role roleSeller = roleRepository.findByRoleEnum(RoleEnum.SELLER);
            if(userRequestRoleDto.getIsSeller()){
                userRole.add(roleSeller);
            }else {
                userRole.remove(roleSeller);
            }
        }

        user.setRoles(userRole);

        return userMapper.toUserDto(userRepository.save(user));
    }
}
