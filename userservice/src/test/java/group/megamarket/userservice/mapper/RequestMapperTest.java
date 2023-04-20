package group.megamarket.userservice.mapper;

import group.megamarket.userservice.model.dto.RequestDto;
import group.megamarket.userservice.model.dto.RequestRoleDto;
import group.megamarket.userservice.model.entity.Request;
import group.megamarket.userservice.model.entity.Role;
import group.megamarket.userservice.model.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RequestMapperTest {
    @Test
    public void testRequestToRequestRoleDto() {
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
    public void testRequestToRequestDto() {
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

}
