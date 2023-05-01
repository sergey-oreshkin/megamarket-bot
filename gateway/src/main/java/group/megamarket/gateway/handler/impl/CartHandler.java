package group.megamarket.gateway.handler.impl;

import feign.FeignException;
import group.megamarket.gateway.feign.MarketServiceClient;
import group.megamarket.gateway.handler.Handler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Класс для обработки /cart запроса
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CartHandler implements Handler {

    private final MarketServiceClient client;

    @Override
    public String handle(Update update) {
        try {
            log.info("Start work /cart method");
            Long userId = update.getMessage().getFrom().getId();
            String name = update.getMessage().getFrom().getFirstName();
            log.info("Send currency /cart response");
            return name + ", вот данные о вашей корзине\n"
                    + client.getOrder(userId).toString();
        } catch (FeignException e) {
            log.warn("Feign error /cart method");
            return "Произошла ошибка, при попытке получить данные о вашей корзине";
        }
    }
}
