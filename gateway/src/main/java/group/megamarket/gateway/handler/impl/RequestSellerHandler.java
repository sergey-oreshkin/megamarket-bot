package group.megamarket.gateway.handler.impl;

import group.megamarket.gateway.dto.user.RequestRoleDto;
import group.megamarket.gateway.dto.user.RoleEnum;
import group.megamarket.gateway.feign.UserServiceClient;
import group.megamarket.gateway.handler.Handler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class RequestSellerHandler implements Handler {

    private final UserServiceClient client;

    @Override
    public String handle(Update update) {
        Long userId = update.getMessage().getFrom().getId();
        RequestRoleDto requestRoleDto = RequestRoleDto
                .builder()
                .id(userId)
                .roleEnum(RoleEnum.SELLER)
                .build();
        client.saveRequestRole(requestRoleDto);
        return "Запрос на роль продавца произведен";
    }
}

