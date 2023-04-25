package group.megamarket.gateway.handler.impl;

import group.megamarket.gateway.handler.Handler;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class AllHandler implements Handler {

    @Override
    public String handle(Update update) {
        return null;
    }
}
