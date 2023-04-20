package group.megamarket.userservice.service;

import group.megamarket.userservice.model.dto.UserDto;
import group.megamarket.userservice.model.dto.UserRequestRoleDto;
import group.megamarket.userservice.model.entity.Role;
import group.megamarket.userservice.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface UserService {
    List<User> findAllAdminAndSeller();

    UserDto save(UserDto userDto);

    Set<Role> findRoleUserByYserId(Long id);

    UserDto updateUserRole(UserRequestRoleDto userRequestRoleDto);
}
