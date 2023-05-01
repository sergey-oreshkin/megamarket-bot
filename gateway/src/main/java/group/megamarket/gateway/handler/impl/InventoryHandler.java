package group.megamarket.gateway.handler.impl;

import group.megamarket.gateway.handler.Handler;
import group.megamarket.gateway.soap.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class InventoryHandler implements Handler {

    private final StorageService storageService;

    @Override
    public String handle(Update update) {
        Long sellerId = update.getMessage().getFrom().getId();
        String sellerName = update.getMessage().getFrom().getFirstName();
        return sellerName + ", вот ваш список товаров \n"
                + storageService.getAllByUserId(sellerId);
    }
}