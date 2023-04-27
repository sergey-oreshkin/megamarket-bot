package group.megamarket.gateway.handler.impl;

import group.megamarket.gateway.feign.UserServiceClient;
import group.megamarket.gateway.handler.Handler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class RequestHandler implements Handler {

    private final UserServiceClient client;

    @Override
    public String handle(Update update) {
        return "Список всех запросов \n" + client.getAllRequests().toString();
    }
}

