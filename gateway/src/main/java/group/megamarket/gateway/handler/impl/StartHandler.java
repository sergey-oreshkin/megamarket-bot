package group.megamarket.gateway.handler.impl;

import group.megamarket.gateway.dto.UserDto;
import group.megamarket.gateway.feign.UserServiceClient;
import group.megamarket.gateway.handler.Handler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class StartHandler implements Handler {

    private final UserServiceClient userServiceClient;

    @Override
    public String handle(Update update) {
        Long userId = update.getMessage().getFrom().getId();
        String userName = update.getMessage().getFrom().getFirstName();
        UserDto user = registerUser(userId, userName);
        return "Привет " + user.getUsername() + ", добро пожаловать в Mega Market";
    }

    private UserDto registerUser(Long id, String name) {
        UserDto userDto = UserDto
                .builder()
                .id(id)
                .username(name)
                .build();

        return userServiceClient.saveUser(userDto);
    }
}
