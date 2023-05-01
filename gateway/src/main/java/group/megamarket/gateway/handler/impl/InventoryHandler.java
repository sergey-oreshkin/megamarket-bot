package group.megamarket.gateway.handler.impl;

import com.sun.xml.ws.fault.ServerSOAPFaultException;
import group.megamarket.gateway.handler.Handler;
import group.megamarket.gateway.soap.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Класс для обработки /inventory запроса
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class InventoryHandler implements Handler {

    private final StorageService storageService;

    @Override
    public String handle(Update update) {
        try {
            log.info("Start work /inventory method");
            Long sellerId = update.getMessage().getFrom().getId();
            String sellerName = update.getMessage().getFrom().getFirstName();
            return sellerName + ", вот ваш список товаров \n"
                    + storageService.getAllByUserId(sellerId);
        } catch (ServerSOAPFaultException e) {
            log.error("Soap server error /inventory method");
            return "Произошла ошибка, при попытке получить ваш список товаров";
        }
    }
}
