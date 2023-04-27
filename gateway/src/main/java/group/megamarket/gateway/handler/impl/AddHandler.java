package group.megamarket.gateway.handler.impl;

import group.megamarket.gateway.handler.Handler;
import group.megamarket.gateway.soap.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class AddHandler implements Handler {

    private final StorageService storageService;

    @Override
    public String handle(Update update) {
        Long sellerId = update.getMessage().getFrom().getId();
        String message = update.getMessage().getText();
        String[] arrParams = message.split(" ");
        storageService.addNewProduct(sellerId, arrParams[1], Integer.valueOf(arrParams[2]));
        return arrParams[1] + " в количестве " + arrParams[2] + "добавлен";
    }
}
