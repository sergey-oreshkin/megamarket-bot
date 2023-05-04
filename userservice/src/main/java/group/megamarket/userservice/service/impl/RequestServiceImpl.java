package group.megamarket.userservice.service.impl;

import group.megamarket.userservice.exception.UserNotFoundException;
import group.megamarket.userservice.mapper.RequestMapper;
import group.megamarket.userservice.model.dto.RequestRoleDto;
import group.megamarket.userservice.model.entity.Request;
import group.megamarket.userservice.model.entity.Role;
import group.megamarket.userservice.model.entity.User;
import group.megamarket.userservice.repository.RequestRepository;
import group.megamarket.userservice.repository.RoleRepository;
import group.megamarket.userservice.repository.UserRepository;
import group.megamarket.userservice.service.RequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;

/**
 * Сервис для работы с запросами. Поиск, сохранение и тп.
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository repository;
    private final RequestMapper requestMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    /**
     * Метод возвращает список всех запросов
     */
    @Override
    public List<Request> findAll() {
        List<Request> requests = repository.findAll();
        log.info("received list requests: {}", requests);
        return requests;
    }

    /**
     * Метод сохраняет каждый запрос роли
     *
     * @param requestRole - запрашиваемая роль.
     */
    @Override
    public RequestRoleDto saveRequestRole(RequestRoleDto requestRole) {
        User user = userRepository.findById(requestRole.getUserId()).orElseThrow(() -> new UserNotFoundException("There is no such user"));
        log.info("user from role: {}", user);
        Role role = roleRepository.findByRoleEnum(requestRole.getRoleDto().getRoleEnum());
        log.info("roles from requestRole: {}", role);

        if (user.getRoles() == null) user.setRoles(new HashSet<>());

        if (user.getRoles().contains(role)) {
            log.info("saved role: {}", requestRole);
            return requestRole;
        } else {
            Request request = requestMapper.toRequest(requestRole);
            request.setUser(user);
            request.setRole(role);
            RequestRoleDto savedRequestRoleDto = requestMapper.toRequestRoleDto(repository.save(request));
            log.info("saved roleDro: {}", savedRequestRoleDto);
            return savedRequestRoleDto;
        }
    }
}
