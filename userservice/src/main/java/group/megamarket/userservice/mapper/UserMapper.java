package group.megamarket.userservice.mapper;

import group.megamarket.userservice.model.dto.UserDto;
import group.megamarket.userservice.model.dto.UserRequestRoleDto;
import group.megamarket.userservice.model.dto.UserRoleDto;
import group.megamarket.userservice.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    List<UserRoleDto> toListUserRoleDto(List<User> users);

    UserRoleDto toUserRoleDto(User user);

    User toUser(UserDto userDto);

    UserDto toUserDto(User user);

    @Mapping(source = "id", target = "userId")
    UserRequestRoleDto toUserRequestRoleDto(User user);

}
