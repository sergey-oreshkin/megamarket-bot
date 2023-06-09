package group.megamarket.gateway.controller;

import group.megamarket.gateway.config.BotConfig;
import group.megamarket.gateway.handler.Handler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Map;

import static group.megamarket.gateway.component.BotCommands.LIST_OF_COMMANDS;

/**
 * Контроллер для работы endpoints
 */
@Slf4j
@Component
public class Bot extends TelegramLongPollingBot {

    private final BotConfig config;

    private final ApplicationContext context;

    public Bot(BotConfig config, ApplicationContext context) {
        this.context = context;
        this.config = config;
        try {
            this.execute(new SetMyCommands(LIST_OF_COMMANDS, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    @Value("#{${handlers}}")
    private Map<String, String> handlers;

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            String[] splitMessage = messageText.split(" ");
            long chatId = update.getMessage().getChatId();
            String message = handlers.get(splitMessage[0]);
            if (message == null) {
                sendResponse(chatId, "Некорректная команда, введите /help для ознакомления со списком команд");
                return;
            }
            Handler handler = (Handler) context.getBean(message);
            sendResponse(chatId, handler.handle(update));
        }
    }

    private void sendResponse(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        try {
            execute(message);
            log.info("Reply sent");
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }
}
