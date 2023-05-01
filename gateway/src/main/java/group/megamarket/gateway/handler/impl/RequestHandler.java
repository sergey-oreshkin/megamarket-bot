package group.megamarket.gateway.handler.impl;

import feign.FeignException;
import group.megamarket.gateway.feign.UserServiceClient;
import group.megamarket.gateway.handler.Handler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Класс для обработки /requests запроса
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RequestHandler implements Handler {

    private final UserServiceClient client;

    @Override
    public String handle(Update update) {
        try {
            log.info("Start work /requests method");
            return "Список всех запросов \n" + client.getAllRequests().toString();
        } catch (FeignException e) {
            log.error("Error work /requests method");
            return "Произошла ошибка, при попытке получить список всех запросов";
        }
    }
}

