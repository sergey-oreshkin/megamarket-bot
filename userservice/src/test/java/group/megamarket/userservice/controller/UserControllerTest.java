package group.megamarket.userservice.controller;

import group.megamarket.userservice.mapper.RoleMapper;
import group.megamarket.userservice.mapper.UserMapper;
import group.megamarket.userservice.model.dto.RoleDto;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    }
}
