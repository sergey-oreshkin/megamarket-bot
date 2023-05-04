package group.megamarket.userservice.controller;

import group.megamarket.userservice.mapper.RoleMapper;
import group.megamarket.userservice.mapper.UserMapper;
import group.megamarket.userservice.model.dto.RoleDto;
import group.megamarket.userservice.model.dto.UserDto;
import group.megamarket.userservice.model.dto.UserRequestRoleDto;
import group.megamarket.userservice.model.dto.UserRoleDto;
import group.megamarket.userservice.model.entity.Role;
import group.megamarket.userservice.model.entity.RoleEnum;
import group.megamarket.userservice.model.entity.User;
import group.megamarket.userservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Mock
    UserService userService;

    @Mock
    UserMapper userMapper;

    @Mock
    RoleMapper roleMapper;

    @InjectMocks
    UserController userController;

    @Test
    void shouldReturnUserRoleDtoList() {
        // given
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("user1");
        Role role = new Role();
        role.setId(1L);
        role.setRoleEnum(RoleEnum.ADMIN);
        user1.setRoles(Set.of(role));
        userList.add(user1);
        when(userService.findAllAdminAndSeller()).thenReturn(userList);

        UserRoleDto userRoleDto1 = new UserRoleDto();
        userRoleDto1.setId(1L);
        RoleDto roleDto = new RoleDto();
        roleDto.setId(1L);
        roleDto.setRoleEnum(RoleEnum.ADMIN);
        userRoleDto1.setRoles(Set.of(roleDto));
        List<UserRoleDto> userRoleDtoList = List.of(userRoleDto1);
        when(userMapper.toListUserRoleDto(userList)).thenReturn(userRoleDtoList);

        // when
        List<UserRoleDto> result = userController.getUsers();

        // then
        assertNotNull(result);
        verify(userService).findAllAdminAndSeller();
        assertEquals(result, userRoleDtoList);
        verify(userService, times(1)).findAllAdminAndSeller();
    }

    @Test
    public void testGetRoleUserById() throws Exception {
        // Setup
        Long id = 1L;
        Role role = new Role();
        role.setId(1L);
        role.setRoleEnum(RoleEnum.ADMIN);
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        RoleDto roleDto = new RoleDto();
        roleDto.setId(1L);
        roleDto.setRoleEnum(RoleEnum.ADMIN);
        Set<RoleDto> roleDtos = new HashSet<>();
        roleDtos.add(roleDto);

        when(userService.findRoleUserByUserId(id)).thenReturn(roles);
        when(roleMapper.toSetRoleDto(roles)).thenReturn(roleDtos);

        // Test
        Set<RoleDto> result = userController.getRoleUserById(id);

        // Verify
        verify(userService, times(1)).findRoleUserByUserId(id);
        verify(roleMapper, times(1)).toSetRoleDto(roles);
        assertSame(result, roleDtos);
    }

    @Test
    public void testSaveUser() throws Exception {
        // Setup
        UserDto userDto = new UserDto();
        when(userService.save(userDto)).thenReturn(userDto);

        // Test
        UserDto result = userController.saveUser(userDto);

        // Verify
        verify(userService, times(1)).save(userDto);
        assertSame(result, userDto);
    }

    @Test
    public void testUpdateUserRole() throws Exception {
        // Setup
        UserRequestRoleDto userRequestRoleDto = new UserRequestRoleDto();
        UserDto userDto = new UserDto();
        when(userService.updateUserRole(userRequestRoleDto)).thenReturn(userDto);

        // Test
        UserDto result = userController.updateUserRole(userRequestRoleDto);

        // Verify
        verify(userService, times(1)).updateUserRole(userRequestRoleDto);
        assertSame(result, userDto);
    }
}
