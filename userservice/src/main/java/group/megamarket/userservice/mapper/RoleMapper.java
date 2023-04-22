package group.megamarket.userservice.mapper;

import group.megamarket.userservice.model.dto.RoleDto;
import group.megamarket.userservice.model.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    Set<RoleDto> toSetRoleDto(Set<Role> role);
    RoleDto toRoleDto(Role role);
}
