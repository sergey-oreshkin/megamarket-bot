package group.megamarket.storageservice.service.impl;

import group.megamarket.storageservice.dto.RoleDto;
import group.megamarket.storageservice.exception.UserNotFoundException;
import group.megamarket.storageservice.model.Role;
import group.megamarket.storageservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Сервис для работы с юзерами
 */

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class UserServiceImpl implements UserService {

    public static final String GET_ROLES_URI_TEMPLATE = "/users/%d";
    public static final String USER_NOT_FOUND_MESSAGE_TEMPLATE =
            "User with id=%d not found or not a seller.";

    @Qualifier("userRestTemplate")
    private final RestTemplate restTemplate;

    /**
     * Проверяет есть ли у юзера данная роль, если нет, кидает эксепшен
     *
     * @param userId - id юзера
     * @param role   - роль
     * @throws UserNotFoundException при любых непонятках
     */
    @Override
    public void checkUserHasRoleOrThrow(Long userId, Role role) {
        log.info("Check userId={} has role={}", userId, role);
        try {
            RoleDto[] roles = restTemplate.getForObject(String.format(GET_ROLES_URI_TEMPLATE, userId), RoleDto[].class);
            if (Arrays.stream(roles).map(RoleDto::getRole).noneMatch(r -> r.equals(role))) {
                log.warn("Check userId={} has role={} failed!", userId, role);
                throw new RuntimeException();
            }
        } catch (Exception e) {
            log.error(e.getStackTrace().toString());
            throw new UserNotFoundException(String.format(USER_NOT_FOUND_MESSAGE_TEMPLATE, userId));
        }
    }
}
