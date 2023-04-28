package group.megamarket.storageservice.service;

import group.megamarket.storageservice.model.Role;

public interface UserService {
    void checkUserHasRoleOrThrow(Long userId, Role role);
}
