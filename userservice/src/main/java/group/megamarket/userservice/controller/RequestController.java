package group.megamarket.userservice.controller;

import group.megamarket.userservice.mapper.RequestMapper;
import group.megamarket.userservice.model.dto.RequestDto;
import group.megamarket.userservice.model.dto.RequestRoleDto;
import group.megamarket.userservice.service.RequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для работы c request endpoints
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/requests")
public class RequestController {

    private final RequestService requestService;
    private final RequestMapper requestMapper;

    @GetMapping
    public List<RequestDto> getAllRequests() {
        List<RequestDto> requestDtos = requestMapper.toListDto(requestService.findAll());
        log.info("received list requestDto: " + requestDtos);
        return requestDtos;
    }

    @PostMapping
    public RequestRoleDto saveRequestRole(@RequestBody RequestRoleDto requestRole) {
        log.info("incoming requestRole: " + requestRole);
        RequestRoleDto roleDto = requestService.saveRequestRole(requestRole);
        log.info("saved requestRoleDto: " + roleDto);
        return roleDto;
    }
}
