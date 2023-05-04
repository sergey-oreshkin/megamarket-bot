package group.megamarket.gateway.handler.impl;

import feign.FeignException;
import group.megamarket.gateway.dto.user.UserRoleDto;
import group.megamarket.gateway.feign.UserServiceClient;
import group.megamarket.gateway.handler.Handler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

/**
 * Класс для обработки /all запроса
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AllHandler implements Handler {

    private final UserServiceClient client;

    @Override
    public String handle(Update update) {
        try {
            log.info("Start work method /all request");
            List<UserRoleDto> users = client.getUsers();
            log.info("All users={}", users);
            log.info("Send correctly /all method response");
            return "Список всех пользователей \n" + users;
        } catch (FeignException e) {
            log.error("Error work /all request: {}", e.getMessage(), e);
            return "Произошла ошибка, при попытке получить список всех пользователей";
        }
    }
}
