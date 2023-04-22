package group.megamarket.userservice.mapper;

import group.megamarket.userservice.model.dto.RoleDto;
import group.megamarket.userservice.model.dto.UserDto;
import group.megamarket.userservice.model.dto.UserRequestRoleDto;
import group.megamarket.userservice.model.dto.UserRoleDto;
import group.megamarket.userservice.model.entity.Role;
import group.megamarket.userservice.model.entity.RoleEnum;
import group.megamarket.userservice.model.entity.User;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserMapperTest {

    @Test
    public void testToUserDto(){
        User user = new User();
        user.setId(1L);
        user.setUsername("john");

        UserDto dto = UserMapper.INSTANCE.toUserDto(user);

        assertNotNull(dto);
        assertEquals(user.getId(), dto.getId());
        assertEquals(user.getUsername(), dto.getUsername());
    }

    @Test
    public void testToUser(){
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setUsername("max");

        User user = UserMapper.INSTANCE.toUser(userDto);

        assertNotNull(user);
        assertEquals(userDto.getId(), user.getId());
        assertEquals(userDto.getUsername(), user.getUsername());
    }

    @Test
    public void testToUserRequestRoleDto() {
        User user = new User();
        user.setId(1L);

        UserRequestRoleDto dto = UserMapper.INSTANCE.toUserRequestRoleDto(user);

        assertNotNull(dto);
        assertEquals(user.getId(), dto.getUserId());
    }

    @Test
    public void testToUserRoleDto() {
        User user = new User();
        user.setId(1L);
        user.setUsername("john");
        Role role = new Role();
        role.setRoleEnum(RoleEnum.ADMIN);
        Set<Role> roles = Set.of(role);
        user.setRoles(roles);

        UserRoleDto dto = UserMapper.INSTANCE.toUserRoleDto(user);

        assertEquals(user.getId(), dto.getId());
        assertEquals(roles.size(), dto.getRoles().size());
    }

    @Test
    public void testToListUserRoleDto() {
        User user = new User();
        user.setId(1L);
        user.setUsername("john");
        Role role = new Role();
        role.setRoleEnum(RoleEnum.ADMIN);
        Set<Role> roles = Set.of(role);
        user.setRoles(roles);

        List<User> users = List.of(user);
        List<UserRoleDto> userRoleDtos = UserMapper.INSTANCE.toListUserRoleDto(users);

        assertNotNull(userRoleDtos);
        assertEquals(users.size(), userRoleDtos.size());
        assertEquals(users.get(0).getId(), userRoleDtos.get(0).getId());
        assertEquals(users.get(0).getRoles().size(), userRoleDtos.get(0).getRoles().size());

    }


}
