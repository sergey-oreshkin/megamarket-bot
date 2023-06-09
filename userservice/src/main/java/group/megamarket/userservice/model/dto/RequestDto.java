package group.megamarket.userservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ДТО запроса
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {
    private Long id;
    private UserDto userDto;
    private RoleDto roleDto;
}
