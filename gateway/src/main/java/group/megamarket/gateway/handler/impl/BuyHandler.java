package group.megamarket.gateway.handler.impl;

import feign.FeignException;
import group.megamarket.gateway.dto.market.OrderRequestDto;
import group.megamarket.gateway.feign.MarketServiceClient;
import group.megamarket.gateway.handler.Handler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Класс для обработки /buy запроса в формате /buy {id}
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class BuyHandler implements Handler {

    private final MarketServiceClient client;

    @Override
    public String handle(Update update) {
        try {
            log.info("Start work /buy method");
            String message = update.getMessage().getText();
            String[] idString = message.split(" ");
            Long userId = update.getMessage().getFrom().getId();
            OrderRequestDto response = OrderRequestDto
                    .builder()
                    .userId(userId)
                    .productId(Long.valueOf(idString[1]))
                    .quantity(Integer.parseInt(idString[2]))
                    .build();
            log.info("OrderResponseDto={}", response);
            client.addProduct(response);
            log.info("Send correctly response /buy method");
            return "Ваш продукт успешно добавлен в корзину";
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            log.error("Validation error /buy method: {}", e.getMessage(), e);
            return "Ошибка валидации, введите в верном формате\n" +
                    "Например,  /buy 1234";
        } catch (FeignException e) {
            log.error("Feign error /buy method: {}", e.getMessage(), e);
            return "Произошла ошибка, при попытке добавить продукт в корзину";
        }
    }
}
