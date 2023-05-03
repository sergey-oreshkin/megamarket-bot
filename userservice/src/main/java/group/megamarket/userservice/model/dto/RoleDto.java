package group.megamarket.userservice.model.dto;

import group.megamarket.userservice.model.entity.RoleEnum;
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
    private RoleEnum roleEnum;
}
