package group.megamarket.gateway.dto.user;

import lombok.*;

/**
 * Сущность для отправки в userService
 */

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserDto {

    private Long id;

    private String username;

}
