package group.megamarket.gateway.handler.impl;

import feign.FeignException;
import group.megamarket.gateway.feign.MarketServiceClient;
import group.megamarket.gateway.handler.Handler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Класс для обработки /pay запроса
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PayHandler implements Handler {

    private final MarketServiceClient client;

    @Override
    public String handle(Update update) {
        try {
            log.info("Start work /pay method");
            Long userId = update.getMessage().getFrom().getId();
            String userName = update.getMessage().getFrom().getFirstName();
            log.info("Send correctly /pay method response");
            return userName + ", ваша корзина оплачена\n"
                    + client.pay(userId);
        } catch (FeignException e) {
            log.error("Feign error /pay method");
            return "Произошла ошибка, при попытке оплатить коризну";
        }
    }
}
