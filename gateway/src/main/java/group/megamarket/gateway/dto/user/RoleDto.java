package group.megamarket.gateway.dto.user;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class RoleDto {

    private Long id;

    private RoleEnum roleEnum;
}
