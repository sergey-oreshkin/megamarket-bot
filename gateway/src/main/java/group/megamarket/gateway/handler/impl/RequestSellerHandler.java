package group.megamarket.gateway.handler.impl;

import feign.FeignException;
import group.megamarket.gateway.dto.user.RequestRoleDto;
import group.megamarket.gateway.dto.user.RoleDto;
import group.megamarket.gateway.dto.user.RoleEnum;
import group.megamarket.gateway.feign.UserServiceClient;
import group.megamarket.gateway.handler.Handler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Класс для обработки /requestseller запроса
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RequestSellerHandler implements Handler {

    private final UserServiceClient client;

    @Override
    public String handle(Update update) {
        log.info("Start work /requestseller method");
        Long userId = update.getMessage().getFrom().getId();
        RequestRoleDto requestRoleDto = RequestRoleDto
                .builder()
                .userId(userId)
                .roleDto(RoleDto.builder().roleEnum(RoleEnum.SELLER).build())
                .build();
        log.info("RequestRoleDto={}", requestRoleDto);
        try {
            client.saveRequestRole(requestRoleDto);
            log.info("Send correctly /requestseller method response");
            return "Запрос на роль продавца произведен";
        } catch (FeignException e) {
            log.error("Error where send /requestadmin request: {}", e.getMessage(), e);
            return "Произошла ошибка, при попытке сделать запрос на роль продавца";
        }
    }
}

