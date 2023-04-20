package group.megamarket.userservice.mapper;

import group.megamarket.userservice.model.dto.RoleDto;
import group.megamarket.userservice.model.entity.Role;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper
public interface RoleMapper {
    Set<RoleDto> toSetDto(Set<Role> role);
    Set<Role> toSetRole(Set<RoleDto> roleDto);

    RoleDto toRoleDto(Role role);

}
