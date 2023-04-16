package ru.megamarket.gateway.component;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;

public interface BotCommands {
    List<BotCommand> LIST_OF_COMMANDS = List.of(
            new BotCommand("/start", "start bot"),
            new BotCommand("/help", "bot info")
    );

    String HELP_TEXT = "Команды общие\n" +
            "/list - список всех товаров\n" +
            "/buy (id) - добавить товар с данным идентификатором в корзину\n" +
            "/cart - посмотреть свою корзину\n" +
            "/pay - оплатить корзину\n" +
            "/clear - очистить корзину\n" +
            "/requestadmin - запрос на получение роли админ\n" +
            "/requestseller - запрос на получение роли продавец\n" +
            "Команды продавца\n" +
            "/inventory - список товаров текущего продавца\n" +
            "/add (name count) - добавить себе на склад товар name в количестве count\n" +
            "/remove (name count) - убрать у себя со склада товар name в количестве count\n" +
            "Команды админа\n" +
            "/all - список всех пользователей с правами админа или продавца\n" +
            "/requests - список всех заявок на роль админа или продавца\n" +
            "/set (username role) - установить пользователю с именем username роль role. Если роль не указана - удалить все роли у данного пользователя.\n" +
            "/inventory (username) - список товаров продавца с именем username";
}
