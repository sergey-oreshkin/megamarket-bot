package group.megamarket.storageservice.dto;

import group.megamarket.storageservice.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * ДТО роли
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    private Long id;
    private Role role;
}
