package group.megamarket.gateway.handler.impl;

import group.megamarket.gateway.dto.market.OrderRequest;
import group.megamarket.gateway.feign.MarketServiceClient;
import group.megamarket.gateway.handler.Handler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class BuyHandler implements Handler {

    private final MarketServiceClient client;

    @Override
    public String handle(Update update) {
        String message = update.getMessage().getText();
        String[] idString = message.split(" ");
        Long userId = update.getMessage().getFrom().getId();
        OrderRequest response = OrderRequest
                .builder()
                .userId(userId)
                .productId(Long.valueOf(idString[1]))
                .quantity(1)
                .build();
        client.addProduct(response);
        return "Ваш продукт успешно добавлен в корзину";
    }
}
