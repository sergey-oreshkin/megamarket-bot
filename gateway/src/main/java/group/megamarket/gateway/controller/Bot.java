package group.megamarket.gateway.controller;

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
import group.megamarket.gateway.config.BotConfig;

import java.util.Map;

import static group.megamarket.gateway.component.BotCommands.LIST_OF_COMMANDS;

@Component
@Slf4j
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
            long chatId = update.getMessage().getChatId();
            String userName = update.getMessage().getFrom().getFirstName();
            String message = handlers.get(messageText);
            if (message == null) {
                sendResponse(chatId, "Uncorrected");
                return;
            }
            Handler handler = (Handler) context.getBean(message);
            sendResponse(chatId, handler.handle(userName));
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
