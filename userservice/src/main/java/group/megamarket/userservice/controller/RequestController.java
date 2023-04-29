package group.megamarket.userservice.controller;

import group.megamarket.userservice.mapper.RequestMapper;
import group.megamarket.userservice.model.dto.RequestDto;
import group.megamarket.userservice.model.dto.RequestRoleDto;
import group.megamarket.userservice.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/requests")
public class RequestController {

    private final RequestService requestService;
    private final RequestMapper requestMapper;

    @GetMapping
    public List<RequestDto> getAllRequests() {
        return requestMapper.toListDto(requestService.findAll());
    }

    @PostMapping
    public RequestRoleDto saveRequestRole(@RequestBody RequestRoleDto requestRole) {
        return requestService.saveRequestRole(requestRole);
    }
}
