package group.megamarket.userservice.service.impl;

import group.megamarket.userservice.exception.UserNotFoundException;
import group.megamarket.userservice.mapper.RequestMapper;
import group.megamarket.userservice.model.dto.RequestRoleDto;
import group.megamarket.userservice.model.dto.RoleDto;
import group.megamarket.userservice.model.entity.Request;
import group.megamarket.userservice.model.entity.Role;
import group.megamarket.userservice.model.entity.RoleEnum;
import group.megamarket.userservice.model.entity.User;
import group.megamarket.userservice.repository.RequestRepository;
import group.megamarket.userservice.repository.RoleRepository;
import group.megamarket.userservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RequestServiceImplTest {
    @Mock
    private RequestRepository requestRepository;

    @Mock
    private RequestMapper requestMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RequestServiceImpl requestService;

    @Test
    void testFindAll() {
        List<Request> requests = new ArrayList<>();
        requests.add(new Request());
        when(requestRepository.findAll()).thenReturn(requests);

        List<Request> result = requestService.findAll();

        assertEquals(requests, result);
        verify(requestRepository).findAll();
    }

    @Test
    void testSaveRequestRole_whenUserExistsAndHasRole() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setId(1L);
        role.setRoleEnum(RoleEnum.ADMIN);
        roles.add(role);
        user.setRoles(roles);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        RoleDto roleDto = new RoleDto(1L, RoleEnum.ADMIN);
        RequestRoleDto requestRole = new RequestRoleDto(userId, roleDto);
        RequestRoleDto expected = requestRole;
        Request request = new Request();
        request.setUser(user);
        request.setRole(role);
        when(requestMapper.toRequest(requestRole)).thenReturn(request);
        request.setId(1L);
        when(requestRepository.save(request)).thenReturn(request);
        when(requestMapper.toRequestRoleDto(request)).thenReturn(expected);

        RequestRoleDto result = requestService.saveRequestRole(requestRole);

        assertNotNull(result);
        assertEquals(expected, result);
        verify(userRepository).findById(userId);
        verify(requestRepository).save(request);
    }

    @Test
    void testSaveRequestRole_whenUserExistsAndDoesNotHaveRole() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        RoleDto roleDto = new RoleDto(1L, RoleEnum.ADMIN);
        RequestRoleDto requestRole = new RequestRoleDto(userId, roleDto);
        RequestRoleDto expected = requestRole;
        Request request = new Request();
        request.setUser(user);
        when(requestMapper.toRequest(requestRole)).thenReturn(request);
        request.setId(1L);
        when(requestRepository.save(request)).thenReturn(request);
        when(requestMapper.toRequestRoleDto(request)).thenReturn(expected);

        RequestRoleDto result = requestService.saveRequestRole(requestRole);

        assertEquals(expected, result);
        verify(userRepository).findById(userId);
        verify(roleRepository).findByRoleEnum(roleDto.getRoleEnum());
        verify(requestRepository).save(request);
    }

    @Test
    void testSaveRequestRole_whenUserDoesNotExist() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        RoleDto roleDto = new RoleDto(1L, RoleEnum.ADMIN);
        RequestRoleDto requestRole = new RequestRoleDto(userId, roleDto);

        assertThrows(UserNotFoundException.class, () -> requestService.saveRequestRole(requestRole));
        verify(userRepository).findById(userId);
        verifyNoInteractions(roleRepository);
        verifyNoInteractions(requestRepository);
    }
}
