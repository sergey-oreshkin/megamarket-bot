package group.megamarket.gateway.handler;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface Handler {
    String handle(Update update);
}
