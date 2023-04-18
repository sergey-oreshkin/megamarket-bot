package group.megamarket.storageservice.service;

import group.megamarket.storageservice.model.Role;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public boolean userHasRole(Long userId, Role role) {
        //TODO достать роль из юзер сервиса
        return true;
    }
}
