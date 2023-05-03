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
 * Класс для обработки /list запроса
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ListHandler implements Handler {

    private final StorageService storageService;

    @Override
    public String handle(Update update) {
        try {
            log.info("Start work /list method");
            List<ProductDto> all = storageService.getAll();
            log.info("List all ProductDto={}", all);
            return "Список всех товаров магазина \n"
                    + all;
        } catch (ServerSOAPFaultException e) {
            log.error("Soap server error /list method");
            return "Произошла ошибка, при попытке получить список всех товаров магазина";
        }
    }
}

