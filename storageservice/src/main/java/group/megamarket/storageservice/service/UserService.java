package group.megamarket.storageservice.service;

import group.megamarket.storageservice.model.Role;

public interface UserService {
    boolean userHasRole(Long userId, Role role);
}
