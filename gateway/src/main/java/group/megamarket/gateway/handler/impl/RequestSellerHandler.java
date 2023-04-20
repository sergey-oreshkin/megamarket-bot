package group.megamarket.gateway.handler.impl;

import group.megamarket.gateway.handler.Handler;
import org.springframework.stereotype.Component;

@Component
public class RequestSellerHandler implements Handler {
    @Override
    public String handle(String param) {
        return "request seller logic";
    }
}
