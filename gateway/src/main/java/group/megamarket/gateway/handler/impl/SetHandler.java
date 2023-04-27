package group.megamarket.gateway.handler.impl;

import group.megamarket.gateway.dto.user.UserRequestRoleDto;
import group.megamarket.gateway.feign.UserServiceClient;
import group.megamarket.gateway.handler.Handler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@RequiredArgsConstructor
public class SetHandler implements Handler {

    private final UserServiceClient client;

    @Override
    public String handle(Update update) {
        String message = update.getMessage().getText();
        String[] arrParam = message.split(" ");
        boolean isAdmin = false;
        boolean isSeller = false;
        if (arrParam[2].equals("ADMIN")) {
            isAdmin = true;
        } else {
            isSeller = true;
        }
        UserRequestRoleDto requestRoleDto = UserRequestRoleDto
                .builder()
                .userId(Long.valueOf(arrParam[1]))
                .isAdmin(isAdmin)
                .isSeller(isSeller)
                .build();
        client.updateUserRole(requestRoleDto);
        return "Пользователю с id = " + arrParam[1] + " назначена роль " + arrParam[2];
    }
}
