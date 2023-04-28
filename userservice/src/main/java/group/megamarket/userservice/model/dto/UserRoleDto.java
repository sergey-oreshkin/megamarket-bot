package group.megamarket.userservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapping;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDto {

    private Long id;
    private Set<RoleDto> roles;

}
