package group.megamarket.gateway.handler.impl;

import com.sun.xml.ws.fault.ServerSOAPFaultException;
import group.megamarket.gateway.handler.Handler;
import group.megamarket.gateway.soap.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Класс для обработки /add запроса в формате /add {name} {count}
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AddHandler implements Handler {

    private final StorageService storageService;

    @Override
    public String handle(Update update) {
        try {
            log.info("Start work /add method");
            Long sellerId = update.getMessage().getFrom().getId();
            String message = update.getMessage().getText();
            String[] arrParams = message.split(" ");
            log.info("Seller id={}, productName={}, productCount={}", sellerId, arrParams[1], arrParams[2]);
            storageService.addNewProduct(sellerId, arrParams[1], Integer.valueOf(arrParams[2]));
            log.info("Send correctly /add method response");
            return arrParams[1] + " в количестве " + arrParams[2] + "добавлен";
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            log.error("Validation error /add method");
            return "Ошибка валидации, введите в верном формате\n" +
                    "Например,  /add carrot 2";
        } catch (ServerSOAPFaultException e) {
            log.error("Soap server error /add method");
            return "Произошла ошибка, при попытке добавить товар";
        }
    }
}
