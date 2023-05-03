package group.megamarket.gateway.handler.impl;

import group.megamarket.gateway.handler.Handler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Класс для обработки /help запроса
 */
@Slf4j
@Component
public class HelpHandler implements Handler {

    private static String HELP_TEXT = "Команды общие\n" +
            "/list - список всех товаров\n" +
            "/buy (id) - добавить товар с данным идентификатором в корзину\n" +
            "/cart - посмотреть свою корзину\n" +
            "/pay - оплатить корзину\n" +
            "/clear - очистить корзину\n" +
            "/requestadmin - запрос на получение роли админ\n" +
            "/requestseller - запрос на получение роли продавец\n" +
            "Команды продавца\n" +
            "/add (name count) - добавить себе на склад товар name в количестве count\n" +
            "/remove (name count) - убрать у себя со склада товар name в количестве count\n" +
            "Команды админа\n" +
            "/all - список всех пользователей с правами админа или продавца\n" +
            "/requests - список всех заявок на роль админа или продавца\n" +
            "/set (id role) - установить пользователю с id роль role. Если роль не указана - удалить все роли у данного пользователя.\n" +
            "/inventory (username) - список товаров продавца с именем username";

    @Override
    public String handle(Update update) {
        log.info("Send correctly /help method response");
        return HELP_TEXT;
    }
}
