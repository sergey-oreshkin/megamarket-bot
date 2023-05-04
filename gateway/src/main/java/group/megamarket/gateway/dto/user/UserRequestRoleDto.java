package group.megamarket.gateway.dto.user;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserRequestRoleDto {

    private Long userId;

    private Boolean isAdmin;

    private Boolean isSeller;
}
