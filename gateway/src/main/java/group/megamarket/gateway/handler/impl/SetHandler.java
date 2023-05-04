package group.megamarket.gateway.handler.impl;

import feign.FeignException;
import group.megamarket.gateway.dto.user.UserRequestRoleDto;
import group.megamarket.gateway.feign.UserServiceClient;
import group.megamarket.gateway.handler.Handler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Класс для оброаботки /set запроса в формате /set {id} {role} (ADMIN, SELLER)
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SetHandler implements Handler {

    private final UserServiceClient client;

    @Override
    public String handle(Update update) {
        try {
            log.info("Start work /set method");
            String message = update.getMessage().getText();
            String[] arrParam = message.split(" ");
            UserRequestRoleDto requestRoleDto = UserRequestRoleDto
                    .builder()
                    .userId(Long.valueOf(arrParam[1]))
                    .build();
            if (arrParam[2].equals("ADMIN")) {
                requestRoleDto.setIsAdmin(true);
            } else if (arrParam[2].equals("SELLER")) {
                requestRoleDto.setIsSeller(true);
            }
            log.info("UserRequestRoleDto={}", requestRoleDto);
            client.updateUserRole(requestRoleDto);
            log.info("Send correctly response /set method");
            return "Пользователю с id = " + arrParam[1] + " назначена роль " + arrParam[2];
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            log.error("Validation error /set method: {}", e.getMessage(), e);
            return "Ошибка валидации, введите в верном формате\n" +
                    "Например,  /set 1234 ADMIN";
        } catch (FeignException e) {
            log.error("Feign error /set method: {}", e.getMessage(), e);
            return "Произошла ошибка, при попытке назначить роль";
        }
    }
}
