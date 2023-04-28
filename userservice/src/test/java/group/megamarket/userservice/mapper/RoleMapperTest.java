package group.megamarket.userservice.mapper;

import group.megamarket.userservice.model.dto.RoleDto;
import group.megamarket.userservice.model.entity.Role;
import group.megamarket.userservice.model.entity.RoleEnum;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RoleMapperTest {

    @Test
    void testToSetDto() {
        Role role1 = new Role();
        role1.setId(1L);
        role1.setRoleEnum(RoleEnum.ADMIN);

        Role role2 = new Role();
        role2.setId(2L);
        role2.setRoleEnum(RoleEnum.SELLER);

        Set<Role> roles = new HashSet<>(Set.of(role1, role2));
        Set<RoleDto> setDtos = RoleMapper.INSTANCE.toSetRoleDto(roles);

        assertNotNull(setDtos);
        assertEquals(roles.size(), setDtos.size());

        RoleDto dto1 = RoleMapper.INSTANCE.toRoleDto(role1);
        assertEquals(dto1.getId(), setDtos.stream().filter(dto -> dto.getRoleEnum() == RoleEnum.ADMIN).findFirst().get().getId());
        assertEquals(dto1.getRoleEnum(), setDtos.stream().filter(dto -> dto.getRoleEnum() == RoleEnum.ADMIN).findFirst().get().getRoleEnum());

        RoleDto dto2 = RoleMapper.INSTANCE.toRoleDto(role2);
        assertEquals(dto2.getId(), setDtos.stream().filter(dto -> dto.getRoleEnum() == RoleEnum.SELLER).findFirst().get().getId());
        assertEquals(dto2.getRoleEnum(), setDtos.stream().filter(dto -> dto.getRoleEnum() == RoleEnum.SELLER).findFirst().get().getRoleEnum());
    }

}
