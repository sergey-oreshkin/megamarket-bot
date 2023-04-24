package group.megamarket.userservice.service.impl;

import group.megamarket.userservice.exception.RoleNotFoundException;
import group.megamarket.userservice.exception.UserNotFoundException;
import group.megamarket.userservice.model.dto.UserDto;
import group.megamarket.userservice.model.dto.UserRequestRoleDto;
import group.megamarket.userservice.model.entity.Role;
import group.megamarket.userservice.model.entity.RoleEnum;
import group.megamarket.userservice.model.entity.User;
import group.megamarket.userservice.repository.RoleRepository;
import group.megamarket.userservice.repository.UserRepository;
import group.megamarket.userservice.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testFindAllAdminAndSeller() {
        // given
        when(userRepository.findAllAdminAndSeller(List.of(RoleEnum.ADMIN, RoleEnum.SELLER)))
                .thenReturn(List.of(new User(), new User()));

        // when
        List<User> users = userService.findAllAdminAndSeller();

        // then
        assertNotNull(users);
        assertEquals(2, users.size());
        verify(userRepository).findAllAdminAndSeller(List.of(RoleEnum.ADMIN, RoleEnum.SELLER));
    }

    @Test
    public void testSaveUser() {
        // given
        UserDto userDto = new UserDto();
        User user = new User();
        Role role = new Role();
        role.setRoleEnum(RoleEnum.USER);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        when(userMapper.toUser(userDto)).thenReturn(user);
        when(roleRepository.findByRoleEnum(RoleEnum.USER)).thenReturn(role);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toUserDto(user)).thenReturn(userDto);

        // when
        UserDto savedUserDto = userService.save(userDto);

        // then
        assertNotNull(savedUserDto);
        assertEquals(1, user.getRoles().size());
        assertTrue(user.getRoles().contains(role));
        verify(userMapper).toUser(userDto);
        verify(roleRepository).findByRoleEnum(RoleEnum.USER);
        verify(userRepository, times(2)).save(user);
    }

    @Test
    public void testSaveUserNull() {
        // when, then
        assertThrows(RuntimeException.class, () -> userService.save(null));
    }

    @Test
    public void testFindRoleUserByUserId() {
        // given
        Long userId = 1L;
        User user = new User();
        Role role = new Role();
        role.setRoleEnum(RoleEnum.USER);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // when
        Set<Role> resultRoles = userService.findRoleUserByUserId(userId);

        // then
        assertNotNull(resultRoles);
        assertEquals(1, resultRoles.size());
        assertTrue(resultRoles.contains(role));
        verify(userRepository).findById(userId);
    }

    @Test
    public void testFindRoleUserByUserIdNotFound() {
        // given
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // when, then
        assertThrows(UserNotFoundException.class, () -> userService.findRoleUserByUserId(userId));
        verify(userRepository).findById(userId);
    }

    @Test
    public void testUpdateUserRole() {
        // Setup
        Long userId = 1L;
        Role adminRole = new Role();
        adminRole.setRoleEnum(RoleEnum.ADMIN);
        Role sellerRole = new Role();
        sellerRole.setRoleEnum(RoleEnum.SELLER);
        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);
        roles.add(sellerRole);
        User user = new User();
        user.setId(userId);
        user.setUsername("max");
        user.setRoles(roles);
        UserRequestRoleDto userRequestRoleDto = new UserRequestRoleDto(userId, true, true);
        UserDto userDto = new UserDto(userId, "max");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(roleRepository.findByRoleEnum(RoleEnum.ADMIN)).thenReturn(adminRole);
        when(roleRepository.findByRoleEnum(RoleEnum.SELLER)).thenReturn(sellerRole);
        when(userMapper.toUserDto(user)).thenReturn(userDto);
        when(userRepository.save(user)).thenReturn(user);

        // Execute
        UserDto updatedUserDto = userService.updateUserRole(userRequestRoleDto);

        // Verify
        assertEquals(userDto, updatedUserDto);
        assertTrue(user.getRoles().contains(adminRole));
        assertTrue(user.getRoles().contains(sellerRole));
    }

    @Test
    public void testUpdateUserRole_UserNotFound() {
        // Setup
        Long userId = 1L;
        UserRequestRoleDto userRequestRoleDto = new UserRequestRoleDto(userId, true, true);

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Execute & Verify
        assertThrows(UserNotFoundException.class, () -> {
            userService.updateUserRole(userRequestRoleDto);
        });
    }

    @Test
    public void testUpdateUserRole_AdminRoleNotFound() {
        // Setup
        Long userId = 1L;
        Role sellerRole = new Role();
        sellerRole.setRoleEnum(RoleEnum.SELLER);
        User user = new User();
        user.setId(userId);
        user.setUsername("max");
        Set<Role> roles = new HashSet<>();
        roles.add(sellerRole);
        user.setRoles(roles);
        UserRequestRoleDto userRequestRoleDto = new UserRequestRoleDto(userId, true, true);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(roleRepository.findByRoleEnum(RoleEnum.ADMIN)).thenReturn(null);

        // Execute & Verify
        assertThrows(RoleNotFoundException.class, () -> {
            userService.updateUserRole(userRequestRoleDto);
        });
    }
}
