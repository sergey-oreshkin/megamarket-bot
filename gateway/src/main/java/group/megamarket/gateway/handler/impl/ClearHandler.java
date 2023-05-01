package group.megamarket.gateway.handler.impl;

import feign.FeignException;
import group.megamarket.gateway.feign.MarketServiceClient;
import group.megamarket.gateway.handler.Handler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Класс для обработки /clear запроса
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ClearHandler implements Handler {

    private final MarketServiceClient client;

    @Override
    public String handle(Update update) {
        try {
            log.info("Start work /clear method");
            Long userId = update.getMessage().getFrom().getId();
            String userName = update.getMessage().getFrom().getFirstName();
            client.clear(userId);
            log.info("Send correctly /clear method response");
            return userName + ", ваша корзина успешно очищена";
        } catch (FeignException e) {
            log.error("Feign error /clear work");
            return "Произошла ошибка, при попытке очистить корзину";
        }
    }
}
