package group.megamarket.storageservice.service.impl;

import group.megamarket.storageservice.exception.UserNotFoundException;
import group.megamarket.storageservice.model.Role;
import group.megamarket.storageservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class UserServiceImpl implements UserService {

    public static final String GET_ROLES_URI_TEMPLATE = "/users/%d";
    public static final String USER_NOT_FOUND_MESSAGE_TEMPLATE =
            "User with id=%d not found or not a seller.";

    @Qualifier("userRestTemplate")
    private final RestTemplate restTemplate;

    @Override
    public void checkUserHasRoleOrThrow(Long userId, Role role) {
        Role[] roles = restTemplate.getForObject(String.format(GET_ROLES_URI_TEMPLATE, userId), Role[].class);
        if (Arrays.stream(roles).noneMatch(r -> r.equals(role))) {
            throw new UserNotFoundException(String.format(USER_NOT_FOUND_MESSAGE_TEMPLATE, userId));
        }
    }
}
