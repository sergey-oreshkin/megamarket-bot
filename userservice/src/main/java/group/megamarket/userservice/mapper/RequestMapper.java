package group.megamarket.userservice.mapper;

import group.megamarket.userservice.model.dto.RequestDto;
import group.megamarket.userservice.model.dto.RequestRoleDto;
import group.megamarket.userservice.model.dto.UserDto;
import group.megamarket.userservice.model.entity.Request;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RequestMapper {
    RequestMapper INSTANCE = Mappers.getMapper(RequestMapper.class);

    @Mapping(source = "user", target = "userDto")
    @Mapping(source = "role", target = "roleDto")
    RequestDto requestToRequestDto(Request request);

    List<RequestDto> toListDto(List<Request> request);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "role", target = "roleDto")
    RequestRoleDto toRequestRoleDto(Request request);
}
