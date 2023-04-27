package group.megamarket.storageservice.service.impl;

import group.megamarket.storageservice.dto.RoleDto;
import group.megamarket.storageservice.exception.UserNotFoundException;
import group.megamarket.storageservice.model.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    public static long VALID_USER_ID = 1L;
    public static RoleDto ADMIN_ROLE_DTO = new RoleDto(1L, Role.ADMIN);

    @Mock
    RestTemplate restTemplate;
    @InjectMocks
    UserServiceImpl userService;

    @Test
    void checkUserHasRoleOrThrow_whenRoleIsPresent_shouldNotThrow() {
        when(restTemplate.getForObject(format(UserServiceImpl.GET_ROLES_URI_TEMPLATE, VALID_USER_ID), RoleDto[].class))
                .thenReturn(new RoleDto[]{ADMIN_ROLE_DTO});

        userService.checkUserHasRoleOrThrow(1L, Role.ADMIN);
    }

    @Test
    void checkUserHasRoleOrThrow_whenRestTemplateTrow_shouldThrowUserNotFoundException() {
        when(restTemplate.getForObject(anyString(), any())).thenThrow(RuntimeException.class);

        assertThrows(UserNotFoundException.class, () -> userService.checkUserHasRoleOrThrow(anyLong(), any()));
    }

    @Test
    void checkUserHasRoleOrThrow_whenRoleIsMissing_shouldThrowUserNotFoundException() {
        when(restTemplate.getForObject(format(UserServiceImpl.GET_ROLES_URI_TEMPLATE, VALID_USER_ID), RoleDto[].class))
                .thenReturn(new RoleDto[]{ADMIN_ROLE_DTO});

        assertThrows(UserNotFoundException.class, () -> userService.checkUserHasRoleOrThrow(1L, Role.SELLER));
    }
}