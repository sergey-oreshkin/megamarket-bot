package group.megamarket.gateway.dto.user;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RequestRoleDto {
    private Long userId;

    private RoleDto roleDto;
}
