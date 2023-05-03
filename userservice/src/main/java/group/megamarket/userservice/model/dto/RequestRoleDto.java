package group.megamarket.userservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ДТО запроса роли
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestRoleDto {
    private Long userId;
    private RoleDto roleDto;
}
