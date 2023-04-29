package group.megamarket.userservice.mapper;

import group.megamarket.userservice.model.dto.RequestDto;
import group.megamarket.userservice.model.dto.RequestRoleDto;
import group.megamarket.userservice.model.dto.RoleDto;
import group.megamarket.userservice.model.entity.Request;
import group.megamarket.userservice.model.entity.Role;
import group.megamarket.userservice.model.entity.RoleEnum;
import group.megamarket.userservice.model.entity.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RequestMapperTest {
    @Test
    public void testToRequestRoleDto() {
        // Given
        Request request = new Request();
        request.setId(1L);

        User user = new User();
        user.setId(2L);
        request.setUser(user);

        Role role = new Role();
        role.setId(3L);
        request.setRole(role);

        // When
        RequestRoleDto requestRoleDto = RequestMapper.INSTANCE.toRequestRoleDto(request);

        // Then
        assertNotNull(requestRoleDto);
        assertEquals(requestRoleDto.getUserId(), user.getId());
        assertNotNull(requestRoleDto.getRoleDto());
        assertEquals(requestRoleDto.getRoleDto().getId(), role.getId());
    }

    @Test
    public void testToRequestDto() {
        // Given
        List<Request> requests = new ArrayList<>();

        Request request = new Request();
        request.setId(1L);

        User user = new User();
        user.setId(2L);
        request.setUser(user);

        Role role = new Role();
        role.setId(3L);
        request.setRole(role);

        requests.add(request);

        // When
        List<RequestDto> requestDto = RequestMapper.INSTANCE.toListDto(requests);

        // Then
        assertNotNull(requestDto);
        assertEquals(requests.size(), 1);

        RequestDto requestDto1 = requestDto.get(0);
        assertEquals(requestDto1.getId(), request.getId());
        assertNotNull(requestDto1.getUserDto());
        assertEquals(requestDto1.getUserDto().getId(), request.getUser().getId());
        assertNotNull(requestDto1.getRoleDto());
        assertEquals(requestDto1.getRoleDto().getId(), request.getRole().getId());
    }

    @Test
    public void testToRequest(){
        // Given
        RequestRoleDto requestRoleDto = new RequestRoleDto();
        requestRoleDto.setUserId(1L);

        RoleDto roleDto = new RoleDto();
        roleDto.setId(3L);
        requestRoleDto.setRoleDto(new RoleDto(1L, RoleEnum.ADMIN));

        // When
        Request request = RequestMapper.INSTANCE.toRequest(requestRoleDto);

        // Then
        assertNotNull(request);
        assertEquals(requestRoleDto.getUserId(), request.getUser().getId());
        assertEquals(requestRoleDto.getRoleDto().getRoleEnum(), request.getRole().getRoleEnum());
    }

}
