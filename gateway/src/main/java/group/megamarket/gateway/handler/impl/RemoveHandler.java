package group.megamarket.gateway.handler.impl;

import com.sun.xml.ws.fault.ServerSOAPFaultException;
import group.megamarket.gateway.handler.Handler;
import group.megamarket.gateway.soap.ProductDto;
import group.megamarket.gateway.soap.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Класс для обработки /remove запроса в формате /remove {name} {count}
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RemoveHandler implements Handler {

    private final StorageService storageService;

    @Override
    public String handle(Update update) {
        try {
            log.info("Start work /remove method");
            Long sellerId = update.getMessage().getFrom().getId();
            String message = update.getMessage().getText();
            String[] arrParams = message.split(" ");
            log.info("SellerId={}, productName={}, productCount={}", sellerId, arrParams[1], arrParams[2]);
            ProductDto productDto =
                    storageService.changeProductCountBySeller(sellerId, arrParams[1], Integer.valueOf(arrParams[2]));
            log.info("ProductDto={}", productDto);
            log.info("Send correctly /remove method response");
            return arrParams[1] + " в количестве " + arrParams[2] + "убран со склада";
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            log.error("Validation error /remove method");
            return "Ошибка валидации, введите в верном формате\n" +
                    "Например,  /remove carrot 2";
        } catch (ServerSOAPFaultException e) {
            log.error("Soap server error /remove method");
            return "Произошла ошибка, при попытке убрать товар со склада";
        }
    }
}
