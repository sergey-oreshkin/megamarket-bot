package group.megamarket.gateway.handler.impl;

import group.megamarket.gateway.feign.MarketServiceClient;
import group.megamarket.gateway.handler.Handler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class CartHandler implements Handler {

    private final MarketServiceClient client;

    @Override
    public String handle(Update update) {
        Long userId = update.getMessage().getFrom().getId();
        String name = update.getMessage().getFrom().getFirstName();

        return name + ", вот данные о вашей корзине\n"
                + client.getOrder(userId).toString();
    }
}
