package group.megamarket.userservice.service;

import group.megamarket.userservice.model.dto.RequestRoleDto;
import group.megamarket.userservice.model.entity.Request;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RequestService {
    List<Request> findAll();

    Boolean requestRole(RequestRoleDto requestRole);
}
