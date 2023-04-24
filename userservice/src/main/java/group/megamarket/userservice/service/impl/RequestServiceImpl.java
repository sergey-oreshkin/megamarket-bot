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
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository repository;
    private final RequestMapper requestMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public List<Request> findAll() {
        return repository.findAll();
    }

    @Override
    public RequestRoleDto saveRequestRole(RequestRoleDto requestRole) {
        User user = userRepository.findById(requestRole.getUserId()).orElseThrow(()-> new UserNotFoundException("There is no such user"));
        Role role = roleRepository.findByRoleEnum(requestRole.getRoleDto().getRoleEnum());
        if (user.getRoles().contains(role)) {
            return requestRole;
        }else {
            return requestMapper.toRequestRoleDto(repository.save(requestMapper.toRequest(requestRole)));
        }
    }
}
