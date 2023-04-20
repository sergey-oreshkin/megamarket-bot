package group.megamarket.gateway.handler.impl;

import group.megamarket.gateway.handler.Handler;
import org.springframework.stereotype.Component;

@Component
public class SetHandler implements Handler {
    @Override
    public String handle(String param) {
        return "set logic";
    }
}
