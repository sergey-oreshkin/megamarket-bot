package group.megamarket.storageservice.dto;

import group.megamarket.storageservice.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ДТО роли
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    private Long id;
    private Role role;
}
