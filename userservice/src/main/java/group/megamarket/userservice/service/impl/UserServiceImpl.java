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
import group.megamarket.userservice.soap.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Сервис для работы с юзерами. Поиск, сохранение  и т.п.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserMapper userMapper;

    private final StorageService storageService;

    /**
     * Метод возвращает список всех юзеров, у которых роли "ADMIN", "SELLER"
     */
    @Override
    public List<User> findAllAdminAndSeller() {
        List<User> allAdminAndSeller = userRepository.findAllAdminAndSeller(List.of(RoleEnum.ADMIN, RoleEnum.SELLER));
        log.info("received list users: {}", allAdminAndSeller);
        return allAdminAndSeller;
    }

    /**
     * Метод возвращает список всех юзеров, у которых роли "ADMIN", "SELLER"
     *
     * @param userDto - конкретный юзер
     * @return сохраненного userDto
     */
    @Override
    public UserDto save(UserDto userDto) {
        if (userDto == null) throw new RuntimeException();

        Optional<User> userOptional = userRepository.findById(userDto.getId());

        if (userOptional.isPresent()) {
            return userMapper.toUserDto(userOptional.get());
        }

        User user = userRepository.save(userMapper.toUser(userDto));
        if (user.getRoles() != null) {
            user.getRoles().add(roleRepository.findByRoleEnum(RoleEnum.USER));
        } else {
            Set<Role> roles = new HashSet<>();
            Role role = roleRepository.findByRoleEnum(RoleEnum.USER);
            roles.add(role);
            user.setRoles(roles);
        }

        User savedUser = userRepository.save(user);
        log.info("saved user: {}", savedUser);
        return userMapper.toUserDto(user);
    }

    /**
     * @param id - id юзера
     * @return список всех ролей конкретного юзера
     * @throws UserNotFoundException - если юзер не найден
     */
    @Override
    public Set<Role> findRoleUserByUserId(Long id) {
        log.info("incoming user id: {}", id);
        Set<Role> roles = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("There is no such user")).getRoles();
        log.info("received roles: {}", roles);
        return roles;
    }

    /**
     * @param userRequestRoleDto - роли, которые юзер запрашивает
     * @return userDto конкретного юзера
     * @throws UserNotFoundException - если юзер не найден
     */
    @Override
    public UserDto updateUserRole(UserRequestRoleDto userRequestRoleDto) {
        log.info("incoming userRequestRoleDto: {}", userRequestRoleDto);
        User user = userRepository.findById(userRequestRoleDto.getUserId()).orElseThrow(() -> new UserNotFoundException("There is no such user"));
        log.info("user from userRequestRoleDto: {}", user);
        Set<Role> userRole = user.getRoles();
        log.info("roles from user: {}", userRole);

        if (userRequestRoleDto.getIsAdmin() != null) {
            Role roleAdmin = roleRepository.findByRoleEnum(RoleEnum.ADMIN);
            if (userRequestRoleDto.getIsAdmin()) {
                userRole.add(roleAdmin);
            } else {
                userRole.remove(roleAdmin);
            }
        }
        if (userRequestRoleDto.getIsSeller() != null) {
            Role roleSeller = roleRepository.findByRoleEnum(RoleEnum.SELLER);
            if (userRequestRoleDto.getIsSeller()) {
                userRole.add(roleSeller);
            } else {
                storageService.deleteAllByUserId(user.getId());
                userRole.remove(roleSeller);
            }
        }

        user.setRoles(userRole);
        UserDto updatedUserDto = userMapper.toUserDto(userRepository.save(user));
        log.info("updated userDto: {}", updatedUserDto);
        return updatedUserDto;
    }
}
