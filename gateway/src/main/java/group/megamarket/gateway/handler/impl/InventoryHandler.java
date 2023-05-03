package group.megamarket.gateway.handler.impl;

import com.sun.xml.ws.fault.ServerSOAPFaultException;
import group.megamarket.gateway.handler.Handler;
import group.megamarket.gateway.soap.ProductDto;
import group.megamarket.gateway.soap.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

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
            List<ProductDto> allByUserId = storageService.getAllByUserId(sellerId);
            log.info("List<ProductDto>={}", allByUserId);
            return sellerName + ", вот ваш список товаров \n"
                    + allByUserId;
        } catch (ServerSOAPFaultException e) {
            log.error("Soap server error /inventory method");
            return "Произошла ошибка, при попытке получить ваш список товаров";
        }
    }
}
