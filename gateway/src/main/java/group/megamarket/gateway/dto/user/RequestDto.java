package group.megamarket.gateway.dto.user;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RequestDto {

    private UserDto userDto;

    private RoleDto roleDto;
}
