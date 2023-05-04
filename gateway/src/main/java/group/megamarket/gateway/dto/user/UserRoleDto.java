package group.megamarket.gateway.dto.user;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserRoleDto {

    private Long id;

    private Set<RoleDto> roles;
}
