package group.megamarket.userservice.controller;

import group.megamarket.userservice.mapper.RequestMapper;
import group.megamarket.userservice.model.dto.RequestDto;
import group.megamarket.userservice.model.dto.RequestRoleDto;
import group.megamarket.userservice.model.entity.Request;
import group.megamarket.userservice.service.RequestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RequestControllerTest {
    @Mock
    private RequestService requestService;

    @Mock
    private RequestMapper requestMapper;

    @InjectMocks
    private RequestController requestController;

    @Test
    public void testGetAllRequests() {
        // Setup
        Request request = new Request();
        request.setId(1L);
        List<Request> requests = new ArrayList<>();
        requests.add(request);

        RequestDto requestDto = new RequestDto();
        requestDto.setId(1L);
        List<RequestDto> requestDtos = new ArrayList<>();
        requestDtos.add(requestDto);

        when(requestService.findAll()).thenReturn(requests);
        when(requestMapper.toListDto(List.of(request))).thenReturn(requestDtos);

        // Test
        List<RequestDto> result = requestController.getAllRequests();

        // Verify
        verify(requestService, times(1)).findAll();
        verify(requestMapper, times(1)).toListDto(requests);
        assertSame(result, requestDtos);
    }

    @Test
    public void testSaveRequestRole() throws Exception {
        // Setup
        RequestRoleDto requestRoleDto = new RequestRoleDto();
        when(requestService.saveRequestRole(requestRoleDto)).thenReturn(requestRoleDto);

        // Test
        RequestRoleDto result = requestController.saveRequestRole(requestRoleDto);

        // Verify
        verify(requestService, times(1)).saveRequestRole(requestRoleDto);
        assertSame(result, requestRoleDto);
    }
}
