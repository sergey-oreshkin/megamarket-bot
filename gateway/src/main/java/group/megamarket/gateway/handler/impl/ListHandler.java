package group.megamarket.gateway.handler.impl;

import com.sun.xml.ws.fault.ServerSOAPFaultException;
import group.megamarket.gateway.handler.Handler;
import group.megamarket.gateway.soap.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

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
            return "Список всех товаров магазина \n"
                    +storageService.getAll().toString();
        } catch (ServerSOAPFaultException e) {
            log.error("Soap server error /list method");
            return "Произошла ошибка, при попытке получить список всех товаров магазина";
        }
    }
}

