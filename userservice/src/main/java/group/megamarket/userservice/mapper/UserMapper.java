package group.megamarket.userservice.mapper;

import group.megamarket.userservice.model.dto.UserDto;
import group.megamarket.userservice.model.dto.UserRequestRoleDto;
import group.megamarket.userservice.model.dto.UserRoleDto;
import group.megamarket.userservice.model.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserRoleDto> toUserRoleDto(List<User> users);

    User toUser(UserDto userDto);

    UserDto toUserDto(User user);

    UserRequestRoleDto toUserRequestRoleDto(User user);

}
