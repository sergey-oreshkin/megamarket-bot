package group.megamarket.userservice.service.impl;

import group.megamarket.userservice.model.dto.RequestRoleDto;
import group.megamarket.userservice.model.entity.Request;
import group.megamarket.userservice.repository.RequestRepository;
import group.megamarket.userservice.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository repository;
    @Override
    public List<Request> findAll() {
        return repository.findAll();
    }

    @Override
    public Boolean requestRole(RequestRoleDto requestRole) {
        return null;
    }
}
