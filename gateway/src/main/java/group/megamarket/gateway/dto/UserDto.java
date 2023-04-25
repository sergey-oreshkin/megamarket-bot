package group.megamarket.gateway.dto;

import lombok.*;

/**
 * Сущность для отправки в userService
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserDto {

    private Long id;

    private String username;

}
