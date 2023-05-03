package group.megamarket.gateway.handler.impl;

import feign.FeignException;
import group.megamarket.gateway.dto.user.RequestDto;
import group.megamarket.gateway.feign.UserServiceClient;
import group.megamarket.gateway.handler.Handler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

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
            List<RequestDto> allRequests = client.getAllRequests();
            log.info("List<RequestDto>={}", allRequests);
            log.info("Send correctly /request method response");
            return "Список всех запросов \n" + allRequests;
        } catch (FeignException e) {
            log.error("Error work /requests method: {}", e.getMessage(), e);
            return "Произошла ошибка, при попытке получить список всех запросов";
        }
    }
}

