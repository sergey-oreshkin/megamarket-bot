package group.megamarket.storageservice.dto;

import group.megamarket.storageservice.model.Role;
import lombok.Data;

@Data
public class RoleDto {
    private Long id;
    private Role role;
}
