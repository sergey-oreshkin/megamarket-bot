package group.megamarket.gateway.handler.impl;

import feign.FeignException;
import group.megamarket.gateway.dto.user.UserDto;
import group.megamarket.gateway.feign.UserServiceClient;
import group.megamarket.gateway.handler.Handler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Класс для обработки /start запроса
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StartHandler implements Handler {

    private final UserServiceClient client;

    @Override
    public String handle(Update update) {
        try {
            log.info("Start working startHandler");
            Long userId = update.getMessage().getFrom().getId();
            String userName = update.getMessage().getFrom().getFirstName();
            UserDto user = registerUser(userId, userName);
            log.info("Welcome and add new user");
            return "Привет " + user.getUsername() + ", добро пожаловать в Mega Market," +
                    " вы добавлены в список наших пользователей";
        } catch (FeignException e) {
            log.warn("Error feign client and send inform message for user");
            return "Привет, добро пожаловать в Mega Market," +
                    " произошла ошибка при добавлении вас в список наших пользователей";
        }
    }

    private UserDto registerUser(Long id, String name) {
        UserDto userDto = UserDto
                .builder()
                .id(id)
                .username(name)
                .build();

        return client.saveUser(userDto);
    }
}
